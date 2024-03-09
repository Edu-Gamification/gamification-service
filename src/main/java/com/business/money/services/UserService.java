package com.business.money.services;

import com.business.money.entities.domain.ClanEntity;
import com.business.money.entities.domain.RoleEntity;
import com.business.money.entities.domain.UserEntity;
import com.business.money.entities.security.Roles;
import com.business.money.exception.exceptions.NotFoundException;
import com.business.money.exception.exceptions.UserAlreadyExistsException;
import com.business.money.repos.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public UserEntity findByEmail(String email) throws UsernameNotFoundException {
        Optional<UserEntity> foundUser = userRepo.findByEmail(email);
        if (foundUser.isEmpty()) throw new UsernameNotFoundException("Пользователя с такой почтой не сущетвует");
        return foundUser.get();
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

        RoleEntity roleUser = roleService.getByName(Roles.USER);
        Set<RoleEntity> roles = Set.of(roleUser);
        user.setRoles(roles);

        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasswordHash(encodedPassword);

        ClanEntity clan = clanService.getMinClan();
        user.setClan(clan);

        user.setActive(true);
        user.setClanPoints(0);
        user.setCoins(0);
        return userRepo.save(user);
    }

    public void setAdminPermission(UserEntity user) {
        RoleEntity adminRole = roleService.getByName(Roles.ADMIN);
        Set<RoleEntity> roles = user.getRoles();
        roles.add(adminRole);
        userRepo.save(user);
    }
}
