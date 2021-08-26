package com.ivladyka.requisitionappapiusers.controller;

import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SMSOTPController {

    @Autowired
    public SmsCodeService otpService;

    @PostMapping("/otp")
    public String generateOtp(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);
        System.out.println("OTP : "+ otp);
        return String.valueOf(otp);
    }
}
