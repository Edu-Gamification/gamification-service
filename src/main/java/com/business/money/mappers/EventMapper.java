package com.business.money.mappers;

import com.business.money.DTOs.event.CreateEventDTO;
import com.business.money.DTOs.event.EventResponseDTO;
import com.business.money.entities.EventEntity;
import com.business.money.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "type.name", source = "type")
    @Mapping(target = "authors", source = "authors", qualifiedByName = "authors")
    EventEntity toEventEntity(CreateEventDTO createEventDTO);

    EventResponseDTO toEventResponseDTO(EventEntity eventEntity);

    @Named(value = "authors")
    default Set<UserEntity> convertAuthors(Set<Long> ids) {
        Set<UserEntity> authors = new HashSet<>();
        for(var id: ids) {
            UserEntity user = new UserEntity();
            user.setId(id);
            authors.add(user);
        }
        return authors;
    }


}
