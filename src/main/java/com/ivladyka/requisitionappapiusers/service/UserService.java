package com.ivladyka.requisitionappapiusers.service;

import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO createUser(UserDTO user);
}
