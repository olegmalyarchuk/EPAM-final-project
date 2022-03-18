package com.example.conference.commands;

import com.example.conference.controller.ExecutionResult;
import com.example.conference.controller.SessionRequestContent;

public interface ICommand {
    /**
     * @param content - object that contains session and request attributes and parameters
     * @return object that contains new request and session attributes and parameters as the result of command execution
     */
    ExecutionResult execute(SessionRequestContent content);

}