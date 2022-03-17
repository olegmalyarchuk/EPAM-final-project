package com.example.conference.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter{
    private String requestEncoding;
    private String responseEncoding;

    public void init(FilterConfig config) throws ServletException {
        requestEncoding = config.getInitParameter("requestEncoding");
        responseEncoding = config.getInitParameter("responseEncoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain net)
            throws IOException, ServletException {
        request.setCharacterEncoding(requestEncoding);
        response.setCharacterEncoding(responseEncoding);
        net.doFilter(request, response);
    }

    public void destroy(){
    }
}
