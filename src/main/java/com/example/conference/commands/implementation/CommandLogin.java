package com.example.conference.commands.implementation;

import com.example.conference.commands.ICommand;
import com.example.conference.cryptor.PasswordCryptor;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IUserService;
import com.example.conference.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class CommandLogin implements ICommand {
    private static final Logger log = Logger.getLogger(CommandLogin.class);
    IUserService service = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = null;
        service.findUserByEmail(email);
        int isRegistered = 0;
        try {
            isRegistered = service.calculateRowsBy("user_email", email);
        } catch (DBException e) {
            log.error(e);
        }
        if(isRegistered!=0) {
            //registered
            //check password
            User u = service.findUserByEmail(email);
            String userPass = u.getUser_password();
            boolean isMatches = false;
            try {
                isMatches = PasswordCryptor.validatePassword(password, userPass);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            if(isMatches) {
                //success
                session.setAttribute("email", email);
                session.setAttribute("name", u.getUser_name());
                session.setAttribute("surname", u.getUser_surname());
                session.setAttribute("role_id", u.getRole_id());
                session.setAttribute("status", "successLogin");
                session.setAttribute("user_id", u.getId());
                session.setAttribute("lang", "en");
                //  req.setAttribute("status", "successLogin");
                try {
                    resp.sendRedirect("listEvent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // dispatcher = req.getRequestDispatcher("/listEvent");
//                req.setAttribute("status", "successLogin");
                log.info("logged in");
            } else {
                //wrong passowrd
                req.setAttribute("status", "wrongPass");
                dispatcher = req.getRequestDispatcher("login.jsp");
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //user is not registered
            req.setAttribute("status", "unexistedEmail");
            dispatcher = req.getRequestDispatcher("login.jsp");
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
