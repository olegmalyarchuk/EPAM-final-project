package com.example.conference.commands.implementation.profile;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Report_speakers;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandAcceptSpeakerFromModerator implements ICommand {
    private static final Logger log = Logger.getLogger(CommandAcceptSpeakerFromModerator.class);
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
        int report_id = Integer.parseInt(request.getParameter("report_id"));
        int speaker_id = Integer.parseInt(request.getParameter("speaker_id"));
        Report_speakers report_speakers = new Report_speakers();
        report_speakers.setSpeaker_id(speaker_id);
        report_speakers.setReport_id(report_id);
        reportSpeakerService.saveWithProposalsDeletion(report_speakers);
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            log.error(e);
        }
    }
}
