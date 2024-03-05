package com.business.money.controllers;

import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.RoleNotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.mappers.UserMapper;
import com.business.money.services.ClanService;
import com.business.money.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
//@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ClanService clanService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(@RequestParam(required = false) String clan,
                                             @RequestParam(required = false) String email) throws NotFoundException {
        Set<UserEntity> res = new HashSet<>();

        if (clan != null) {
            ClanEntity clanEntity = clanService.findByName(clan);
            res = clanEntity.getMembers();
        }

        if (email != null) {
            Set<UserEntity> users = userService.findByEmailStartsWith(email);
            res.retainAll(users);
        }

        if (!res.isEmpty()) return res.stream().map(userMapper::toUserResponseDTO).toList();
        return userService.getAllUsers().stream().map(userMapper::toUserResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) throws NotFoundException {
        UserEntity foundUser = userService.findById(id);
        return userMapper.toUserResponseDTO(foundUser);
    }

    @PostMapping
    public UserResponseDTO saveNewUser(@RequestBody @Valid CreateUserDTO createUserDTO) throws NotFoundException,
            UserAlreadyExistsException, RoleNotFoundException {
        UserEntity user = userMapper.toEntity(createUserDTO);
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(encodedPassword);
        user = userService.save(user);
        return userMapper.toUserResponseDTO(user);
    }
}
