package com.demo.libraryproject.security;

import com.demo.libraryproject.model.SessionDB;
import com.demo.libraryproject.service.SessionDBService;
import com.demo.libraryproject.service.UserDetailsServiceImpl;
import com.demo.libraryproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SessionDBService sessionDBService;

    @Autowired
    private JwtUtil jwtUtil;

    public CustomBeforeAuthenticationFilter() {
        setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");



        return super.attemptAuthentication(request, response);
    }
}
