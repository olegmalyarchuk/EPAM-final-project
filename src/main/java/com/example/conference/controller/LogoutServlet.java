package com.example.conference.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    public static final long serialVersionUID = 123488652L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        HttpSession session = req.getSession();
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.invalidate();
        dispatcher = req.getRequestDispatcher("/login.jsp");
        req.setAttribute("status", "logout");
        dispatcher.forward(req, resp);
    }
}
