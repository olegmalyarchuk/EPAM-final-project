package com.example.conference.controller;

import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IUserService;
import com.example.conference.service.ServiceFactory;
import com.example.conference.validator.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static final long serialVersionUID = 123488252L;
    IUserService service = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = null;
        service.findUserByEmail(email);
        int isRegistered = 0;
        try {
            isRegistered = service.calculateRowsBy("user_email", email);
        } catch (DBException e) {
            //e.printStackTrace();
        }
        if(isRegistered!=0) {
            //registered
            //check password
            User u = service.findUserByEmail(email);
            String userPass = u.getUser_password();
            if(userPass.equals(password)) {
                //success
                session.setAttribute("email", email);
                session.setAttribute("name", u.getUser_name());
                session.setAttribute("role_id", u.getRole_id());
                dispatcher = req.getRequestDispatcher("/main.jsp");
                req.setAttribute("status", "successLogin");
            } else {
                //wrong passowrd
                req.setAttribute("status", "wrongPass");
                dispatcher = req.getRequestDispatcher("login.jsp");
            }
        } else {
            //user is not registered
            req.setAttribute("status", "unexistedEmail");
            dispatcher = req.getRequestDispatcher("login.jsp");
        }
        dispatcher.forward(req, resp);
    }

}
