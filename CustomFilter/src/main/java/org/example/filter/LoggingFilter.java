package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class LoggingFilter implements Filter {

    private final Logger logger =
            Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        String authorizationKey = httpRequest.getHeader("Authorization");
        logger.info("Successfully authenticated request with authorization key " +  authorizationKey);
        filterChain.doFilter(request, response);
    }
}
