package com.ivladyka.requisitionappapiusers.util;

import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SMSAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String MOBILE_KEY = "mobile";

    private String mobileParameter = MOBILE_KEY;

    private boolean postOnly = true;

    public SMSAuthenticationFilter() {
        super(new AntPathRequestMatcher("/otp", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported");
        }
        String phoneNumber = request.getParameter(mobileParameter);
        SmsCodeDTO smsCodeDTO = new SmsCodeDTO(phoneNumber, null);
        return this.getAuthenticationManager().authenticate(smsCodeDTO);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
