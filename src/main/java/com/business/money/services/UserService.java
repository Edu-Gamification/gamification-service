package com.business.money.services;

import com.business.money.entities.ClanEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.mappers.UserMapper;
import com.business.money.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ClanService clanService;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity findById(Long id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    public UserEntity save(UserEntity userEntity) throws NotFoundException, UserAlreadyExistsException {
        if (userRepo.findByEmail(userEntity.getEmail()).isPresent()) throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");
        ClanEntity clan = clanService.findByName(userEntity.getClan().getName());
        userEntity.setClan(clan);
        userEntity.setActive(true);
        return userRepo.save(userEntity);
    }
}
