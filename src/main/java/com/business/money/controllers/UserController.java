package com.business.money.controllers;

import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.mappers.UserMapper;
import com.business.money.services.ClanService;
import com.business.money.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ClanService clanService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(@RequestParam(required = false) String clan,
                                             @RequestParam(required = false) String email) throws NotFoundException {
        Set<UserEntity> res = new HashSet<>();
        if (email != null) {
            res= userService.findByEmailStartsWith(email);
        }
        if (clan != null) {
            ClanEntity clanEntity = clanService.findByName(clan);
            if (res != null) {
                for (var user: res) {
                    if (user.getClan().getName().equals(clan)) res.add(user);
                }
            }
            else {
                res = clanEntity.getMembers();
            }
        }

        if (res.isEmpty()) {
            return userService.getAllUsers().stream().map(userMapper::toUserResponseDTO).toList();
        }
        return res.stream().map(userMapper::toUserResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) throws NotFoundException {
        UserEntity foundUser = userService.findById(id);
        return userMapper.toUserResponseDTO(foundUser);
    }

    @PostMapping
    public UserResponseDTO saveNewUser(@RequestBody @Valid CreateUserDTO createUserDTO) throws NotFoundException, UserAlreadyExistsException {
        UserEntity user = userMapper.toEntity(createUserDTO);
        user = userService.save(user);
        return userMapper.toUserResponseDTO(user);
    }
}
