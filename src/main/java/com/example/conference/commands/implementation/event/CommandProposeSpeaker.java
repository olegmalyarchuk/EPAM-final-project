package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.User;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandProposeSpeaker implements ICommand {
    private static final Logger log = Logger.getLogger(CommandProposeSpeaker.class);
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
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            List<User> users = userService.findAllUsersInDB();
            List<User> speakers = new ArrayList<>();
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getRole_id()==2) {
                    speakers.add(users.get(i));
                }
            }
            request.setAttribute("speakers", speakers);
            request.setAttribute("report_id", report_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("moderatorPrepositionForm.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
