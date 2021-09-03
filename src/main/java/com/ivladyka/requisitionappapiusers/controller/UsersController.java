package com.ivladyka.requisitionappapiusers.controller;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private Environment environment;

    private final UserService userService;

    @Autowired
    public UsersController(final UserService userService, final Environment environment) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + environment.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        UserDTO userEntity = modelMapper.map(user, UserDTO.class);
        userService.createUser(userEntity);
        return "New user was created";
    }

    @DeleteMapping
    public String removeUser() {
        return "Edit user method call";
    }
}
