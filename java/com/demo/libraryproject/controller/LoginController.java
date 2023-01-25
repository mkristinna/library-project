package com.demo.libraryproject.controller;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login-page";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login-page";
    }

}
