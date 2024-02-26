package com.business.money.services;

import com.business.money.entities.ClanEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.repos.ClanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClanService {
    private final ClanRepo clanRepo;

    public ClanEntity findByName(String name) throws NotFoundException {
        return clanRepo.findByName(name).orElseThrow(() -> new NotFoundException("Клана с таким названием не существует"));
    }
}
