package com.business.money.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    @NotEmpty(message = "имя не должно быть пустым")
    @JsonProperty("firstname")
    private String firstname;

    @NotBlank(message = "фамилия не должна быть пустой")
    @NotNull
    @JsonProperty(value = "lastname", required = false)
    private String lastname;

    @JsonProperty("patronymic")
    private String patronymic;

    @Email
    @NotEmpty(message = "почта не должна быть пустой")
    @JsonProperty("email")
    private String email;

    @NotEmpty(message = "пароль не должен быть пустым")
    private String password;

    @JsonProperty("clan_points")
    private int clanPoints = 0;

    @JsonProperty("coins")
    private int coins = 0;

}
