package com.ivladyka.requisitionappapiusers.controller;


import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorisationController implements AuthenticationProvider {


    public SmsCodeService smsCodeService;
    @Autowired
    public AuthorisationController(SmsCodeService smsCodeService ) {
        this.smsCodeService = smsCodeService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        authentication.setAuthenticated(false);
        securityContext.setAuthentication(authentication);
        SmsCodeDTO smsCodeDTO = new SmsCodeDTO(authenticate(authentication).getAuthorities());
        smsCodeDTO.setCode(String.valueOf(smsCodeService.generateOTP(user.getPhoneNumber())));
//        send otp via sms service to user.getPhoneNumber();
        System.out.println("OTP : " + user.getOneTimePassword());
        return ResponseEntity.ok(user);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeDTO smsCodeDTO = new SmsCodeDTO(authentication.getAuthorities());
        //        if (!user.getPassword().equals(password)) {
        //            throw new BadCredentialsException("Bad Credentials");
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
