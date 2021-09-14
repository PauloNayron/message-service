package com.lb.messageservice.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class CorrelationHeaderFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationHeaderFilter.class);
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String correlationId = httpServletRequest.getHeader(RequestCorrelation.CORRELATION_ID_HEADER);

        correlationId = correlationId == null ? UUID.randomUUID().toString() : correlationId;

        RequestCorrelation.setId(correlationId);
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
