package com.example.conference.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/deleteUser", "/editUser", "/updateUser", "/newReport","/deleteReport", "/editReport", "/updateReport", "/addReport", "/insertReport", "/newEvent","/insertEvent","/deleteEvent", "/editEvent", "/updateEvent", "/editPresence", "/setPresence", "/setPreposition", "/proposeSpeaker"})
public class ModeratorFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        Integer role_id = (Integer) session.getAttribute("role_id");
        boolean isTrue = (role_id==1);
        if(!isTrue) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("listEvent");
            dispatcher.forward(request, response);
            dispatcher.forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
