package com.example.conference.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 *     Encodes request and response
 *
 */
public class CharsetFilter implements Filter{
    /**
     *     Encodes request to UTF-8 encoding
     *     Encodes response to UTF-8 encoding
     * @param request an instance representing {@link ServletRequest}
     * @param response an instance representing {@link ServletResponse}
     * @param net an instance representing {@link FilterChain}
     * @throws IOException exception while may occur during filter processing
     * @throws ServletException exception while may occur during filter processing
     */

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
