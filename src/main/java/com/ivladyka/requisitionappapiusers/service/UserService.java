package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO createUser(UserDTO user);

    UserDTO getUserByPhoneNumber(String phoneNumber);

    public boolean isUserAlreadyRegistered(User user);

    void login(UserDTO user);
}
