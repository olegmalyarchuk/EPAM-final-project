package com.example.conference.commands.implementation.profile;

import com.example.conference.commands.ICommand;
import com.example.conference.cryptor.PasswordCryptor;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import com.example.conference.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class CommandEditInfo implements ICommand {
    private static final Logger log = Logger.getLogger(CommandEditInfo.class);
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IReportPrepositionService reportPrepositionService = ServiceFactory.getInstance().getReportPrepositionService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
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
            log.error(e);
        }

        //empty
        if(name == null || name.equals("")) {
            request.setAttribute("editStatus", "emptyFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidName(name)) {
            request.setAttribute("editStatus", "invalidFirstname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(surname == null || surname.equals("")) {
            request.setAttribute("editStatus", "emptyLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidName(surname)) {
            request.setAttribute("editStatus", "invalidLastname");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidPassword(password)) {
            request.setAttribute("editStatus", "invalidPassword");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(email == null || email.equals("")) {
            request.setAttribute("editStatus", "emptyEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidEmail(email)) {
            request.setAttribute("editStatus", "invalidEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(cntEmails!=0) {
            request.setAttribute("editStatus", "dublicateEmail");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(phone == null || phone.equals("")) {
            request.setAttribute("editStatus", "emptyPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidPhone(phone)) {
            request.setAttribute("editStatus", "invalidPhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(cntPhones!=0) {
            request.setAttribute("editStatus", "dublicatePhone");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(location == null || location.equals("")) {
            request.setAttribute("editStatus", "emptyAddress");
            RequestDispatcher dispatcher = request.getRequestDispatcher("showProfile");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            User newUser = new User();
            newUser.setId(user_id);
            newUser.setRole_id(role_id);
            newUser.setUser_name(name);
            newUser.setUser_surname(surname);
            String securedPassword = password;
            try {
                securedPassword = PasswordCryptor.generateStorngPasswordHash(password);
            } catch (NoSuchAlgorithmException e) {
                log.error(e);
            } catch (InvalidKeySpecException e) {
               log.error(e);
            }
            newUser.setUser_password(securedPassword);
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
            try {
                response.sendRedirect("showProfile");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
