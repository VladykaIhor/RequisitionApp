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

    private final SmsCodeService smsCodeService;
//    private final UserService userService;

    @Autowired
    public CustomAuthenticationProvider(SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UserDTO userByPhoneNumber = userService.getUserByPhoneNumber(((SmsCodeDTO) authentication).getPhoneNumber());
        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeDTO.class.equals(authentication);
    }
}
