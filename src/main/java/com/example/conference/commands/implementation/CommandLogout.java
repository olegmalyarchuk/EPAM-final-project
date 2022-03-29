package com.example.conference.commands.implementation;

import com.example.conference.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogout implements ICommand {
    private static final Logger log = Logger.getLogger(CommandLogout.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = null;
        HttpSession session = req.getSession();
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("status");
        String lang = "en";
        if(session.getAttribute("lang").equals("ua")) lang = "ua";
        session.invalidate();
        dispatcher = req.getRequestDispatcher("/login.jsp");
        req.setAttribute("status", "logout");
        log.info("logged out");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
