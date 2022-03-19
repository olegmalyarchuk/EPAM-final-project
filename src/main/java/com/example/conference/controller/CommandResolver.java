package com.example.conference.controller;

import com.example.conference.commands.ICommand;
import com.example.conference.commands.implementation.*;
import com.example.conference.commands.implementation.CommandOpenRegisterPage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandResolver {

    private static CommandResolver instance = null;
    Map<String, ICommand> commands = new HashMap<>();

    public CommandResolver() {
        /** Commands available for all */
        commands.put("main", new CommandOpenMainPage());
        commands.put("login", new CommandOpenLoginPage());
        commands.put("register", new CommandOpenRegisterPage());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
       if (command == null)  command = new CommandMissing();

        return command;
    }

    public static CommandResolver getInstance() {
        if (instance == null)
            instance = new CommandResolver();
        return instance;
    }
}