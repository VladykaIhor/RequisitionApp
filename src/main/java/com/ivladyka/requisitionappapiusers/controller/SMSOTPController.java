package com.ivladyka.requisitionappapiusers.controller;

import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SMSOTPController {
    public SmsCodeService smsCodeService;
    @Autowired
    public SMSOTPController(SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    @PostMapping("/otp")
    public Authentication validateOtpInput(@RequestBody String code){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getCredentials();
        if (code.equals(String.valueOf(smsCodeService.getOTP(auth.getPrincipal().toString())))) {
            auth.setAuthenticated(true);
            throw new BadCredentialsException("Invalid verification code");
        } else {
            auth.setAuthenticated(true);
            return auth;
        }
    }
}
