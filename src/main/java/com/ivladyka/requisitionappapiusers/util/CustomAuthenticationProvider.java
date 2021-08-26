package com.ivladyka.requisitionappapiusers.util;

import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {
//here I should Autowire otp checking service, and in case of match return successful authentication model


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof SmsCodeDTO){
            return null;
        }else {
            //smsCodeDTO.getCode().equals(authentication.getCode())
//            return new AbstractAuthenticationToken.getAuthorities()
        }

        Object login = authentication.getCredentials();
//        shouldAuthenticateAgainstThirdPartySystem()
        authentication.getAuthorities().forEach(s -> System.out.println(s.getAuthority()));
    return new SmsCodeDTO(authentication.getAuthorities());
    }
//re
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeDTO.class.equals(authentication);
    }
}
