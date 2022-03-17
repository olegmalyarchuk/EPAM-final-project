package com.example.conference.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

/**
 * A simple implementation of ServletRequestListener interface
 * to log the ServletRequest IP address when request is initialized and destroyed.
 */

@WebListener
public class ServletRequestListener implements javax.servlet.ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        //log
        //System.out.println("ServletRequest destroyed. Remote IP="+servletRequest.getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        //log
        //System.out.println("ServletRequest initialized. Remote IP="+servletRequest.getRemoteAddr());
    }
}
