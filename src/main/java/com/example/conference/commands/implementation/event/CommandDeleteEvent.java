package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Event_users;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandDeleteEvent implements ICommand {
    private static final Logger log = Logger.getLogger(CommandDeleteEvent.class);
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
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "ua";
            List<User> userList = userService.findByEventId(id);
            for(int i = 0; i < userList.size(); i++) {
                System.out.println(userList.get(i));
            }
            String event_name = service.findEventsById(id).getEvent_name_en();
            if(lang.equals("ua")) service.findEventsById(id).getEvent_name_ua();
            boolean isDeleted = service.deleteEventsByIdFromDB(id);
            if(isDeleted) request.setAttribute("status", "successDelete");
            else request.setAttribute("status", "errorDelete");
            GmailSender.sendEventChange(userList, event_name, lang);
            response.sendRedirect("/listEvent");
        } catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }

}
