package com.business.money.DTOs.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class CreateEventDTO {
    @JsonProperty("title")
    @NotBlank(message = "Название не должно быть пустым")
    private String title;

    @NotBlank(message = "Описание не должно быть пустым")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Тип мероприятия не должен равняться null")
    @JsonProperty("type")
    private String type;

    @NotNull(message = "Время начала не должно быть пустым")
    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания не должно быть пустым")
    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @Max(value = 50, message = "Максмиальное кол-во человек на мероприятии: 50")
    @JsonProperty("quote")
    private int quote = 5;

    @JsonProperty("clan_only")
    private boolean clanOnly = false;

    @JsonProperty("authors")
    @Size(min = 1, max = 5, message = "Кол-во авторов должно быть между 1 и 5")
    @NotEmpty(message = "Должен быть хотя бы один автор")
    private Set<Long> authors;
}
