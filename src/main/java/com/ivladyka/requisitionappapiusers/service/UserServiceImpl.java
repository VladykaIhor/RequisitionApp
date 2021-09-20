package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.repository.UserRepository;
import com.ivladyka.requisitionappapiusers.shared.SmsCodeDTO;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import com.ivladyka.requisitionappapiusers.shared.mapper.UserMapper;
import com.ivladyka.requisitionappapiusers.util.CustomAuthenticationProvider;
import com.ivladyka.requisitionappapiusers.util.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SmsCodeService smsCodeService;
    private final QueueProducer queueProducer;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           SmsCodeService smsCodeService,
                           QueueProducer queueProducer,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.smsCodeService = smsCodeService;
        this.queueProducer = queueProducer;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User entity = userMapper.userDtoToUser(userDTO);
        userRepository.save(entity);
        return userDTO;
    }

    @Override
    public UserDTO getUserByPhoneNumber(String phoneNumber) {
        User userByPhoneNumber = userRepository.findUserByPhoneNumber(phoneNumber);
        return userMapper.userToUserDto(userByPhoneNumber);

    }

    @Override
    public boolean isUserAlreadyRegistered(User user) {
        if (userRepository.findUserByPhoneNumber(user.getPhoneNumber()) == null) {
            return false;
        } else return true;
    }

    @Override
    public void login(SmsCodeDTO smsCodeDTO) {
//        ????
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SecurityContextHolder.setContext(securityContext);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }
}
