package com.business.money.DTOs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {

    @JsonProperty("email")
    @NotBlank(message = "Почта не заполнена")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Пароль не заполнен")
    private String password;
}
