package com.example.conference.controller;

import com.example.conference.comparators.DateComparator;
import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.comparators.UsersCountComparator;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

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

/**
 *
 *Servlet for all action with event
 *
 */
@WebServlet(urlPatterns = {"/newEvent","/insertEvent","/deleteEvent", "/editEvent", "/updateEvent", "/listEvent", "/eventEvent", "/proposeMe", "/editPresence", "/setPresence", "/setPreposition", "/proposeSpeaker", "/lang"})
public class EventServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(EventServlet.class);
    public static final long serialVersionUID = 1234882438L;
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();;
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();;

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
                break;
            case "/proposeMe":
                addPreposition(req, resp);
                break;
            case "/editPresence":
                showEditPresense(req, resp);
                break;
            case "/setPresence":
                setPresent(req, resp);
                break;
            case "/proposeSpeaker":
                showProposeForm(req, resp);
                break;
            case "/setPreposition":
                setPreps(req, resp);
                break;
            case "/lang":
                changeLang(req, resp);
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
           log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
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
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = service.deleteEventsByIdFromDB(id);
            if(isDeleted) request.setAttribute("status", "successDelete");
            else request.setAttribute("status", "errorDelete");
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "en";
            dataforMailUpdate(id, lang);
            response.sendRedirect("listEvent");
        } catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_form.jsp");
            dispatcher.forward(request, response);
        }  catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
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
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
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
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "ua";
            dataforMailUpdate(event_id, lang);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            log.error(e);
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
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "ua";
            dataforMailUpdate(event_id, lang);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            log.error(e);
        }

    }

    private void dataforMailUpdate(int event_id, String lang) throws DBException {
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
      if(lang.equals("ua")) service.findEventsById(id).getEvent_name_ua();
        GmailSender.sendEventChange(userList, event_name, lang);
    }

    private void addPreposition(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = speakerPrepositionService.calculateSpeakerPrepositionNumber()+1;
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer speaker_id = Integer.valueOf(request.getParameter("speaker_id"));
            Speaker_preposition sp = new Speaker_preposition();
            sp.setId(id);
            sp.setReport_id(report_id);
            sp.setSpeaker_id(speaker_id);
            speakerPrepositionService.addSpeakerPrepositionToDB(sp);
            response.sendRedirect("listEvent");
        } catch (DBException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }

    }

    private void showEditPresense(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer page = 1;
            if(request.getParameter("page")!=null) page = Integer.valueOf(request.getParameter("page"));
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            List<Event_users> ev = eventUsersService.findAllEventUsersInDB();
            List<Event_users> event_users = new ArrayList<>();


            for(int i = 0; i < ev.size(); i++) {
                if(ev.get(i).getEvent_id()==event_id) {
                   event_users.add(ev.get(i));
                }
            }

            //pagination
            Integer perPage = 3;
            int cntPages = event_users.size()/perPage;
            if(event_users.size()%perPage!=0) cntPages++;
            boolean prevDisabled = false;
            boolean nextDisabled = false;
            if(page==1) prevDisabled=true;
            if(page==cntPages) nextDisabled = true;
            if(page<1) page = 1;
            if(page>cntPages) page = cntPages;
            Integer start = (page-1)*perPage;
            Integer end = start+perPage;
            if(start>event_users.size()) {
                start = 0;
                end = 0;
            }
            if(end>event_users.size()) end = event_users.size();

            List<Event_users> paginatedList = new ArrayList<>();
            for(int i = start; i < end; i++) {
                paginatedList.add(event_users.get(i));
            }

            request.setAttribute("page", page);
            request.setAttribute("prevDisabled", prevDisabled);
            request.setAttribute("nextDisabled", nextDisabled);
            request.setAttribute("event_users", paginatedList);
            request.setAttribute("event_id", event_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editPresence.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void setPresent(HttpServletRequest request, HttpServletResponse response) {
        Integer event_id = Integer.valueOf(request.getParameter("event_id"));
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        String presense = request.getParameter("presence");
        boolean isPresent = false;
        if(presense.equals("yes")) isPresent = true;
        Event_users ev = new Event_users();
        ev.setUser_id(user_id);
        ev.setEvent_id(event_id);
        ev.setPresent(isPresent);
        eventUsersService.updateUserPresenceByUserIdAndMeetingId(ev);
        try {
            response.sendRedirect("editPresence?event_id="+event_id);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void showProposeForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            List<User> users = userService.findAllUsersInDB();
            List<User> speakers = new ArrayList<>();
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getRole_id()==2) {
                    speakers.add(users.get(i));
                }
            }
            request.setAttribute("speakers", speakers);
            request.setAttribute("report_id", report_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("moderatorPrepositionForm.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void setPreps(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = moderatorPrepositionService.calculateModeratorPrepositionNumber()+2;
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer speaker_id = Integer.valueOf(request.getParameter("speaker_id"));
            Moderator_preposition mp = new Moderator_preposition();
            mp.setId(id);
            mp.setReport_id(report_id);
            mp.setSpeaker_id(speaker_id);
            moderatorPrepositionService.addModeratorPrepositionToDB(mp);
            response.sendRedirect("proposeSpeaker?report_id="+report_id);
        } catch (DBException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void changeLang(HttpServletRequest request, HttpServletResponse response) {
        String lang = "en";
        if(request.getParameter("lang").equals("ua")) lang = "ua";
        request.getSession().setAttribute("lang", lang);
        try {
            response.sendRedirect("/listEvent");
        } catch (IOException e) {
            log.error(e);
        }
    }

}
