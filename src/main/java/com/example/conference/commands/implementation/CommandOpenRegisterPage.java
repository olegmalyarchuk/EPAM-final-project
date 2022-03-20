package com.example.conference.commands.implementation;

import com.example.conference.commands.ICommand;
import com.example.conference.config.Configuration;
import com.example.conference.controller.Direction;
import com.example.conference.controller.ExecutionResult;
import com.example.conference.controller.SessionRequestContent;

public class CommandOpenRegisterPage implements ICommand {

    @Override
    public ExecutionResult execute(SessionRequestContent request) {
        ExecutionResult result = new ExecutionResult();
        result.setPage(Configuration.getInstance().getPage("register"));
        result.setDirection(Direction.FORWARD);

        return result;
    }
}
