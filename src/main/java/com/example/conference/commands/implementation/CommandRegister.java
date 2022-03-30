package com.example.conference.commands.implementation;

import com.example.conference.commands.ICommand;
import com.example.conference.cryptor.PasswordCryptor;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.IUserService;
import com.example.conference.service.ServiceFactory;
import com.example.conference.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class CommandRegister implements ICommand {
    private static final Logger log = Logger.getLogger(CommandLogin.class);
    IUserService service = ServiceFactory.getInstance().getUserService();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = service.calculateUsersNumber()+1;
        Integer role_id = 3;
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String password = req.getParameter("password");
        String confirmpassword = req.getParameter("confirmpassword");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String photo = "user.jpg";
        String address = req.getParameter("address");
        RequestDispatcher dispatcher = null;
        int cntEmails = 0;
        int cntPhones = 0;
        try {
            cntEmails = service.calculateRowsBy("user_email", email);
            cntPhones = service.calculateRowsBy("user_phone", phone);
        } catch (DBException e) {
            log.error(e);
        }

        //empty
        if(firstName == null || firstName.equals("")) {
            req.setAttribute("status", "emptyFirstname");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidName(firstName)) {
            req.setAttribute("status", "invalidFirstname");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(lastName == null || lastName.equals("")) {
            req.setAttribute("status", "emptyLastname");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidName(lastName)) {
            req.setAttribute("status", "invalidLastname");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(password == null || password.equals("")) {
            req.setAttribute("status", "emptyPassword");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidPassword(password)) {
            req.setAttribute("status", "invalidPassword");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!confirmpassword.equals(password)) {
            req.setAttribute("status", "invalidConfirmpassword");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(email == null || email.equals("")) {
            req.setAttribute("status", "emptyEmail");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidEmail(email)) {
            req.setAttribute("status", "invalidEmail");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(cntEmails!=0) {
            req.setAttribute("status", "dublicateEmail");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(phone == null || phone.equals("")) {
            req.setAttribute("status", "emptyPhone");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(!Validator.isValidPhone(phone)) {
            req.setAttribute("status", "invalidPhone");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(cntPhones!=0) {
            req.setAttribute("status", "dublicatePhone");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(address == null || address.equals("")) {
            req.setAttribute("status", "emptyAddress");
            dispatcher = req.getRequestDispatcher("register.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            User newUser = new User();
            newUser.setId(id);
            newUser.setRole_id(role_id);
            newUser.setUser_name(firstName);
            newUser.setUser_surname(lastName);
            //crypting password
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
            newUser.setUser_photo_url(photo);
            newUser.setUser_address(address);

            if (service.addUserToDB(newUser)) {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                session.setAttribute("name", newUser.getUser_name());
                session.setAttribute("role_id", newUser.getRole_id());
                session.setAttribute("status", "successRegister");
                session.setAttribute("lang", "en");
                dispatcher = req.getRequestDispatcher("/listEvent");
                req.setAttribute("status", "successRegister");
                String lang = "en";
                if(req.getSession().getAttribute("lang").equals("ua")) lang = "ua";
                GmailSender.sendWelcome(email, newUser.getUser_name(), newUser.getUser_surname(), lang);
                try {
                    resp.sendRedirect("/listEvent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //dispatcher.forward(req, resp);

            } else {
                req.setAttribute("status", "servererror");
                dispatcher = req.getRequestDispatcher("register.jsp");
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
