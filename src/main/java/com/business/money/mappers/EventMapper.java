package com.business.money.mappers;

import com.business.money.DTOs.event.CreateEventDTO;
import com.business.money.DTOs.event.EventResponseDTO;
import com.business.money.entities.EventEntity;
import com.business.money.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface EventMapper {
    @Mapping(target = "type.name", source = "type")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "mapToUserEntities")
    EventEntity toEventEntity(CreateEventDTO createEventDTO);

    @Named(value = "mapToUserEntities")
    default Set<UserEntity> mapToUserEntities(Set<Long> ids) {
        Set<UserEntity> authors = new HashSet<>();
        for(var id: ids) {
            UserEntity user = new UserEntity();
            user.setId(id);
            authors.add(user);
        }
        return authors;
    }

    @Mapping(target = "participantsId", source = "participants", qualifiedByName = "mapToUserIds")
    @Mapping(target = "authorsId", source = "authors", qualifiedByName = "mapToUserIds")
    @Mapping(target = "type", source = "type.name")
    EventResponseDTO toEventResponseDTO(EventEntity eventEntity);

    @Named("mapToUserIds")
    default Set<Long> mapToUserIds(Set<UserEntity> users) {
        if (users == null) return new HashSet<>();
        return users.stream()
                .map(UserEntity::getId)
                .collect(Collectors.toSet());
    }
}