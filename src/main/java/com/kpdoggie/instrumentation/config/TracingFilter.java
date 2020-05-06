package com.kpdoggie.instrumentation.config;

import com.kpdoggie.instrumentation.KmoConstants;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TracingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String tracingId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        HttpServletRequest idInjectedRequest = new InjectHeader(request,
                KmoConstants.TRACING_ID_HEADER, tracingId);
        response.addHeader(KmoConstants.TRACING_ID_HEADER, tracingId);
        filterChain.doFilter(idInjectedRequest, response);
        // MDC
    }

    public class InjectHeader extends HttpServletRequestWrapper {

        private String headerName;
        private String value;

        public InjectHeader(HttpServletRequest request, String headerName, String value) {
            super(request);
            this.headerName = headerName;
            this.value = value;
        }

        public String getHeader(String name) {
            if (name.equals(headerName)) {
                return value;
            } else {
                return super.getHeader(name);
            }
        }

        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            names.add(headerName);
            return Collections.enumeration(names);
        }
    }
}
