package com.taskflow.mappers;
import com.taskflow.dto.UserDTO;
import com.taskflow.dto.response.UserResponseDTO;
import com.taskflow.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    UserResponseDTO entityToDto(User user);
    User dtoToEntity(UserDTO userDTO);
}
