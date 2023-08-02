package org.example;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Objects;
import java.util.TimeZone;

@WebFilter(value = "/time?timezone")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezoneParameter = req.getParameter("timezone");

        if (isValidTimezone(timezoneParameter)) {
            chain.doFilter(req, resp);
        } else {
            resp.setStatus(400);
            resp.setContentType("text/plain; charset=utf-8");
            resp.getWriter().write("Invalid timezone");
            resp.getWriter().close();
        }
    }
    private boolean isValidTimezone(String timezone) {
        return !Objects.equals(TimeZone.getTimeZone(timezone), "GMT");

    }
}