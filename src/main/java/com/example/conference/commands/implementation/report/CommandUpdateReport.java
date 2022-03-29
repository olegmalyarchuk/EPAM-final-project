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
            dataforMailUpdate(event_id, lang);
            response.sendRedirect("listEvent");
        } catch (IOException | DBException e) {
            log.error(e);
        }
    }

    private void dataforMailUpdate(int event_id, String lang) throws DBException {
        int id = event_id;
        List<User> userList = new ArrayList<>();
        List<Report_speakers> rs = reportSpeakerService.findAllReportSpeakersInDB();
        List<Event_users> ev = eventUsersService.findAllEventUsersInDB();
        for(int i = 0; i < ev.size(); i++) {
            if(ev.get(i).getEvent_id()==id) {
                Integer user_id = ev.get(i).getUser_id();
                User u = userService.findUserById(user_id);
                userList.add(u);
            }
        }
        for(int i = 0; i < rs.size(); i++) {
            Report_speakers report_speakers = rs.get(i);
            Integer speaker_id = report_speakers.getSpeaker_id();
            Integer report_id = report_speakers.getReport_id();
            List<Reports> reports = reportService.findAllReportsInDB();
            for(int j = 0; j < reports.size(); j++) {
                if(reports.get(j).getEvent_id()==id) {
                    User u = userService.findUserById(speaker_id);
                    userList.add(u);
                    break;
                }
            }
        }
        String event_name = service.findEventsById(id).getEvent_name_en();
        GmailSender.sendEventChange(userList, event_name, lang);
    }
}
