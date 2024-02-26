package com.business.money.controllers;

import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.mappers.UserMapper;
import com.business.money.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(userMapper::toUserResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable long id) throws NotFoundException {
        UserEntity foundUser = userService.findById(id);
        return userMapper.toUserResponseDTO(foundUser);
    }

    @PostMapping("/")
    public UserResponseDTO saveNewUser(@RequestBody @Valid CreateUserDTO createUserDTO) throws NotFoundException, UserAlreadyExistsException {
        UserEntity user = userMapper.toEntity(createUserDTO);
        user = userService.saveUser(user);
        return userMapper.toUserResponseDTO(user);
    }
}
