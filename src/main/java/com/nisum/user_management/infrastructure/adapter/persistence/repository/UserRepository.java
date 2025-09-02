package com.nisum.user_management.infrastructure.adapter.persistence.repository;

import com.nisum.user_management.infrastructure.adapter.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query("UPDATE UserEntity u SET u.lastLogin = :lastLogin WHERE u.email = :email")
    void updateLastLoginByEmail(@Param("email") String email, @Param("lastLogin") LocalDateTime lastLogin);
}
