package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findUserEntitiesByUserName(String username);

}
