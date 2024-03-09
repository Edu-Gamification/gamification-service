package com.business.money.services;

import com.business.money.entities.domain.RoleEntity;
import com.business.money.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleEntity getByName(String name) {
        return roleRepo.findByName(name).orElse(null);
    }
}
