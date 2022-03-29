package com.example.conference.commands.implementation.profile;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.User;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteUser implements ICommand {
    private static final Logger log = Logger.getLogger(CommandDeleteUser.class);
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
            Integer user_id = Integer.valueOf(request.getParameter("user_id"));
            User u = userService.findUserById(user_id);
            userService.deleteUserFromDB(u);
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            log.error(e);
        }
    }
}
