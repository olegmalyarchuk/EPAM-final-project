package com.example.conference.service;

import com.example.conference.service.implementation.*;

/**
 *
 *     Defines methods to instantiate services
 *
 *
 */
public class ServiceFactory {
    private static ServiceFactory instance;
    private IEventService eventService;
    private IEventUsersService eventUsersService;
    private IModeratorPrepositionService moderatorPrepositionService;
    private IReportPrepositionService reportPrepositionService;
    private IReportService reportService;
    private IReportSpeakerService reportSpeakerService;
    private ISpeakerPrepositionService speakerPrepositionService;
    private IUserRolesService userRolesService;
    private IUserService userService;

    private ServiceFactory() {}

    public static synchronized ServiceFactory getInstance() {
        if(instance==null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public synchronized IEventService getEventService() {
        if(eventService == null) {
            eventService = new EventService();
        }
        return eventService;
    }

    public synchronized IEventUsersService getEventUsersService() {
        if(eventUsersService == null) {
            eventUsersService = new EventUsersService();
        }
        return eventUsersService;
    }

    public synchronized IModeratorPrepositionService getModeratorPrepositionService() {
        if(moderatorPrepositionService == null) {
            moderatorPrepositionService = new ModeratorPrepositionService();
        }
        return moderatorPrepositionService;
    }

    public synchronized IReportPrepositionService getReportPrepositionService() {
        if(reportPrepositionService == null) {
            reportPrepositionService = new ReportPrepositionService();
        }
        return reportPrepositionService;
    }

    public synchronized IReportService getReportService() {
        if(reportService == null) {
            reportService = new ReportService();
        }
        return reportService;
    }

    public synchronized IReportSpeakerService getReportSpeakerService() {
        if(reportSpeakerService == null) {
            reportSpeakerService = new ReportSpeakerService();
        }
        return reportSpeakerService;
    }

    public synchronized ISpeakerPrepositionService getSpeakerPrepositionService() {
        if(speakerPrepositionService == null) {
            speakerPrepositionService = new SpeakerPrepositionService();
        }
        return speakerPrepositionService;
    }

    public synchronized IUserRolesService getUserRolesService() {
        if(userRolesService == null) {
            userRolesService = new UserRolesService();
        }
        return userRolesService;
    }

    public synchronized IUserService getUserService() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }

}
