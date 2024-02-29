package com.business.money.DTOs.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantEventDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "Неверный id пользователя")
    private Long userId;

    @JsonProperty("event_id")
    @Min(value = 1, message = "Неверный id события")
    private Long eventId;
}
