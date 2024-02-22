package com.business.money.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {
    @JsonProperty("id")
    private long id;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("email")
    private String email;

    @JsonProperty("clanPoints")
    private int clanPoints;

    @JsonProperty("coins")
    private int coins;
}
