package com.demo.libraryproject.aspect;

import com.demo.libraryproject.model.PeriodOdrzavanja;
import com.demo.libraryproject.service.PeriodOdrzavanjaService;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private PeriodOdrzavanjaService periodOdrzavanjaService;

    @Around("execution(public String com.demo.libraryproject.controller.LoginController.login())")
    public String beforeLogging(){
        LocalDateTime now = LocalDateTime.now();
        if(periodOdrzavanjaService.findById(1).isBetween(now)){
            return "maintenance-page";
        } else{
            return "login-page";
        }
    }
}
