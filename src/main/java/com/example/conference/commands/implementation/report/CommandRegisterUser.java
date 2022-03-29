package com.example.conference.commands.implementation.report;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Events;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandRegisterUser implements ICommand {
    private static final Logger log = Logger.getLogger(CommandRegisterUser.class);
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
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "ua;";
            if(lang.equals("ua"))  GmailSender.sendEventWelcome(email, user.getUser_name(), user.getUser_surname(), events.getEvent_place_ua(), events.getEvent_date(), lang);
            else GmailSender.sendEventWelcome(email, user.getUser_name(), user.getUser_surname(), events.getEvent_place_en(), events.getEvent_date(), lang);
            response.sendRedirect("listEvent");
        } catch (DBException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
