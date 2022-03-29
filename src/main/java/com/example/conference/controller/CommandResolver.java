package com.example.conference.controller;

import com.example.conference.commands.ICommand;
import com.example.conference.commands.implementation.CommandLogin;
import com.example.conference.commands.implementation.CommandLogout;
import com.example.conference.commands.implementation.event.*;
import com.example.conference.commands.implementation.CommandRegister;
import com.example.conference.commands.implementation.profile.*;
import com.example.conference.commands.implementation.report.*;
import com.example.conference.service.IUserService;
import com.example.conference.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandResolver {
    private static CommandResolver instance = null;
    Map<String, ICommand> commands = new HashMap<>();
    IUserService service = ServiceFactory.getInstance().getUserService();

    public CommandResolver() {
        commands.put("login", new CommandLogin());
        commands.put("register", new CommandRegister());
        commands.put("logout", new CommandLogout());
        commands.put("newEvent", new CommandNewEvent());
        commands.put("insertEvent", new CommandInsertEvent());
        commands.put("deleteEvent", new CommandDeleteEvent());
        commands.put("editEvent", new CommandEdtEvent());
        commands.put("updateEvent", new CommandUpdateEvent());
        commands.put("listEvent", new CommandListEvent());
        commands.put("eventEvent", new CommandEventEvent());
        commands.put("proposeMe", new CommandProposeMe());
        commands.put("editPresence", new CommandEditPresence());
        commands.put("setPresence", new CommandSetPresence());
        commands.put("proposeSpeaker", new CommandProposeSpeaker());
        commands.put("setPreposition", new CommandSetPreposition());
        commands.put("lang", new CommandLang());
        commands.put("showProfile", new CommandShowProfile());
        commands.put("editInfo", new CommandEditInfo());
        commands.put("rejectSpeakerFromModerator", new CommandRejectSpeakerFromModerator());
        commands.put("acceptSpeakerFromModerator", new CommandAcceptSpeakerFromModerator());
        commands.put("acceptSpeakerReports", new CommandAcceptSpeakerReports());
        commands.put("acceptSpeakerForReport", new CommandAcceptSpeakerForReport());
        commands.put("rejectSpeakerForReport", new CommandRejectSpeakerForReport());
        commands.put("rejectSpeakerReports", new CommandRejectSpeakerReports());
        commands.put("editUser", new CommandEditUser());
        commands.put("updateUser", new CommandUpdateUser());
        commands.put("deleteUser", new CommandDeleteUser());
        commands.put("proposeReport", new CommandProposeReport());
        commands.put("addReportPropose", new CommandAddReportPropose());
        commands.put("addReport", new CommandAddReport());
        commands.put("insertReport", new CommandInsertReport());
        commands.put("registerUser", new CommandRegisterUser());
        commands.put("excludeUser", new CommandExcludeUser());
        commands.put("deleteReport", new CommandDeleteReport());
        commands.put("editReport", new CommandEditReport());
        commands.put("updateReport", new CommandUpdateReport());
    }

    public ICommand getCommand(String action) {
        ICommand command = commands.get(action);
        return command;
    }

    public static CommandResolver getInstance() {
        if (instance == null)
            instance = new CommandResolver();
        return instance;
    }
}
