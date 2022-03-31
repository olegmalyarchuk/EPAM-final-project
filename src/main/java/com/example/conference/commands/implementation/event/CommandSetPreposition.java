package com.example.conference.commands.implementation.event;

import com.example.conference.commands.ICommand;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSetPreposition implements ICommand {
    private static final Logger log = Logger.getLogger(CommandSetPreposition.class);
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
            Integer id = moderatorPrepositionService.calculateModeratorPrepositionNumber()+1;
            Integer report_id = Integer.valueOf(request.getParameter("report_id"));
            Integer speaker_id = Integer.valueOf(request.getParameter("speaker_id"));
            Moderator_preposition mp = new Moderator_preposition();
            mp.setId(id);
            mp.setReport_id(report_id);
            mp.setSpeaker_id(speaker_id);
            moderatorPrepositionService.addModeratorPrepositionToDB(mp);
            response.sendRedirect("proposeSpeaker?report_id="+report_id);
        } catch (DBException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
