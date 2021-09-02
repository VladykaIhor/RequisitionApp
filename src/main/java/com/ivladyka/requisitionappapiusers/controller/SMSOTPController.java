package com.ivladyka.requisitionappapiusers.controller;

import com.ivladyka.requisitionappapiusers.model.SmsCode;
import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.util.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class SMSOTPController {

    private SmsCodeService smsCodeService;
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public SMSOTPController(CustomAuthenticationProvider customAuthenticationProvider, SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @PostMapping("/otp")
    public ResponseEntity<SmsCodeDTO> validateOtpInput(@RequestBody SmsCode smsCode) {
        SmsCodeDTO auth = (SmsCodeDTO) SecurityContextHolder.getContext().getAuthentication();
        int cachedOTP = smsCodeService.getOTP(auth.getPhoneNumber());
        if (String.valueOf(cachedOTP).equals(smsCode.getCode())) {
            smsCodeService.clearOTP(auth.getPhoneNumber());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>
                    (Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            SmsCodeDTO smsCodeDTO = new SmsCodeDTO(auth.getPhoneNumber(), auth.getCode(), grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(smsCodeDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
