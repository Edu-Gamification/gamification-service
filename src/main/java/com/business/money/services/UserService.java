package com.business.money.services;

import com.business.money.DTOs.user.CreateUserDTO;
import com.business.money.entities.UserEntity;
import com.business.money.mappers.UserMapper;
import com.business.money.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserById(long id) {
        return userRepo.findById(id);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

}
