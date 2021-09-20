package com.ivladyka.requisitionappapiusers.shared.mapper;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);
}
