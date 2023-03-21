package com.contactmangementservice.security;

import com.contactmangementservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

//@Component
//@Order(1)
public class RequestFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Autowired
    public RequestFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!authService.isPublicAPI(request.getRequestURI())) {
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Basic ")) {
                String userNamePassword = new String(Base64.getDecoder().decode(requestTokenHeader.substring(6)));
                if (userNamePassword.contains(":")) {
                    String[] userPassword = userNamePassword.split(":");
                    if (!authService.isValidCredentials(userPassword[0], userPassword[1])) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                "Invalid username and password!");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            "UNAUTHORIZED Access!");
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "UNAUTHORIZED Access!");
            }
            filterChain.doFilter(request, response);
        }

    }
}
