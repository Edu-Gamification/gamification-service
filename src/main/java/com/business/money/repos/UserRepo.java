package com.business.money.repos;

import com.business.money.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query(
            value = "select * from users where starts_with(email, :email_start)",
            nativeQuery = true
    )
    Set<UserEntity> findAllByEmailStartsWith(@Param("email_start") String email);
}
