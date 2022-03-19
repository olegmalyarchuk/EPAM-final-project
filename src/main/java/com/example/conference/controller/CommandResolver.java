package com.example.conference.controller;

import com.example.conference.commands.ICommand;
import com.example.conference.commands.implementation.CommandOpenMainPage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandResolver {

    private static CommandResolver instance = null;
    Map<String, ICommand> commands = new HashMap<>();

    public CommandResolver() {
        /** Commands available for all */
        commands.put("main", new CommandOpenMainPage());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
       if (command == null)  command = commands.get("main");

        return command;
    }

    public static CommandResolver getInstance() {
        if (instance == null)
            instance = new CommandResolver();
        return instance;
    }
}