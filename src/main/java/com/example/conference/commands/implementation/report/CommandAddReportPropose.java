package com.example.conference.commands.implementation.report;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Report_preposition;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAddReportPropose implements ICommand {
    private static final Logger log = Logger.getLogger(CommandAddReportPropose.class);
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
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer event_id = Integer.valueOf(request.getParameter("event_id"));
        Integer speaker_id = Integer.valueOf(request.getParameter("speaker_id"));
        String report_name_ua = request.getParameter("report_name_ua");
        String report_name_en = request.getParameter("report_name_en");
        Report_preposition rp = new Report_preposition();
        rp.setId(id);
        rp.setEvent_id(event_id);
        rp.setSpeaker_id(speaker_id);
        rp.setReport_name_ua(report_name_ua);
        rp.setReport_name_en(report_name_en);
        reportPrepositionService.addReportPrepositionToDB(rp);
        try {
            response.sendRedirect("listEvent");
        } catch (IOException e) {
            log.error(e);
        }

    }
}
