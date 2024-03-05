package com.business.money.controllers;

import com.business.money.DTOs.user.UserLoginDTO;
import com.business.money.DTOs.user.UserLoginResponseDTO;
import com.business.money.mappers.UserMapper;
import com.business.money.services.UserService;
import com.business.money.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthUtil authUtil;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO loginUserDto) throws Exception {
        var user = userService.getByEmail(loginUserDto.getUsername());
        return authUtil.responseAuthUser(user, loginUserDto);
    }
}
