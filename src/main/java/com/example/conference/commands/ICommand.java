package com.example.conference.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    /**
     * @param req, resp - request attributes and parameters
     *
     */
    void execute(HttpServletRequest req, HttpServletResponse resp);

}