package com.example.conference.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * A simple implementation to log the event when session is created or destroyed.
 */

@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
       //log
        // System.out.println("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
       //log
        // System.out.println("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}
