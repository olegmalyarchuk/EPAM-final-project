package com.example.conference.commands.implementation.profile;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandAcceptSpeakerReports implements ICommand {
    private static final Logger log = Logger.getLogger(CommandAcceptSpeakerReports.class);
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
        //delete from report_preps by id
        //add to report_speakers
        //add to reports
        Integer rid = Integer.parseInt(request.getParameter("id"));
        List<Report_preposition> allPreps = null;
        try {
            allPreps = reportPrepositionService.findAllReportPrepositionInDB();
        } catch (DBException e) {
            e.printStackTrace();
        }
        Report_preposition prep = null;
        for(int i = 0; i < allPreps.size(); i++) {
            if(allPreps.get(i).getId()==rid) {
                prep = allPreps.get(i);
                break;
            }
        }
        Integer id = prep.getId();
        Integer event_id = prep.getEvent_id();
        Integer report_id = 0;
        Integer speaker_id =  prep.getSpeaker_id();
        try {
            report_id = reportService.calculateReportsNumber()+1;
        } catch (DBException e) {
            e.printStackTrace();
        }
        String report_name_ua = prep.getReport_name_ua();
        String report_name_en = prep.getReport_name_en();

        reportPrepositionService.deleteReportPrepositionFromDB(prep);

        //add to reports
        Reports rep = new Reports();
        rep.setReport_id(report_id);
        rep.setEvent_id(event_id);
        rep.setReport_name_en(report_name_en);
        rep.setReport_name_ua(report_name_ua);
        reportService.addReportToDB(rep);
        //add to report_speaker
        try {
            Integer rsid = reportSpeakerService.calculateReportSpeakerNumber()+1;
            Report_speakers report_speakers = new Report_speakers();
            report_speakers.setId(rsid);
            report_speakers.setReport_id(report_id);
            report_speakers.setSpeaker_id(speaker_id);
            reportSpeakerService.addReportSpeakersToDB(report_speakers);
        } catch (DBException e) {
            log.error(e);
        }
        try {
            response.sendRedirect("showProfile");
        } catch (IOException e) {
            log.error(e);
        }
    }
    }
