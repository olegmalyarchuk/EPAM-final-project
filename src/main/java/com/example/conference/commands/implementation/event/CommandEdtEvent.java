package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Events;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandEdtEvent implements ICommand {
    private static final Logger log = Logger.getLogger(CommandEdtEvent.class);
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();;
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Events event = service.findEventsById(id);
            request.setAttribute("event", event);
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_form.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }
}
