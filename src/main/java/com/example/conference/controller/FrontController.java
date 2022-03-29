package com.example.conference.controller;

import com.example.conference.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/showProfile", "/editInfo", "/deleteUser", "/editUser", "/updateUser", "/rejectSpeakerFromModerator", "/acceptSpeakerFromModerator", "/rejectSpeakerForReport", "/rejectSpeakerReports", "/acceptSpeakerForReport", "/acceptSpeakerReports", "/newReport","/deleteReport", "/editReport", "/updateReport", "/listReport", "/proposeReport", "/addReportPropose", "/addReport", "/insertReport", "/registerUser", "/excludeUser", "/newEvent","/insertEvent","/deleteEvent", "/editEvent", "/updateEvent", "/listEvent", "/eventEvent", "/proposeMe", "/editPresence", "/setPresence", "/setPreposition", "/proposeSpeaker", "/lang", "/login", "/logout", "/register"})
public class FrontController extends HttpServlet {
    private static final Logger log = Logger.getLogger(FrontController.class);
    private CommandResolver commandResolver = CommandResolver.getInstance();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if(action.startsWith("/")) action = action.substring(1);
       if(commandResolver.getCommand(action) != null) {
           log.info("start command");
           ICommand command = commandResolver.getCommand(action);
           command.execute(req, resp);
           log.info("end command");
       }
    }
}
