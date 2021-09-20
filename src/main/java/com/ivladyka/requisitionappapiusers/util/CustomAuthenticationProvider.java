package com.ivladyka.requisitionappapiusers.util;

import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
@NoArgsConstructor
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
        SmsCodeDTO smsAuthToken = (SmsCodeDTO) authentication;
        String enteredOtp = smsCodeService.getOTP(String.valueOf(smsAuthToken.getPrincipal()));
        //check if enterd otp is similar to an otp from cache
        if (enteredOtp.equals(smsAuthToken.getOtp())) {
            smsAuthToken.setAuthenticated(true);
        } else throw new BadCredentialsException("User authentication failed");

        UserDetails userDetails = userService.loadUserByUsername(String.valueOf(smsAuthToken.getPrincipal()));
        if (userDetails == null) {
//        register logic
        }
        return smsAuthToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeDTO.class.isAssignableFrom(authentication);
    }
}
