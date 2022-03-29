package com.example.conference.commands.implementation.report;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandDeleteReport implements ICommand {
    private static final Logger log = Logger.getLogger(CommandDeleteReport.class);
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
            Reports r = new Reports();
            List<Reports> reports = reportService.findAllReportsInDB();
            List<Report_speakers> report_speakers = reportSpeakerService.findAllReportSpeakersInDB();
            List<Speaker_preposition> speakerPrepositions = speakerPrepositionService.findAllSpeakerPrepositionInDB();
            List<Moderator_preposition> moderator_prepositions = moderatorPrepositionService.findAllModeratorPrepositionInDB();
            for(int i = 0; i < report_speakers.size(); i++) {
                if(report_speakers.get(i).getReport_id()==report_id) {
                    reportSpeakerService.deleteReportSpeakersFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < speakerPrepositions.size(); i++) {
                if(speakerPrepositions.get(i).getReport_id()==report_id) {
                    speakerPrepositionService.deleteSpeakerPrepositionFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < moderator_prepositions.size(); i++) {
                if(moderator_prepositions.get(i).getReport_id()==report_id) {
                    moderatorPrepositionService.deleteModeratorPrepositionFromDB(report_id);
                    break;
                }
            }
            for(int i = 0; i < reports.size(); i++) {
                if(reports.get(i).getReport_id()==report_id) {
                    r = reports.get(i);
                }
            }
            reportService.deleteReportFromDB(r);
            response.sendRedirect("/listEvent");
        }  catch (IOException e) {
            log.error(e);
        } catch (DBException e) {
            log.error(e);
        }
    }
}
