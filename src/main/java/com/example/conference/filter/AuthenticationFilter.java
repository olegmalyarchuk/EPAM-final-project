package com.example.conference.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/showProfile", "/editInfo", "/deleteUser", "/editUser", "/updateUser", "/rejectSpeakerFromModerator", "/acceptSpeakerFromModerator", "/rejectSpeakerForReport", "/rejectSpeakerReports", "/acceptSpeakerForReport", "/acceptSpeakerReports", "/newReport","/deleteReport", "/editReport", "/updateReport", "/listReport", "/proposeReport", "/addReportPropose", "/addReport", "/insertReport", "/registerUser", "/excludeUser", "/newEvent","/insertEvent","/deleteEvent", "/editEvent", "/updateEvent", "/listEvent", "/eventEvent", "/proposeMe", "/editPresence", "/setPresence", "/setPreposition", "/proposeSpeaker", "/lang", "/logout"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        if(session.getAttribute("email")==null) {
            request.setAttribute("status", "unregistered");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
