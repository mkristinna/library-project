package com.demo.libraryproject.security;

import com.demo.libraryproject.model.SessionDB;
import com.demo.libraryproject.service.SessionDBService;
import com.demo.libraryproject.service.UserDetailsServiceImpl;
import com.demo.libraryproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public CustomBeforeAuthenticationFilter customBeforeAuthenticationFilter() throws Exception {
        CustomBeforeAuthenticationFilter customBeforeAuthenticationFilter = new CustomBeforeAuthenticationFilter();
        customBeforeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return customBeforeAuthenticationFilter;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SessionDBService sessionDBService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN").authorities("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER").authorities("USER");

        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/books/**").permitAll()
                .antMatchers("/clanovi/**").permitAll()
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAnyAuthority("MANAGER", "ADMIN")
                .anyRequest().authenticated()
           //     .and()
           //     .sessionManagement()
           //     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getBeforeAuthenticationFilter(), CustomBeforeAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
        //        .successHandler(loginSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

    }
    public UsernamePasswordAuthenticationFilter getBeforeAuthenticationFilter() throws Exception {
        CustomBeforeAuthenticationFilter filter = new CustomBeforeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                System.out.println("Login error: " + exception.getMessage());
                super.setDefaultFailureUrl("/login?error");
                super.onAuthenticationFailure(request, response, exception);
            }

        });

        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(){

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String username = request.getParameter("username");
                final UserDetails user = userDetailsService.loadUserByUsername(username);

                if(!sessionDBService.existsByUsername(username)){
                    final String jwt = jwtUtil.generateToken(user);
                    SessionDB sessionDB = new SessionDB();
                    sessionDB.setUsername(username);
                    sessionDB.setToken(jwt);
                    sessionDB.setTime(LocalDateTime.now());
                    sessionDBService.save(sessionDB);
                } else {
                    SessionDB sessionDB = sessionDBService.findByUsername(username);
                    String token = sessionDB.getToken();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    if (jwtUtil.validateToken(token, userDetails)) {
                        System.out.println("Token je validan");
                    }
                }

                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if(roles.contains("ADMIN") || roles.contains("MANAGER")){
                    response.sendRedirect("/admin/homepage");
                } else {
                    response.sendRedirect("/user/homepage");
                }

                super.onAuthenticationSuccess(request, response, authentication);
            }
        });


        return filter;
    }

//    ovo je okej
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .successHandler(loginSuccessHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login");
//
//    }


    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/images/**","/js/**");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/login")
//                .permitAll()
//                .antMatchers("/**")
//                .hasAnyRole("ADMIN", "USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/homepage.html")
//                .failureUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
//    }


    //   @Override
    //   protected void configure(HttpSecurity http) throws Exception {

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/anonymous*").anonymous()
//                .antMatchers(HttpMethod.POST,"/login*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html", true)
//                .failureUrl("/login.html?error=true")
//                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessHandler(logoutSuccessHandler());
    //   }



}

