package com.example.conference.controller;

import com.example.conference.comparators.DateComparator;
import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.comparators.UsersCountComparator;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;
import com.example.conference.validator.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/showProfile", "/editInfo", "/deleteUser", "/editUser", "/updateUser", "/rejectSpeakerFromModerator", "/acceptSpeakerFromModerator", "/rejectSpeakerForReport", "/rejectSpeakerReports", "/acceptSpeakerForReport", "/acceptSpeakerReports"})
public class ProfileServlet extends HttpServlet {
    public static final long serialVersionUID = 123278492438L;
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
            case "/showProfile":
                viewProfile(req, resp);
                break;
            case "/editInfo":
                updateUser(req, resp);
                break;
            case "/rejectSpeakerFromModerator":
                rejectSpeakerFromModer(req, resp);
                break;
            case "/acceptSpeakerFromModerator":
                acceptSpeakerFromModer(req, resp);
                break;
            case "/acceptSpeakerReports":
                accSpeakerReports(req, resp);
                break;
            case "/acceptSpeakerForReport":
                accSpeakerForReport(req, resp);
                break;
            case "/rejectSpeakerForReport":
                rejFromSpeaker(req, resp);
                break;
            case "/rejectSpeakerReports":
                rejSpeakerReport(req, resp);
                break;
            case "/editUser":
                showUpdateUserForm(req, resp);
                break;
            case "/updateUser":
                updateUserInfo(req, resp);
                break;
            case "/deleteUser":
                deleteUserFromSite(req, resp);
                break;
        }
    }

    private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User u = userService.findUserByEmail((String) session.getAttribute("email"));
        String name = u.getUser_name();
        String surname = u.getUser_surname();
        String phone = u.getUser_phone();
        String email = u.getUser_email();
        String location = u.getUser_address();
        Integer role_id = (Integer) session.getAttribute("role_id");
        request.setAttribute("name", name);
        request.setAttribute("surname", surname);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("location", location);
        if(role_id==1) {


            String userStatus = "all";
            if(request.getParameter("userStatus") != null) userStatus = request.getParameter("userStatus");
            request.setAttribute("userStatus", userStatus);
            List<User> allUsers = userService.findAllUsersInDB();
            List<User> users = new ArrayList<>();
            for(int i = 0; i < allUsers.size(); i++) {
                if( (userStatus.equals("users") && allUsers.get(i).getRole_id()==3) || (userStatus.equals("speakers") && allUsers.get(i).getRole_id()==2) || userStatus.equals("all")) {
                    users.add(allUsers.get(i));
                }
            }
            request.setAttribute("users", users);

            String prepositionStatus = "fromModerator";
            if(request.getParameter("prepositionStatus") != null) prepositionStatus = request.getParameter("prepositionStatus");
            request.setAttribute("prepositionStatus", prepositionStatus);
            //fromModer
            if(prepositionStatus.equals("fromModerator")) {
                try {
                    List<Moderator_preposition> allModeratorPreps = moderatorPrepositionService.findAllModeratorPrepositionInDB();
                    List<Reports> moderatorReports = new ArrayList<>();
                    List<Events> fromModeratorEvents = new ArrayList<>();
                    for(int i = 0; i < allModeratorPreps.size(); i++) {
                        Integer report_id = allModeratorPreps.get(i).getReport_id();
                        Reports rep = reportService.findReportsByReportId(report_id);
                        moderatorReports.add(rep);
                        Integer event_id = rep.getEvent_id();
                        Events event = service.findEventsById(event_id);
                        fromModeratorEvents.add(event);

                    }


                    request.setAttribute("propEvents", fromModeratorEvents);
                    request.setAttribute("propReports", moderatorReports);
                    request.setAttribute("Preps", allModeratorPreps);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("fromSpeaker")) {
                try {
                    List<Speaker_preposition> allSpeakerPreps = speakerPrepositionService.findAllSpeakerPrepositionInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromSpeakerEvents = new ArrayList<>();
                    for(int i = 0; i < allSpeakerPreps.size(); i++) {
                        Integer report_id = allSpeakerPreps.get(i).getReport_id();
                        Reports rep = reportService.findReportsByReportId(report_id);
                        speakerReports.add(rep);
                        Integer event_id = rep.getEvent_id();
                        Events event = service.findEventsById(event_id);
                        fromSpeakerEvents.add(event);

                    }


                    request.setAttribute("propEvents", fromSpeakerEvents);
                    request.setAttribute("propReports", speakerReports);
                    request.setAttribute("Preps", allSpeakerPreps);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("forEvent")) {
                    try {
                        List<Report_preposition> allReportPreps = reportPrepositionService.findAllReportPrepositionInDB();

                        List<Events> events = new ArrayList<>();
                        List<Report_preposition> forReports = new ArrayList<>();
                        for (int i = 0; i < allReportPreps.size(); i++) {
                                forReports.add(allReportPreps.get(i));
                                Integer event_id = allReportPreps.get(i).getEvent_id();
                                Events events1 = service.findEventsById(event_id);
                                events.add(events1);
                        }
                        request.setAttribute("propEvents", events);
                        request.setAttribute("propReports", forReports);
                        request.setAttribute("Preps", allReportPreps);
                    } catch (DBException e) {
                        e.printStackTrace();
                    }
            }



        } else if(role_id==2) {


            Integer speaker_id = u.getId();
            request.setAttribute("speaker_id", speaker_id);
            String eventStatus = "all";
            if(request.getParameter("eventStatus") != null) eventStatus = request.getParameter("eventStatus");
            request.setAttribute("eventStatus", eventStatus);
            try {
                List<Report_speakers> event_users = reportSpeakerService.findAllReportSpeakersInDB();
                List<Integer> speakerReportIds = new ArrayList<>();
                //find report_id with speaker id
                for(int i = 0; i < event_users.size(); i++) {
                    if(event_users.get(i).getSpeaker_id()==speaker_id) {
                        Integer report_id = event_users.get(i).getReport_id();
                        speakerReportIds.add(report_id);
                    }
                }

                List<Reports> allReports = reportService.findAllReportsInDB();
                List<Events> speakerEvents = new ArrayList<>();
                for(int i = 0; i < allReports.size(); i++) {
                    if(speakerReportIds.contains(allReports.get(i).getReport_id())) {
                        Events speakerEvent = service.findEventsById(allReports.get(i).getEvent_id());
                        if((eventStatus.equals("finished") && speakerEvent.isFinished())
                        || (eventStatus.equals("upcoming") && !speakerEvent.isFinished()) || (eventStatus.equals("all"))) {
                            speakerEvents.add(speakerEvent);
                        }
                    }
                }
                request.setAttribute("events", speakerEvents);
            } catch (DBException e) {
                e.printStackTrace();
            }
            String prepositionStatus = "fromModerator";
            if(request.getParameter("prepositionStatus") != null) prepositionStatus = request.getParameter("prepositionStatus");
            request.setAttribute("prepositionStatus", prepositionStatus);
            //fromModer
            if(prepositionStatus.equals("fromModerator")) {
                try {
                    List<Moderator_preposition> allModeratorPreps = moderatorPrepositionService.findAllModeratorPrepositionInDB();
                    List<Integer> fromModeratorReportsId = new ArrayList<>();
                    List<Moderator_preposition> moderatorPrepsId = new ArrayList<>();
                    for (int i = 0; i < allModeratorPreps.size(); i++) {
                        if (allModeratorPreps.get(i).getSpeaker_id() == speaker_id) {
                            fromModeratorReportsId.add(allModeratorPreps.get(i).getReport_id());
                                moderatorPrepsId.add(allModeratorPreps.get(i));
                        }
                    }
                    List<Reports> allReports = reportService.findAllReportsInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromModeratorEvents = new ArrayList<>();
                    List<Moderator_preposition> moderatorPreps = new ArrayList<>();
                    for (int i = 0; i < allReports.size(); i++) {
                        if (fromModeratorReportsId.contains(allReports.get(i).getReport_id())) {
                            Integer event_id = allReports.get(i).getEvent_id();
                            Events events = service.findEventsById(event_id);
                            fromModeratorEvents.add(events);
                            speakerReports.add(allReports.get(i));
                            reportService.findReportsByEvent(events);
                        }
                    }
                    request.setAttribute("propEvents", fromModeratorEvents);
                    request.setAttribute("propReports", speakerReports);
                   request.setAttribute("moderatorPreps", moderatorPrepsId);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("fromSpeaker")) {
                try {
                    List<Speaker_preposition> allSpeakerPreps = speakerPrepositionService.findAllSpeakerPrepositionInDB();
                    List<Speaker_preposition> speakerPreps = new ArrayList<>();
                    List<Integer> fromSpeakerReportsId = new ArrayList<>();
                    for (int i = 0; i < allSpeakerPreps.size(); i++) {
                        if (allSpeakerPreps.get(i).getSpeaker_id() == speaker_id) {
                            fromSpeakerReportsId.add(allSpeakerPreps.get(i).getReport_id());
                            speakerPreps.add(allSpeakerPreps.get(i));
                        }
                    }
                    List<Reports> allReports = reportService.findAllReportsInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromSpeakerEvents = new ArrayList<>();
                    for (int i = 0; i < allReports.size(); i++) {
                        if (fromSpeakerReportsId.contains(allReports.get(i).getReport_id())) {
                            Integer event_id = allReports.get(i).getEvent_id();
                            Events events = service.findEventsById(event_id);
                            fromSpeakerEvents.add(events);
                            speakerReports.add(allReports.get(i));
                            reportService.findReportsByEvent(events);
                        }
                    }
                    request.setAttribute("propEvents", fromSpeakerEvents);
                    request.setAttribute("propReports", speakerReports);
                   request.setAttribute("speakerPreps", speakerPreps);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("forEvent")) {
                try {
                    List<Report_preposition> allReportPreps = reportPrepositionService.findAllReportPrepositionInDB();

                    List<Events> events = new ArrayList<>();
                    List<Report_preposition> forReports = new ArrayList<>();
                    for (int i = 0; i < allReportPreps.size(); i++) {
                        if (allReportPreps.get(i).getSpeaker_id() == speaker_id) {
                            forReports.add(allReportPreps.get(i));
                            Integer event_id = allReportPreps.get(i).getEvent_id();
                            Events events1 = service.findEventsById(event_id);
                            events.add(events1);
                        }
                    }
                    request.setAttribute("propEvents", events);
                    request.setAttribute("propReports", forReports);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }


        } else {


            //for user
            String eventStatus = "all";
            if(request.getParameter("eventStatus") != null) eventStatus = request.getParameter("eventStatus");
            request.setAttribute("eventStatus", eventStatus);
            try {
                List<Events> eventsRaw = service.findAllEventsInDB();
                List<Events> events = new ArrayList<>();
                for(int i = 0; i < eventsRaw.size(); i++) {
                    Event_users event_users = eventUsersService.findByUserIdAndEventId(u.getId(), eventsRaw.get(i).getEvent_id());
                    if(event_users==null) continue;
                    if(eventStatus.equals("finished")) {
                        if(eventsRaw.get(i).isFinished()) events.add(eventsRaw.get(i));
                    } else if(eventStatus.equals("upcoming")) {
                        if(eventsRaw.get(i).isFinished()) events.add(eventsRaw.get(i));
                    } else if(eventStatus.equals("all")) {
                        events.add(eventsRaw.get(i));
                    }
                }
                List<String> presense = new ArrayList<>();
                for(int i = 0; i < events.size(); i++) {
                    Integer ev_id = events.get(i).getEvent_id();
                    Event_users event_users = eventUsersService.findByUserIdAndEventId(u.getId(), ev_id);
                   if(event_users==null) continue;
                    if(event_users.isPresent()) presense.add("Present");
                    else presense.add("Registered");
                }
                request.setAttribute("events", events);
                request.setAttribute("presense", presense);
            } catch (DBException e) {
                e.printStackTrace();
            }



        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //for user info form
        User u = userService.findUserByEmail((String) request.getSession().getAttribute("email"));
        Integer user_id = u.getId();
        Integer role_id = u.getRole_id();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = u.getUser_password();
        if(request.getParameter("password") != null) {
            if(!request.getParameter("password").equals(""))
             password = request.getParameter("password");
        }
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String user_photo_url = u.getUser_photo_url();
        String location = request.getParameter("location");
        int cntEmails = 0;
        int cntPhones = 0;
        List<User> users = userService.findAllUsersInDB();
        try {
            cntEmails = userService.calculateRowsBy("user_email", email);
            for(int i = 0; i < users.size(); i++) {
                User usr = users.get(i);
                if(usr.getUser_email().equals(email) && usr.getId()==user_id) {
                    cntEmails = 0;
                    break;
                }
            }
            cntPhones = userService.calculateRowsBy("user_phone", phone);
            for(int i = 0; i < users.size(); i++) {
                User usr = users.get(i);
                if(usr.getUser_phone().equals(phone) && usr.getId()==user_id) {
                    cntPhones = 0;
                    break;
                }
            }
        } catch (DBException e) {
            //e.printStackTrace();
        }

        //empty
        if(name == null || name.equals("")) {
            request.setAttribute("editStatus", "emptyFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidName(name)) {
            request.setAttribute("editStatus", "invalidFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(surname == null || surname.equals("")) {
            request.setAttribute("editStatus", "emptyLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidName(surname)) {
            request.setAttribute("editStatus", "invalidLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidPassword(password)) {
            request.setAttribute("editStatus", "invalidPassword");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(email == null || email.equals("")) {
            request.setAttribute("editStatus", "emptyEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidEmail(email)) {
            request.setAttribute("editStatus", "invalidEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(cntEmails!=0) {
            request.setAttribute("editStatus", "dublicateEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);

        }
        else if(phone == null || phone.equals("")) {
            request.setAttribute("editStatus", "emptyPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidPhone(phone)) {
            request.setAttribute("editStatus", "invalidPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        }
        else if(cntPhones!=0) {
            request.setAttribute("editStatus", "dublicatePhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);

        }
        else if(location == null || location.equals("")) {
            request.setAttribute("editStatus", "emptyAddress");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            dispatcher.forward(request, response);
        } else {
            User newUser = new User();
            newUser.setId(user_id);
            newUser.setRole_id(role_id);
            newUser.setUser_name(name);
            newUser.setUser_surname(surname);
            newUser.setUser_password(password);
            newUser.setUser_phone(phone);
            newUser.setUser_email(email);
            newUser.setUser_photo_url(user_photo_url);
            newUser.setUser_address(location);
            boolean isUpdated = userService.updateUserInDB(newUser);
            if(isUpdated) {
                request.getSession().setAttribute("email", email);
                request.setAttribute("editStatus", "successUpdate");
            }
            else request.setAttribute("editStatus", "errorUpdate");
            response.sendRedirect("showProfile");
        }


    }

    private void acceptSpeakerFromModer(HttpServletRequest request, HttpServletResponse response) {
        int report_id = Integer.parseInt(request.getParameter("report_id"));
        int speaker_id = Integer.parseInt(request.getParameter("speaker_id"));
        Report_speakers report_speakers = new Report_speakers();
        report_speakers.setSpeaker_id(speaker_id);
        report_speakers.setReport_id(report_id);
        reportSpeakerService.saveWithProposalsDeletion(report_speakers);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rejectSpeakerFromModer(HttpServletRequest request, HttpServletResponse response) {
        int report_id = Integer.parseInt(request.getParameter("report_id"));
        int speaker_id = Integer.parseInt(request.getParameter("speaker_id"));
        Moderator_preposition moderator_preposition = new Moderator_preposition();
        moderator_preposition.setSpeaker_id(speaker_id);
        moderator_preposition.setReport_id(report_id);
        moderatorPrepositionService.deleteProposal(moderator_preposition);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rejFromSpeaker(HttpServletRequest request, HttpServletResponse response) {
        //delete from speaker_preposition where id
        Integer id = Integer.parseInt(request.getParameter("id"));
        speakerPrepositionService.deleteSpeakerPrepositionFromDB(id);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rejSpeakerReport(HttpServletRequest request, HttpServletResponse response) {
        //delete from report_preposition where speaker_id
        Integer id = Integer.parseInt(request.getParameter("id"));
        Report_preposition rp = new Report_preposition();
        rp.setId(id);
        reportPrepositionService.deleteReportPrepositionFromDB(rp);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateUserForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer user_id = Integer.valueOf(request.getParameter("user_id"));
            request.setAttribute("user_id", user_id);
            User u = userService.findUserById(user_id);
            request.setAttribute("user", u);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUserForm.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        Integer role_id = Integer.valueOf(request.getParameter("role_id"));
        String name = request.getParameter("user_name");
        String surname = request.getParameter("user_surname");
        String password = userService.findUserById(user_id).getUser_password();
        String phone = request.getParameter("user_phone");
        String email = request.getParameter("user_email");
        String user_photo_url = request.getParameter("user_photo_url");
        String location = request.getParameter("user_address");
        int cntEmails = 0;
        int cntPhones = 0;
        List<User> users = userService.findAllUsersInDB();
        try {
            cntEmails = userService.calculateRowsBy("user_email", email);
            for(int i = 0; i < users.size(); i++) {
                User usr = users.get(i);
                if(usr.getUser_email().equals(email) && usr.getId()==user_id) {
                    cntEmails = 0;
                    break;
                }
            }
            cntPhones = userService.calculateRowsBy("user_phone", phone);
            for(int i = 0; i < users.size(); i++) {
                User usr = users.get(i);
                if(usr.getUser_phone().equals(phone) && usr.getId()==user_id) {
                    cntPhones = 0;
                    break;
                }
            }
        } catch (DBException e) {
            //e.printStackTrace();
        }


        //empty
        if(name == null || name.equals("")) {
            request.setAttribute("editStatus", "emptyFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidName(name)) {
            request.setAttribute("editStatus", "invalidFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(surname == null || surname.equals("")) {
            request.setAttribute("editStatus", "emptyLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidName(surname)) {
            request.setAttribute("editStatus", "invalidLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(email == null || email.equals("")) {
            request.setAttribute("editStatus", "emptyEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidEmail(email)) {
            request.setAttribute("editStatus", "invalidEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(cntEmails!=0) {
            request.setAttribute("editStatus", "dublicateEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);

        }
        else if(phone == null || phone.equals("")) {
            request.setAttribute("editStatus", "emptyPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(!Validator.isValidPhone(phone)) {
            request.setAttribute("editStatus", "invalidPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        }
        else if(cntPhones!=0) {
            request.setAttribute("editStatus", "dublicatePhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);

        }
        else if(location == null || location.equals("")) {
            request.setAttribute("editStatus", "emptyAddress");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser");
            dispatcher.forward(request, response);
        } else {
            User newUser = new User();
            newUser.setId(user_id);
            newUser.setRole_id(role_id);
            newUser.setUser_name(name);
            newUser.setUser_surname(surname);
            newUser.setUser_password(password);
            newUser.setUser_phone(phone);
            newUser.setUser_email(email);
            newUser.setUser_photo_url(user_photo_url);
            newUser.setUser_address(location);
            boolean isUpdated = userService.updateUserInDB(newUser);
            if(isUpdated) {
                request.getSession().setAttribute("email", email);
                request.setAttribute("editStatus", "successUpdate");
            }
            else request.setAttribute("editStatus", "errorUpdate");
            response.sendRedirect("showProfile");
        }


    }

    private void deleteUserFromSite(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer user_id = Integer.valueOf(request.getParameter("user_id"));
            User u = userService.findUserById(user_id);
            userService.deleteUserFromDB(u);
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accSpeakerForReport(HttpServletRequest request, HttpServletResponse response) {
        //delete from moderator and speaker pres
        //insert into report_speaker
        int report_id = Integer.parseInt(request.getParameter("report_id"));
        int speaker_id = Integer.parseInt(request.getParameter("speaker_id"));
        Report_speakers report_speakers = new Report_speakers();
        report_speakers.setSpeaker_id(speaker_id);
        report_speakers.setReport_id(report_id);
        reportSpeakerService.saveWithProposalsDeletion(report_speakers);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void accSpeakerReports(HttpServletRequest request, HttpServletResponse response) {
        //delete from report_preps by id
        //add to report_speakers
        //add to reports
        Integer rid = Integer.parseInt(request.getParameter("id"));
        List<Report_preposition> allPreps = null;
        try {
            allPreps = reportPrepositionService.findAllReportPrepositionInDB();
        } catch (DBException e) {
            e.printStackTrace();
        }
        Report_preposition prep = null;
        for(int i = 0; i < allPreps.size(); i++) {
            if(allPreps.get(i).getId()==rid) {
                prep = allPreps.get(i);
                break;
            }
        }
        Integer id = prep.getId();
        Integer event_id = prep.getEvent_id();
        Integer report_id = 0;
        Integer speaker_id =  prep.getSpeaker_id();
        try {
           report_id = reportService.calculateReportsNumber()+1;
        } catch (DBException e) {
            e.printStackTrace();
        }
        String report_name_ua = prep.getReport_name_ua();
        String report_name_en = prep.getReport_name_en();

        reportPrepositionService.deleteReportPrepositionFromDB(prep);

        //add to reports
       Reports rep = new Reports();
       rep.setReport_id(report_id);
       rep.setEvent_id(event_id);
       rep.setReport_name_en(report_name_en);
       rep.setReport_name_ua(report_name_ua);
       reportService.addReportToDB(rep);
       //add to report_speaker
        try {
            Integer rsid = reportSpeakerService.calculateReportSpeakerNumber()+1;
            Report_speakers report_speakers = new Report_speakers();
            report_speakers.setId(rsid);
            report_speakers.setReport_id(report_id);
            report_speakers.setSpeaker_id(speaker_id);
            reportSpeakerService.addReportSpeakersToDB(report_speakers);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
