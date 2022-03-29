package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.comparators.DateComparator;
import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.comparators.UsersCountComparator;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CommandListEvent implements ICommand {
    private static final Logger log = Logger.getLogger(CommandListEvent.class);
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
            String status = "all";
            if(request.getParameter("eventStatus") != null) status = request.getParameter("eventStatus");
            String orderBy = "default";
            if(request.getParameter("orderBy") != null) orderBy = request.getParameter("orderBy");
            List<Events> eventsList = service.findAllEventsInDB();
            for(int i = 0; i < eventsList.size(); i++) {
                int event_id = eventsList.get(i).getEvent_id();
                int cntRegistered = service.calculateRegistered(event_id);
                int cntPresent = service.calculatePresent(event_id);
                cntRegistered += cntPresent;
                eventsList.get(i).setRegisteredCount(cntRegistered);
                List<Reports> reports = reportService.findReportsByEvent(eventsList.get(i));
                int reportsCnt = reports.size();
                eventsList.get(i).setReportsCount(reports.size());
            }
            //sorting
            if(orderBy.equals("date")) {
                Collections.sort(eventsList, new DateComparator());
            } else if(orderBy.equals("reports")) {
                Collections.sort(eventsList, new ReportsCountComparator());
            } else if(orderBy.equals("users")) {
                Collections.sort(eventsList, new UsersCountComparator());
            }
            request.setAttribute("eventStatus", status);
            request.setAttribute("eventsList", eventsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("event_list.jsp");
            dispatcher.forward(request, response);
        } catch (DBException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
