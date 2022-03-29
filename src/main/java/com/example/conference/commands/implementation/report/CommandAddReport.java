package com.example.conference.commands.implementation.report;

import com.example.conference.commands.ICommand;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddReport implements ICommand {
    private static final Logger log = Logger.getLogger(CommandAddReport.class);
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
            String event_id = request.getParameter("event_id");
            Integer report_id = reportService.calculateReportsNumber()+1;
            request.setAttribute("report_id", report_id);
            request.setAttribute("event_id", event_id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reportAdd_form.jsp");
            dispatcher.forward(request, response);
        }  catch (IOException e) {
            log.error(e);
        } catch (ServletException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }
}
