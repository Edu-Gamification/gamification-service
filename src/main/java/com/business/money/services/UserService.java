package com.business.money.services;

import com.business.money.entities.ClanEntity;
import com.business.money.entities.RoleEntity;
import com.business.money.entities.UserEntity;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.repos.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ClanService clanService;
    private final RoleService roleService;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public UserEntity findById(Long id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    public Set<UserEntity> findByEmailStartsWith(String email) {
        return userRepo.findAllByEmailStartsWith(email);
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) throws NotFoundException,
            UserAlreadyExistsException {
        if (userRepo.findByEmail(userEntity.getEmail()).isPresent())
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");

        ClanEntity clan = clanService.findByName(userEntity.getClan().getName());
        userEntity.setClan(clan);
        userEntity.setActive(true);
        userEntity.setClanPoints(0);
        userEntity.setCoins(0);

        RoleEntity roleUser = roleService.getByName("USER");
        Set<RoleEntity> roles = Set.of(roleUser);
        userEntity.setRoles(roles);

        return userRepo.save(userEntity);
    }
}
