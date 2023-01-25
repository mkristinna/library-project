//package com.demo.libraryproject.security;
//
//import com.demo.libraryproject.model.SessionDB;
//import com.demo.libraryproject.service.SessionDBService;
//import com.demo.libraryproject.util.JwtUtil;
//import io.jsonwebtoken.Jwt;
//import org.bson.BsonDateTime;
//import org.bson.codecs.jsr310.LocalDateTimeCodec;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//@Component
//public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private SessionDBService sessionDBService;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
//
//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//        String username = user.getUsername();
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        SessionDB sessionDB = new SessionDB();
//        sessionDB.setUsername(username);
//        sessionDB.setToken(jwt);
//        sessionDB.setTime(LocalDateTime.now());
//        sessionDBService.save(sessionDB);
//
////        response.setHeader("Authorization", "Bearer " + jwt);
//
//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        if(roles.contains("ADMIN") || roles.contains("MANAGER")){
//            response.sendRedirect("/admin/homepage");
//        } else {
//            response.sendRedirect("/user/homepage");
//        }
//
//    }
//}
