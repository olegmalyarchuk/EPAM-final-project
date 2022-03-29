package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandEventEvent implements ICommand {
    private static final Logger log = Logger.getLogger(CommandEventEvent.class);
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
            int event_id = Integer.parseInt(request.getParameter("id"));
            Events events = service.findEventsById(event_id);
            int cntRegistered = service.calculateRegistered(events.getEvent_id());
            int cntPresent = service.calculatePresent(events.getEvent_id());
            cntRegistered += cntPresent;
            List<Reports> reports = reportService.findReportsByEvent(events);
            List<User> speakers = new ArrayList<>();
            for(int i = 0; i < reports.size(); i++) {
                int report_id = reports.get(i).getReport_id();
                //find speaker
                Report_speakers report_speakers = reportSpeakerService.findReportById(report_id);
                if(report_speakers==null) {
                    User u = new User();
                    u.setUser_surname("null");
                    u.setUser_name("null");
                    speakers.add(u);
                } else {
                    int speaker_id = report_speakers.getSpeaker_id();
                    User u = userService.findUserById(speaker_id);
                    speakers.add(u);
                }

            }
            boolean isRegister = false;
            String email = (String) request.getSession().getAttribute("email");
            User u = userService.findUserByEmail(email);
            int user_id = u.getId();
            List<Event_users>  eventUsers = eventUsersService.findAllEventUsersInDB();
            for(int i = 0; i < eventUsers.size(); i++) {
                Event_users eu = eventUsers.get(i);
                if(eu.getUser_id()==user_id && eu.getEvent_id()==event_id) {
                    isRegister = true;
                    break;
                }
            }
            request.setAttribute("event", events);
            request.setAttribute("reports", reports);
            request.setAttribute("speakers", speakers);
            request.setAttribute("registered", cntRegistered);
            request.setAttribute("present", cntPresent);
            if(isRegister) request.setAttribute("isRegister", "yes");
            else request.setAttribute("isRegister", "no");
            RequestDispatcher dispatcher = request.getRequestDispatcher("event.jsp");
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
