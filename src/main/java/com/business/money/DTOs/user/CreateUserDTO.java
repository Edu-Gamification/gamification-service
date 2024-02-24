package com.business.money.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    @NotBlank(message = "имя не должно быть пустым")
    @JsonProperty("name")
    @NotNull
    private String name;

    @NotBlank(message = "фамилия не должна быть пустой")
    @NotNull
    @JsonProperty(value = "surname", required = false)
    private String surname;

    @JsonProperty("patronymic")
    @NotNull
    private String patronymic;

    @Email
    @NotBlank(message = "почта не должна быть пустой")
    @JsonProperty("email")
    @NotNull
    private String email;

    @NotBlank(message = "пароль не должен быть пустым")
    @NotNull
    private String password;

    @JsonProperty("clan_points")
    private int clanPoints = 0;

    @JsonProperty("coins")
    private int coins = 0;
}
