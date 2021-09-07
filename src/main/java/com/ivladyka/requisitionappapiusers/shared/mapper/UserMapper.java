package com.ivladyka.requisitionappapiusers.shared.mapper;

import com.ivladyka.requisitionappapiusers.model.User;
import com.ivladyka.requisitionappapiusers.shared.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    NameInfoMapper MAPPER = Mappers.getMapper(NameInfoMapper.class);

    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);
}
