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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/showProfile", "/editInfo", "/deleteUser", "/editUser"})
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
        }
    }

    private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
        User u = userService.findUserByEmail((String) request.getSession().getAttribute("email"));
        String name = u.getUser_name();
        String surname = u.getUser_surname();
        String phone = u.getUser_phone();
        String email = u.getUser_email();
        String location = u.getUser_address();
        request.setAttribute("name", name);
        request.setAttribute("surname", surname);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("location", location);
        if(request.getSession().getAttribute("role_id").equals("1")) {
            //for moderator
        } else if(request.getSession().getAttribute("role_id").equals("2")) {
            //for speaker
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

}
