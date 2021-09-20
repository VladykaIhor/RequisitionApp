package com.ivladyka.requisitionappapiusers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorisationController {

    public UserService userService;
    public SmsCodeService smsCodeService;

    @Autowired
    public AuthorisationController(final UserService userService, final SmsCodeService smsCodeService) {
        this.userService = userService;
        this.smsCodeService = smsCodeService;
    }

    @PostMapping("/login")
    public ResponseEntity<SmsCodeDTO> login(@RequestBody SmsCodeDTO smsCodeDTO) throws JsonProcessingException {
        userService.login(smsCodeDTO);
        return ResponseEntity.ok().build();
    }
}
