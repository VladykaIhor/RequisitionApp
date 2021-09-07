package com.ivladyka.requisitionappapiusers.shared.mapper;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public User userDtoToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRequisitionList(userDTO.getRequisitionList());
        return user;
    }

    @Override
    public UserDTO userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRequisitionList(user.getRequisitionList());
        return userDTO;
    }
}
