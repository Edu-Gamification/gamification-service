package com.business.money.controllers;

import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.mappers.UserMapper;
import com.business.money.services.ClanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clan")
@RequiredArgsConstructor
public class ClanController {
    private final ClanService clanService;
    private final UserMapper userMapper;

    @GetMapping("/members")
    public List<UserResponseDTO> getMembers(@RequestParam String name) throws NotFoundException {
        return clanService.getMembers(name).stream().map(userMapper::toUserResponseDTO).toList();
    }
}
