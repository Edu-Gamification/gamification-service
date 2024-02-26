package com.business.money.mappers;

import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "clan.name", source = "clan")
    UserEntity toEntity(CreateUserDTO createUserDTO);

    @Mapping(target = "clan", source = "clan.name")
    UserResponseDTO toUserResponseDTO(UserEntity user);
}
