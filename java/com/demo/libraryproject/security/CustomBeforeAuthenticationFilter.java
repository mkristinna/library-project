package com.demo.libraryproject.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomBeforeAuthenticationFilter() {
        setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

    }

}
