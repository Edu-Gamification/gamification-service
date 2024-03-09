package com.business.money.controllers;

import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.domain.UserEntity;
import com.business.money.entities.security.AdminPermission;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.mappers.UserMapper;
import com.business.money.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;

    @AdminPermission
    @PostMapping("/add")
    public UserResponseDTO addAdminRole(@RequestParam Long id) throws NotFoundException {
        UserEntity user = userService.findById(id);
        userService.setAdminPermission(user);
        return userMapper.toUserResponseDTO(user);
    }

}
