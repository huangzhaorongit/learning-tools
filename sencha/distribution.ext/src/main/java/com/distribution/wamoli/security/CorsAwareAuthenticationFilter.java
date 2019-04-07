package com.distribution.wamoli.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by baling.fang 于 2017年01月12日.
 */
public class CorsAwareAuthenticationFilter extends OncePerRequestFilter {
    static final String ORIGIN = "Origin";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (request.getHeader(ORIGIN) == null || request.getHeader(ORIGIN).equals("null")) {
            String origin = request.getHeader(ORIGIN);
            response.setHeader("Access-Control-Allow-Origin", "*");//* or origin as u prefer
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers",
                request.getHeader("Access-Control-Request-Headers"));
        }
        if (request.getMethod().equals("OPTIONS")) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
