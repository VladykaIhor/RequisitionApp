package com.ivladyka.requisitionappapiusers.controller;


import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import com.ivladyka.requisitionappapiusers.util.CustomAuthenticationProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorisationController {

    public UserService userService;
    public SmsCodeService smsCodeService;
    public CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public AuthorisationController(UserService userService,
                                   SmsCodeService smsCodeService,
                                   CustomAuthenticationProvider customAuthenticationProvider) {
        this.userService = userService;
        this.smsCodeService = smsCodeService;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SmsCodeDTO smsCodeDTO = new SmsCodeDTO(user.getPhoneNumber(),
                String.valueOf(smsCodeService.generateOTP(user.getPhoneNumber())));

        //check if user with the phone number exists in DB, and set it authenticated.
        if (customAuthenticationProvider.authenticate(smsCodeDTO).isAuthenticated()) {
//            securityContext.setAuthentication(smsCodeDTO);
            System.out.println(smsCodeService.getOTP(user.getPhoneNumber()));
        } else {
            //create user in db
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userService.createUser(userDTO);
        }

        securityContext.setAuthentication(smsCodeDTO);
        System.out.println(smsCodeService.getOTP(user.getPhoneNumber()));
        return ResponseEntity.ok(user);
    }
}
