package com.example.conference.listener;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * A simple implementation to log the event when session is created or destroyed.
 */

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger log = Logger.getLogger(SessionListener.class);
    public void sessionCreated(HttpSessionEvent sessionEvent) {
       log.trace("Session Created:: ID="+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
       log.trace("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}
