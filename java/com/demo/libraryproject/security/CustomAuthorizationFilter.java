//package com.demo.libraryproject.security;
//
//import com.demo.libraryproject.model.SessionDB;
//import com.demo.libraryproject.service.SessionDBService;
//import com.demo.libraryproject.service.UserDetailsServiceImpl;
//import com.demo.libraryproject.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomAuthorizationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private SessionDBService sessionDBService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String username = request.getHeader("userName");
//        System.out.println(username);
//
//        if(username != null){
//            SessionDB sessionDB = sessionDBService.findByUsername(username);
//            String token = sessionDB.getToken();
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            if(jwtUtil.validateToken(token, userDetails)){
//                System.out.println("Token je validan");
//            } else {
//                System.out.println("token nije validan");
//            }
//        } else {
//            response.sendRedirect("/login");
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//
//
////    @Override
////    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
////        return Boolean.TRUE.equals(request.getRequestURL().equals("http://localhost:1010/login"));
////    }
//}
