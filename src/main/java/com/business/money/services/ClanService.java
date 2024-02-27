package com.business.money.services;

import com.business.money.DTOs.user.UserResponseDTO;
import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.repos.ClanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClanService {
    private final ClanRepo clanRepo;

    public ClanEntity findByName(String name) throws NotFoundException {
        return clanRepo.findByName(name).orElseThrow(() -> new NotFoundException("Клана с таким названием не существует"));
    }

    public Set<UserEntity> getMembers(String name) throws NotFoundException {
        Optional<ClanEntity> clan = clanRepo.findByName(name);
        if (clan.isEmpty()) throw new NotFoundException("Клан с таким именем не найден");
        System.out.println(Arrays.toString(clan.get().getMembers().toArray()));
        return clan.get().getMembers();
    }
}
