package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLang implements ICommand {
    private static final Logger log = Logger.getLogger(CommandLang.class);
    IEventService service = ServiceFactory.getInstance().getEventService();
    IUserService userService = ServiceFactory.getInstance().getUserService();
    IReportService reportService = ServiceFactory.getInstance().getReportService();
    IReportSpeakerService reportSpeakerService = ServiceFactory.getInstance().getReportSpeakerService();
    IEventUsersService eventUsersService = ServiceFactory.getInstance().getEventUsersService();;
    ISpeakerPrepositionService speakerPrepositionService = ServiceFactory.getInstance().getSpeakerPrepositionService();
    IModeratorPrepositionService moderatorPrepositionService = ServiceFactory.getInstance().getModeratorPrepositionService();;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = "en";
        if(request.getParameter("lang").equals("ua")) lang = "ua";
        request.getSession().setAttribute("lang", lang);
        try {
            response.sendRedirect("/listEvent");
        } catch (IOException e) {
            log.error(e);
        }
    }
}
