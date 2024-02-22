package com.business.money.mappers;

import com.business.money.DTOs.CreateEventDTO;
import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.EventEntity;
import com.business.money.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(CreateUserDTO createUserDTO);
    UserResponseDTO toUserResponseDTO(UserEntity user);
}
