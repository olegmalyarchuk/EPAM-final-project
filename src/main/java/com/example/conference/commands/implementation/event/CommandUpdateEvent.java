package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.mail.GmailSender;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandUpdateEvent implements ICommand {
    private static final Logger log = Logger.getLogger(CommandUpdateEvent.class);
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
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            String event_name_ua = request.getParameter("event_name_ua");
            String event_name_en = request.getParameter("event_name_en");
            String event_place_ua = request.getParameter("event_place_ua");
            String event_place_en = request.getParameter("event_place_en");
            String event_description_ua = request.getParameter("event_description_ua");
            String event_description_en = request.getParameter("event_description_en");
            LocalDateTime event_date = LocalDateTime.parse(request.getParameter("event_date"));
            String event_photo_url = request.getParameter("event_photo_url");
            Events events = new Events();
            events.setEvent_id(event_id);
            events.setEvent_name_ua(event_name_ua);
            events.setEvent_name_en(event_name_en);
            events.setEvent_place_ua(event_place_ua);
            events.setEvent_place_en(event_place_en);
            events.setEvent_description_ua(event_description_ua);
            events.setEvent_description_en(event_description_en);
            events.setEvent_date(event_date);
            events.setEvent_photo_url(event_photo_url);
            boolean isUpdated = service.updateEventsInDB(events);
            if(isUpdated) request.setAttribute("status", "successUpdate");
            else request.setAttribute("status", "errorUpdate");
            String lang = "en";
            if(request.getSession().getAttribute("lang").equals("ua")) lang = "ua";
            List<User> userList = userService.findByEventId(event_id);
            String event_name = service.findEventsById(event_id).getEvent_name_en();
            GmailSender.sendEventChange(userList, event_name, lang);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            log.error(e);
        }
    }

}
