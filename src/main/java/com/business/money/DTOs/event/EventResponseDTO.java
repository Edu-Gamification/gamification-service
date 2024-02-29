package com.business.money.DTOs.event;

import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
public class EventResponseDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("quote")
    private Integer quote;

    @JsonProperty("clan_only")
    private Boolean clanOnly;

    @JsonProperty("authors_id")
    private Set<Long> authorsId;

    @JsonProperty("participants_id")
    private Set<Long> participantsId;
}
