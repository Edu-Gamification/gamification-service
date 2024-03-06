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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ClanService clanService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

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
    public UserEntity save(UserEntity user) throws UserAlreadyExistsException, NotFoundException {
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException("Пользователь с такой почтой уже существует");

        RoleEntity roleUser = roleService.getByName("USER");
        Set<RoleEntity> roles = Set.of(roleUser);
        user.setRoles(roles);

        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(encodedPassword);

        ClanEntity clan = clanService.findByName(user.getClan().getName());
        user.setClan(clan);

        user.setActive(true);
        user.setClanPoints(0);
        user.setCoins(0);

        return userRepo.save(user);
    }
}
