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

@WebServlet(urlPatterns = {"/newReport","/deleteReport", "/editReport", "/updateReport", "/listReport", "/proposeReport", "/addReportPropose", "/addReport", "/insertReport", "/registerUser", "/excludeUser"})
public class ReportServlet extends HttpServlet{
    public static final long serialVersionUID = 123478392438L;
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IReportPrepositionService reportPrepositionService = ServiceFactory.getInstance().getReportPrepositionService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/proposeReport":
                showProposeReport(req, resp);
                break;
            case "/addReportPropose":
                rinsertProposeReport(req, resp);
                break;
            case "/addReport":
                showAddReportForm(req, resp);
                break;
            case "/insertReport":
                insReport(req, resp);
                break;
            case "/registerUser":
                addUser(req, resp);
                break;
            case "/excludeUser":
                deleteUser(req, resp);
                break;
            case "/deleteReport":
                delRep(req, resp);
                break;
            case "/editReport":
                editRep(req, resp);
                break;
            case "/updateReport":
                updateRep(req, resp);
                break;
        }
    }

    private void showProposeReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            String event_id = request.getParameter("event_id");
            Integer id = reportPrepositionService.calculateReportPrepositionNumber()+1;
            String email = (String) request.getSession().getAttribute("email");
            User u = userService.findUserByEmail(email);
            Integer speaker_id = u.getId();
            request.setAttribute("id", id);
            request.setAttribute("speaker_id", speaker_id);
            request.setAttribute("event_id", event_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reportPropose_form.jsp");
            dispatcher.forward(request, response);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    private void showAddReportForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            String event_id = request.getParameter("event_id");
            Integer report_id = reportService.calculateReportsNumber()+1;
            request.setAttribute("report_id", report_id);
            request.setAttribute("event_id", event_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reportAdd_form.jsp");
            dispatcher.forward(request, response);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void rinsertProposeReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
       Integer id = Integer.valueOf(request.getParameter("id"));
      Integer event_id = Integer.valueOf(request.getParameter("event_id"));
      Integer speaker_id = Integer.valueOf(request.getParameter("speaker_id"));
       String report_name_ua = request.getParameter("report_name_ua");
       String report_name_en = request.getParameter("report_name_en");
        Report_preposition rp = new Report_preposition();
        rp.setId(id);
        rp.setEvent_id(event_id);
        rp.setSpeaker_id(speaker_id);
        rp.setReport_name_ua(report_name_ua);
        rp.setReport_name_en(report_name_en);
        reportPrepositionService.addReportPrepositionToDB(rp);
        response.sendRedirect("listEvent");

    }

    private void insReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            String report_name_ua = request.getParameter("report_name_ua");
            String report_name_en = request.getParameter("report_name_en");
            Reports r = new Reports();
            r.setReport_id(report_id);
            r.setEvent_id(event_id);
            r.setReport_name_ua(report_name_ua);
            r.setReport_name_en(report_name_en);
            reportService.addReportToDB(r);
            response.sendRedirect("listEvent");
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = eventUsersService.calculateEventUsersNumber()+2;
            String email = request.getParameter("email");
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            Events events = service.findEventsById(event_id);
            User user = userService.findUserByEmail(email);
            Integer user_id = user.getId();
            Event_users event_users = new Event_users();
            event_users.setId(id);
            event_users.setUser(user);
            event_users.setUser_id(user_id);
            event_users.setEvent_id(event_id);
            event_users.setPresent(false);
            eventUsersService.addEventUsersToDB(event_users);
            GmailSender.sendEventWelcome(email, user.getUser_name(), user.getUser_surname(), events.getEvent_place_en(), events.getEvent_date());
            response.sendRedirect("listEvent");
        } catch (DBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = eventUsersService.calculateEventUsersNumber()+2;
            String email = request.getParameter("email");
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            User user = userService.findUserByEmail(email);
            Integer user_id = user.getId();
            Event_users event_users = new Event_users();
            event_users.setId(id);
            event_users.setUser(user);
            event_users.setUser_id(user_id);
            event_users.setEvent_id(event_id);
            event_users.setPresent(false);
            eventUsersService.deleteEventUsersFromDB(event_users);
            response.sendRedirect("/listEvent");
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void delRep(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Reports r = new Reports();
            List<Reports> reports = reportService.findAllReportsInDB();
            List<Report_speakers> report_speakers = reportSpeakerService.findAllReportSpeakersInDB();
            List<Speaker_preposition> speakerPrepositions = speakerPrepositionService.findAllSpeakerPrepositionInDB();
            List<Moderator_preposition> moderator_prepositions = moderatorPrepositionService.findAllModeratorPrepositionInDB();
            for(int i = 0; i < report_speakers.size(); i++) {
                if(report_speakers.get(i).getReport_id()==report_id) {
                    reportSpeakerService.deleteReportSpeakersFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < speakerPrepositions.size(); i++) {
                if(speakerPrepositions.get(i).getReport_id()==report_id) {
                    speakerPrepositionService.deleteSpeakerPrepositionFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < moderator_prepositions.size(); i++) {
                if(moderator_prepositions.get(i).getReport_id()==report_id) {
                    moderatorPrepositionService.deleteModeratorPrepositionFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < reports.size(); i++) {
                if(reports.get(i).getReport_id()==report_id) {
                    r = reports.get(i);
                }
            }
            reportService.deleteReportFromDB(r);
            response.sendRedirect("/listEvent");
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    private void editRep(HttpServletRequest request, HttpServletResponse response) {
        try {
            int event_id = Integer.parseInt(request.getParameter("event_id"));
            int report_id = Integer.parseInt(request.getParameter("report_id"));
            request.setAttribute("event_id", event_id);
            request.setAttribute("report_id", report_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reportUpdate_form.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRep(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer event_id =  Integer.valueOf(request.getParameter("event_id"));
            String report_name_ua = request.getParameter("report_name_ua");
            String report_name_en = request.getParameter("report_name_en");
            Reports reports = new Reports();
            reports.setReport_id(report_id);
            reports.setEvent_id(event_id);
            reports.setReport_name_ua(report_name_ua);
            reports.setReport_name_en(report_name_en);
            reportService.updateReportInDB(reports);
            response.sendRedirect("listEvent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
