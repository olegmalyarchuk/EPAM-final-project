package com.example.conference.controller;

import com.example.conference.comparators.DateComparator;
import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.comparators.UsersCountComparator;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/newEvent","/insertEvent","/deleteEvent", "/editEvent", "/updateEvent", "/listEvent", "/eventEvent"})
public class EventServlet extends HttpServlet {
    public static final long serialVersionUID = 1234882438L;
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/newEvent":
                showNewForm(req, resp);
                break;
            case "/insertEvent":
                insertUser(req, resp);
                break;
            case "/deleteEvent":
                deleteEvent(req, resp);
                break;
            case "/editEvent":
                showEditForm(req, resp);
                break;
            case "/updateEvent":
               updateUser(req, resp);
               break;
            case "/listEvent":
                 listEvents(req, resp);
                  break;
            case "/eventEvent":
                showEvent(req, resp);
        }
    }

    private void listEvents(HttpServletRequest request, HttpServletResponse response) {
        try {
          String status = "all";
          if(request.getParameter("eventStatus") != null) status = request.getParameter("eventStatus");
          String orderBy = "default";
          if(request.getParameter("orderBy") != null) orderBy = request.getParameter("orderBy");
            List<Events> eventsList = service.findAllEventsInDB();
            for(int i = 0; i < eventsList.size(); i++) {
                int event_id = eventsList.get(i).getEvent_id();
                int cntRegistered = service.calculateRegistered(event_id);
                int cntPresent = service.calculatePresent(event_id);
                cntRegistered += cntPresent;
                eventsList.get(i).setRegisteredCount(cntRegistered);
                List<Reports> reports = reportService.findReportsByEvent(eventsList.get(i));
                int reportsCnt = reports.size();
                eventsList.get(i).setReportsCount(reports.size());
            }
            //sorting
            if(orderBy.equals("date")) {
                Collections.sort(eventsList, new DateComparator());
            } else if(orderBy.equals("reports")) {
                Collections.sort(eventsList, new ReportsCountComparator());
            } else if(orderBy.equals("users")) {
                Collections.sort(eventsList, new UsersCountComparator());
            }
            request.setAttribute("eventStatus", status);
            request.setAttribute("eventsList", eventsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_list.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEvent(HttpServletRequest request, HttpServletResponse response) {
        try {
            int event_id = Integer.parseInt(request.getParameter("id"));
            Events events = service.findEventsById(event_id);
            int cntRegistered = service.calculateRegistered(events.getEvent_id());
            int cntPresent = service.calculatePresent(events.getEvent_id());
            cntRegistered += cntPresent;
            List<Reports> reports = reportService.findReportsByEvent(events);
            List<User> speakers = new ArrayList<>();
          for(int i = 0; i < reports.size(); i++) {
              int report_id = reports.get(i).getReport_id();
              //find speaker
              Report_speakers report_speakers = reportSpeakerService.findReportById(report_id);
              if(report_speakers==null) {
                  User u = new User();
                  u.setUser_surname("null");
                  u.setUser_name("null");
                  speakers.add(u);
              } else {
                  int speaker_id = report_speakers.getSpeaker_id();
                  User u = userService.findUserById(speaker_id);
                  speakers.add(u);
              }

          }
          boolean isRegister = false;
          String email = (String) request.getSession().getAttribute("email");
          User u = userService.findUserByEmail(email);
          int user_id = u.getId();
          List<Event_users>  eventUsers = eventUsersService.findAllEventUsersInDB();
          for(int i = 0; i < eventUsers.size(); i++) {
              Event_users eu = eventUsers.get(i);
              if(eu.getUser_id()==user_id && eu.getEvent_id()==event_id) {
                  isRegister = true;
                  break;
              }
          }
            request.setAttribute("event", events);
            request.setAttribute("reports", reports);
            request.setAttribute("speakers", speakers);
            request.setAttribute("registered", cntRegistered);
            request.setAttribute("present", cntPresent);
            if(isRegister) request.setAttribute("isRegister", "yes");
            else request.setAttribute("isRegister", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("event.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = service.deleteEventsByIdFromDB(id);
            if(isDeleted) request.setAttribute("status", "successDelete");
            else request.setAttribute("status", "errorDelete");
            dataforMailUpdate(id);
            response.sendRedirect("listEvent");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_form.jsp");
            dispatcher.forward(request, response);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Events event = service.findEventsById(id);
            request.setAttribute("event", event);
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_form.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            String event_name_ua = request.getParameter("event_name_ua");
            String event_name_en = request.getParameter("event_name_en");
            String event_place_ua = request.getParameter("event_place_ua");
            String event_place_en = request.getParameter("event_place_en");
            String event_description_ua = request.getParameter("event_description_ua");
            String event_description_en = request.getParameter("event_description_en");
            LocalDateTime event_date = LocalDateTime.parse(request.getParameter("event_date"));
            String event_photo_url = request.getParameter("event_photo_url");
            Events events = new Events();
            events.setEvent_id(event_id);
            events.setEvent_name_ua(event_name_ua);
            events.setEvent_name_en(event_name_en);
            events.setEvent_place_ua(event_place_ua);
            events.setEvent_place_en(event_place_en);
            events.setEvent_description_ua(event_description_ua);
            events.setEvent_description_en(event_description_en);
            events.setEvent_date(event_date);
            events.setEvent_photo_url(event_photo_url);
            boolean isUpdated = service.updateEventsInDB(events);
            if(isUpdated) request.setAttribute("status", "successUpdate");
            else request.setAttribute("status", "errorUpdate");
            dataforMailUpdate(event_id);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            e.printStackTrace();
        }


    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer event_id = service.calculateEventNumber()+1;
            String event_name_ua = request.getParameter("event_name_ua");
            String event_name_en = request.getParameter("event_name_en");
            String event_place_ua = request.getParameter("event_place_ua");
            String event_place_en = request.getParameter("event_place_en");
            String event_description_ua = request.getParameter("event_description_ua");
            String event_description_en = request.getParameter("event_description_en");
            LocalDateTime event_date = LocalDateTime.parse(request.getParameter("event_date"));
            String event_photo_url = request.getParameter("event_photo_url");

            Events events = new Events();
            events.setEvent_id(event_id);
            events.setEvent_name_ua(event_name_ua);
            events.setEvent_name_en(event_name_en);
            events.setEvent_place_ua(event_place_ua);
            events.setEvent_place_en(event_place_en);
            events.setEvent_description_ua(event_description_ua);
            events.setEvent_description_en(event_description_en);
            events.setEvent_date(event_date);
            events.setEvent_photo_url(event_photo_url);
            boolean isInserted = service.addEventsToDB(events);
            if(isInserted) request.setAttribute("status", "successInsert");
            else request.setAttribute("status", "errorInsert");
            dataforMailUpdate(event_id);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            e.printStackTrace();
        }

    }

    private void dataforMailUpdate(int event_id) throws DBException {
        int id = event_id;
        List<User> userList = new ArrayList<>();
        List<Report_speakers> rs = reportSpeakerService.findAllReportSpeakersInDB();
        List<Event_users> ev = eventUsersService.findAllEventUsersInDB();
        for(int i = 0; i < ev.size(); i++) {
            if(ev.get(i).getEvent_id()==id) {
                Integer user_id = ev.get(i).getUser_id();
                User u = userService.findUserById(user_id);
                userList.add(u);
            }
        }
        for(int i = 0; i < rs.size(); i++) {
            Report_speakers report_speakers = rs.get(i);
            Integer speaker_id = report_speakers.getSpeaker_id();
            Integer report_id = report_speakers.getReport_id();
            List<Reports> reports = reportService.findAllReportsInDB();
            for(int j = 0; j < reports.size(); j++) {
                if(reports.get(j).getEvent_id()==id) {
                    User u = userService.findUserById(speaker_id);
                    userList.add(u);
                    break;
                }
            }
        }
        String event_name = service.findEventsById(id).getEvent_name_en();
        GmailSender.sendEventChange(userList, event_name);
    }

}
