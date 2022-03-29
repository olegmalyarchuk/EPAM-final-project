package com.example.conference.commands.implementation.profile;

import com.example.conference.commands.ICommand;
import com.example.conference.commands.implementation.event.CommandNewEvent;
import com.example.conference.entity.*;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandShowProfile implements ICommand {
    private static final Logger log = Logger.getLogger(CommandShowProfile.class);
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
        HttpSession session = request.getSession();
        User u = userService.findUserByEmail((String) session.getAttribute("email"));
        String name = u.getUser_name();
        String surname = u.getUser_surname();
        String phone = u.getUser_phone();
        String email = u.getUser_email();
        String location = u.getUser_address();
        Integer role_id = (Integer) session.getAttribute("role_id");
        request.setAttribute("name", name);
        request.setAttribute("surname", surname);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("location", location);
        if(role_id==1) {


            String userStatus = "all";
            if(request.getParameter("userStatus") != null) userStatus = request.getParameter("userStatus");
            request.setAttribute("userStatus", userStatus);
            List<User> allUsers = userService.findAllUsersInDB();
            List<User> users = new ArrayList<>();
            for(int i = 0; i < allUsers.size(); i++) {
                if( (userStatus.equals("users") && allUsers.get(i).getRole_id()==3) || (userStatus.equals("speakers") && allUsers.get(i).getRole_id()==2) || userStatus.equals("all")) {
                    users.add(allUsers.get(i));
                }
            }
            request.setAttribute("users", users);

            String prepositionStatus = "fromModerator";
            if(request.getParameter("prepositionStatus") != null) prepositionStatus = request.getParameter("prepositionStatus");
            request.setAttribute("prepositionStatus", prepositionStatus);
            //fromModer
            if(prepositionStatus.equals("fromModerator")) {
                try {
                    List<Moderator_preposition> allModeratorPreps = moderatorPrepositionService.findAllModeratorPrepositionInDB();
                    List<Reports> moderatorReports = new ArrayList<>();
                    List<Events> fromModeratorEvents = new ArrayList<>();
                    for(int i = 0; i < allModeratorPreps.size(); i++) {
                        Integer report_id = allModeratorPreps.get(i).getReport_id();
                        Reports rep = reportService.findReportsByReportId(report_id);
                        moderatorReports.add(rep);
                        Integer event_id = rep.getEvent_id();
                        Events event = service.findEventsById(event_id);
                        fromModeratorEvents.add(event);

                    }


                    request.setAttribute("propEvents", fromModeratorEvents);
                    request.setAttribute("propReports", moderatorReports);
                    request.setAttribute("Preps", allModeratorPreps);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("fromSpeaker")) {
                try {
                    List<Speaker_preposition> allSpeakerPreps = speakerPrepositionService.findAllSpeakerPrepositionInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromSpeakerEvents = new ArrayList<>();
                    for(int i = 0; i < allSpeakerPreps.size(); i++) {
                        Integer report_id = allSpeakerPreps.get(i).getReport_id();
                        Reports rep = reportService.findReportsByReportId(report_id);
                        speakerReports.add(rep);
                        Integer event_id = rep.getEvent_id();
                        Events event = service.findEventsById(event_id);
                        fromSpeakerEvents.add(event);

                    }


                    request.setAttribute("propEvents", fromSpeakerEvents);
                    request.setAttribute("propReports", speakerReports);
                    request.setAttribute("Preps", allSpeakerPreps);
                } catch (DBException e) {
                    log.error(e);
                }
            } else if(prepositionStatus.equals("forEvent")) {
                try {
                    List<Report_preposition> allReportPreps = reportPrepositionService.findAllReportPrepositionInDB();

                    List<Events> events = new ArrayList<>();
                    List<Report_preposition> forReports = new ArrayList<>();
                    for (int i = 0; i < allReportPreps.size(); i++) {
                        forReports.add(allReportPreps.get(i));
                        Integer event_id = allReportPreps.get(i).getEvent_id();
                        Events events1 = service.findEventsById(event_id);
                        events.add(events1);
                    }
                    request.setAttribute("propEvents", events);
                    request.setAttribute("propReports", forReports);
                    request.setAttribute("Preps", allReportPreps);
                } catch (DBException e) {
                    log.error(e);
                }
            }



        } else if(role_id==2) {


            Integer speaker_id = u.getId();
            request.setAttribute("speaker_id", speaker_id);
            String eventStatus = "all";
            if(request.getParameter("eventStatus") != null) eventStatus = request.getParameter("eventStatus");
            request.setAttribute("eventStatus", eventStatus);
            try {
                List<Report_speakers> event_users = reportSpeakerService.findAllReportSpeakersInDB();
                List<Integer> speakerReportIds = new ArrayList<>();
                //find report_id with speaker id
                for(int i = 0; i < event_users.size(); i++) {
                    if(event_users.get(i).getSpeaker_id()==speaker_id) {
                        Integer report_id = event_users.get(i).getReport_id();
                        speakerReportIds.add(report_id);
                    }
                }

                List<Reports> allReports = reportService.findAllReportsInDB();
                List<Events> speakerEvents = new ArrayList<>();
                for(int i = 0; i < allReports.size(); i++) {
                    if(speakerReportIds.contains(allReports.get(i).getReport_id())) {
                        Events speakerEvent = service.findEventsById(allReports.get(i).getEvent_id());
                        if((eventStatus.equals("finished") && speakerEvent.isFinished())
                                || (eventStatus.equals("upcoming") && !speakerEvent.isFinished()) || (eventStatus.equals("all"))) {
                            speakerEvents.add(speakerEvent);
                        }
                    }
                }
                request.setAttribute("events", speakerEvents);
            } catch (DBException e) {
                e.printStackTrace();
            }
            String prepositionStatus = "fromModerator";
            if(request.getParameter("prepositionStatus") != null) prepositionStatus = request.getParameter("prepositionStatus");
            request.setAttribute("prepositionStatus", prepositionStatus);
            //fromModer
            if(prepositionStatus.equals("fromModerator")) {
                try {
                    List<Moderator_preposition> allModeratorPreps = moderatorPrepositionService.findAllModeratorPrepositionInDB();
                    List<Integer> fromModeratorReportsId = new ArrayList<>();
                    List<Moderator_preposition> moderatorPrepsId = new ArrayList<>();
                    for (int i = 0; i < allModeratorPreps.size(); i++) {
                        if (allModeratorPreps.get(i).getSpeaker_id() == speaker_id) {
                            fromModeratorReportsId.add(allModeratorPreps.get(i).getReport_id());
                            moderatorPrepsId.add(allModeratorPreps.get(i));
                        }
                    }
                    List<Reports> allReports = reportService.findAllReportsInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromModeratorEvents = new ArrayList<>();
                    List<Moderator_preposition> moderatorPreps = new ArrayList<>();
                    for (int i = 0; i < allReports.size(); i++) {
                        if (fromModeratorReportsId.contains(allReports.get(i).getReport_id())) {
                            Integer event_id = allReports.get(i).getEvent_id();
                            Events events = service.findEventsById(event_id);
                            fromModeratorEvents.add(events);
                            speakerReports.add(allReports.get(i));
                            reportService.findReportsByEvent(events);
                        }
                    }
                    request.setAttribute("propEvents", fromModeratorEvents);
                    request.setAttribute("propReports", speakerReports);
                    request.setAttribute("moderatorPreps", moderatorPrepsId);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            } else if(prepositionStatus.equals("fromSpeaker")) {
                try {
                    List<Speaker_preposition> allSpeakerPreps = speakerPrepositionService.findAllSpeakerPrepositionInDB();
                    List<Speaker_preposition> speakerPreps = new ArrayList<>();
                    List<Integer> fromSpeakerReportsId = new ArrayList<>();
                    for (int i = 0; i < allSpeakerPreps.size(); i++) {
                        if (allSpeakerPreps.get(i).getSpeaker_id() == speaker_id) {
                            fromSpeakerReportsId.add(allSpeakerPreps.get(i).getReport_id());
                            speakerPreps.add(allSpeakerPreps.get(i));
                        }
                    }
                    List<Reports> allReports = reportService.findAllReportsInDB();
                    List<Reports> speakerReports = new ArrayList<>();
                    List<Events> fromSpeakerEvents = new ArrayList<>();
                    for (int i = 0; i < allReports.size(); i++) {
                        if (fromSpeakerReportsId.contains(allReports.get(i).getReport_id())) {
                            Integer event_id = allReports.get(i).getEvent_id();
                            Events events = service.findEventsById(event_id);
                            fromSpeakerEvents.add(events);
                            speakerReports.add(allReports.get(i));
                            reportService.findReportsByEvent(events);
                        }
                    }
                    request.setAttribute("propEvents", fromSpeakerEvents);
                    request.setAttribute("propReports", speakerReports);
                    request.setAttribute("speakerPreps", speakerPreps);
                } catch (DBException e) {
                    log.error(e);
                }
            } else if(prepositionStatus.equals("forEvent")) {
                try {
                    List<Report_preposition> allReportPreps = reportPrepositionService.findAllReportPrepositionInDB();

                    List<Events> events = new ArrayList<>();
                    List<Report_preposition> forReports = new ArrayList<>();
                    for (int i = 0; i < allReportPreps.size(); i++) {
                        if (allReportPreps.get(i).getSpeaker_id() == speaker_id) {
                            forReports.add(allReportPreps.get(i));
                            Integer event_id = allReportPreps.get(i).getEvent_id();
                            Events events1 = service.findEventsById(event_id);
                            events.add(events1);
                        }
                    }
                    request.setAttribute("propEvents", events);
                    request.setAttribute("propReports", forReports);
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }


        } else {


            //for user
            String eventStatus = "all";
            if(request.getParameter("eventStatus") != null) eventStatus = request.getParameter("eventStatus");
            request.setAttribute("eventStatus", eventStatus);
            try {
                List<Events> eventsRaw = service.findAllEventsInDB();
                List<Events> events = new ArrayList<>();
                for(int i = 0; i < eventsRaw.size(); i++) {
                    Event_users event_users = eventUsersService.findByUserIdAndEventId(u.getId(), eventsRaw.get(i).getEvent_id());
                    if(event_users==null) continue;
                    if(eventStatus.equals("finished")) {
                        if(eventsRaw.get(i).isFinished()) events.add(eventsRaw.get(i));
                    } else if(eventStatus.equals("upcoming")) {
                        if(eventsRaw.get(i).isFinished()) events.add(eventsRaw.get(i));
                    } else if(eventStatus.equals("all")) {
                        events.add(eventsRaw.get(i));
                    }
                }
                List<String> presense = new ArrayList<>();
                for(int i = 0; i < events.size(); i++) {
                    Integer ev_id = events.get(i).getEvent_id();
                    Event_users event_users = eventUsersService.findByUserIdAndEventId(u.getId(), ev_id);
                    if(event_users==null) continue;
                    if(event_users.isPresent()) presense.add("Present");
                    else presense.add("Registered");
                }
                request.setAttribute("events", events);
                request.setAttribute("presense", presense);
            } catch (DBException e) {
                log.error(e);
            }



        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
