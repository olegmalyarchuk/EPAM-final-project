package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Event_users;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSetPresence implements ICommand {
    private static final Logger log = Logger.getLogger(CommandSetPresence.class);
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();;
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Integer event_id = Integer.valueOf(request.getParameter("event_id"));
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        String presense = request.getParameter("presence");
        boolean isPresent = false;
        if(presense.equals("yes")) isPresent = true;
        Event_users ev = new Event_users();
        ev.setUser_id(user_id);
        ev.setEvent_id(event_id);
        ev.setPresent(isPresent);
        eventUsersService.updateUserPresenceByUserIdAndMeetingId(ev);
        try {
            response.sendRedirect("editPresence?event_id="+event_id);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
