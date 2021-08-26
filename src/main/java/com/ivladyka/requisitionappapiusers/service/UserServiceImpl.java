package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.repository.UserRepository;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SmsCodeService smsCodeService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SmsCodeService smsCodeService) {
        this.userRepository = userRepository;
        this.smsCodeService = smsCodeService;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        ModelMapper modelMapper = new ModelMapper();
//        user.setOneTimePassword(smsCodeService.generateAndSaveSmsCode());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        User userEntity = modelMapper.map(user, User.class);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User userByPhoneNumber = userRepository.findUserByPhoneNumber(phoneNumber);
        GrantedAuthority authority = new SimpleGrantedAuthority("user");
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(phoneNumber, null, Arrays.asList(authority));
        return userDetails;
    }
}
