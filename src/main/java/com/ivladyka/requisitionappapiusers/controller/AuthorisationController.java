package com.ivladyka.requisitionappapiusers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.service.SmsCodeService;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.NotificationRequestDTO;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import com.ivladyka.requisitionappapiusers.util.CustomAuthenticationProvider;
import com.ivladyka.requisitionappapiusers.util.QueueProducer;
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
    public QueueProducer queueProducer;

    @Autowired
    public AuthorisationController(final UserService userService,
                                   final SmsCodeService smsCodeService,
                                   final CustomAuthenticationProvider customAuthenticationProvider,
                                   final QueueProducer queueProducer) {
        this.queueProducer = queueProducer;
        this.userService = userService;
        this.smsCodeService = smsCodeService;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws JsonProcessingException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SmsCodeDTO smsCodeDTO = new SmsCodeDTO(user.getPhoneNumber(),
                String.valueOf(smsCodeService.generateOTP(user.getPhoneNumber())), null);

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
        queueProducer.produce(new NotificationRequestDTO("satoribnd@gmail.com", user.getPhoneNumber()));
        securityContext.setAuthentication(smsCodeDTO);
        System.out.println(smsCodeService.getOTP(user.getPhoneNumber()));
        return ResponseEntity.ok(user);
    }
}
