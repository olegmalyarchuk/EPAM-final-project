package com.example.conference.commands.implementation;

import com.example.conference.commands.ICommand;
import com.example.conference.config.Configuration;
import com.example.conference.controller.Direction;
import com.example.conference.controller.ExecutionResult;
import com.example.conference.controller.SessionRequestContent;

public class CommandMissing implements ICommand {

    @Override
    public ExecutionResult execute(SessionRequestContent content) {
        Configuration conf = Configuration.getInstance();
        ExecutionResult result = new ExecutionResult();
        result.setDirection(Direction.FORWARD);
        result.setPage(conf.getPage("404"));
        return result;
    }
}
