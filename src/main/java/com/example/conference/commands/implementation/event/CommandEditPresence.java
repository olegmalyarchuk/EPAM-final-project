package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Event_users;
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

public class CommandEditPresence implements ICommand {
    private static final Logger log = Logger.getLogger(CommandEditPresence.class);
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
            Integer page = 1;
            if(request.getParameter("page")!=null) page = Integer.valueOf(request.getParameter("page"));
            Integer event_id = Integer.valueOf(request.getParameter("event_id"));
            List<Event_users> ev = eventUsersService.findAllEventUsersInDB();
            List<Event_users> event_users = new ArrayList<>();


            for(int i = 0; i < ev.size(); i++) {
                if(ev.get(i).getEvent_id()==event_id) {
                    event_users.add(ev.get(i));
                }
            }

            //pagination
            Integer perPage = 3;
            int cntPages = event_users.size()/perPage;
            if(event_users.size()%perPage!=0) cntPages++;
            boolean prevDisabled = false;
            boolean nextDisabled = false;
            if(page==1) prevDisabled=true;
            if(page==cntPages) nextDisabled = true;
            if(page<1) page = 1;
            if(page>cntPages) page = cntPages;
            Integer start = (page-1)*perPage;
            Integer end = start+perPage;
            if(start>event_users.size()) {
                start = 0;
                end = 0;
            }
            if(end>event_users.size()) end = event_users.size();

            List<Event_users> paginatedList = new ArrayList<>();
            for(int i = start; i < end; i++) {
                paginatedList.add(event_users.get(i));
            }

            request.setAttribute("page", page);
            request.setAttribute("prevDisabled", prevDisabled);
            request.setAttribute("nextDisabled", nextDisabled);
            request.setAttribute("event_users", paginatedList);
            request.setAttribute("event_id", event_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editPresence.jsp");
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
