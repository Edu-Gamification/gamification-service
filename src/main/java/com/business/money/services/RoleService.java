package com.business.money.services;

import com.business.money.entities.RoleEntity;
import com.business.money.exception.exceptions.RoleNotFoundException;
import com.business.money.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleEntity getByName(String name) {
        return roleRepo.findByName(name).orElse(null);
    }
}
