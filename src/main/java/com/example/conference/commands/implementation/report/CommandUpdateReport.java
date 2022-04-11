package com.example.conference.commands.implementation.report;

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

public class CommandUpdateReport implements ICommand {
    private static final Logger log = Logger.getLogger(CommandUpdateReport.class);
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
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer event_id =  Integer.valueOf(request.getParameter("event_id"));
            String report_name_ua = request.getParameter("report_name_ua");
            String report_name_en = request.getParameter("report_name_en");
            Reports reports = new Reports();
            reports.setReport_id(report_id);
            reports.setEvent_id(event_id);
            reports.setReport_name_ua(report_name_ua);
            reports.setReport_name_en(report_name_en);
            reportService.updateReportInDB(reports);
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
