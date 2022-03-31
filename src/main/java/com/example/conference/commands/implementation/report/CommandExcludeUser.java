package com.example.conference.commands.implementation.report;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandExcludeUser implements ICommand {
    private static final Logger log = Logger.getLogger(CommandExcludeUser.class);
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
            Integer id = eventUsersService.calculateEventUsersNumber()+1;
            String email = request.getParameter("email");
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            User user = userService.findUserByEmail(email);
            Integer user_id = user.getId();
            Event_users event_users = new Event_users();
            event_users.setId(id);
            event_users.setUser(user);
            event_users.setUser_id(user_id);
            event_users.setEvent_id(event_id);
            event_users.setPresent(false);
            eventUsersService.deleteEventUsersFromDB(event_users);
            response.sendRedirect("/listEvent");
        }  catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }
}
