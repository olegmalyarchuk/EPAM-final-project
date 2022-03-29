package com.example.conference.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * A simple implementation to log the event when attribute is added
 * removed or replaced in servlet context.
 */

@WebListener
public class AppContextAttributeListener implements ServletContextAttributeListener {
    private static final Logger log = Logger.getLogger(AppContextAttributeListener.class);
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
       log.trace("ServletContext attribute added::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        log.trace("ServletContext attribute replaced::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
       log.trace("ServletContext attribute removed::{"+servletContextAttributeEvent.getName()+","+servletContextAttributeEvent.getValue()+"}");
    }
}
