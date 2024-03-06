package com.business.money.services;

import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.repos.ClanRepo;
import com.business.money.util.ClanComparator;
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

    public ClanEntity findById(Long id) throws NotFoundException {
        return clanRepo.findById(id).orElseThrow(() -> new NotFoundException("Клана с таким id не существует"));
    }

    public Set<UserEntity> getMembers(Long id) throws NotFoundException {
        ClanEntity clan = findById(id);
        return clan.getMembers();
    }

    public List<ClanEntity> getAllClans() {
        return clanRepo.findAll();
    }

    // возварщает клан с миниальным кол-вом участников
    public ClanEntity getMinClan() {
        return getAllClans().stream().min(ClanComparator::compare).get();
    }
}
