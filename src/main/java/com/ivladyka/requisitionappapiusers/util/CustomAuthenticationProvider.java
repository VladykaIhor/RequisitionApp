package com.ivladyka.requisitionappapiusers.util;

import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {
//here I should Autowire otp checking service, and in case of match return successful authentication model

    private SmsCodeService smsCodeService;
    private UserService userService;

    @Autowired
    public CustomAuthenticationProvider(SmsCodeService smsCodeService, UserService userService) {
        this.smsCodeService = smsCodeService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if (authentication instanceof SmsCodeDTO) {
        UserDTO userByPhoneNumber = userService.getUserByPhoneNumber(((SmsCodeDTO) authentication).getPhoneNumber());
        smsCodeService.getOTP(authentication.getCredentials().toString());
        authentication.setAuthenticated(userByPhoneNumber != null);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeDTO.class.equals(authentication);
    }
}
