package com.example.conference.commands;

import com.example.conference.controller.SessionRequestContent;
import com.example.conference.entity.User;
import com.example.conference.entity.User_roles;

public class Security {
    /**
     * Checks if user allowed for action
     * @param content - object that contains session and request attributes and parameters
     * @param roles - user roles allowed for command execution
     * @return
     */
    public static boolean checkSecurity(SessionRequestContent content, User_roles... roles) {
        if (!content.checkSessionAttribute("user")) {
            return false;
        }
        User user = (User) content.getSessionAttribute("user");
        for (User_roles role : roles)
            if (user.getRole_id() == role.getRole_id())
                return true;
        return false;
    }
}