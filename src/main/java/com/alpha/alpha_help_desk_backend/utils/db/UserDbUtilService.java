package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbUtilService {
    private final UserRepository userRepository;

    public Optional<UserEntity> getUserByUsername(String username) {

        return userRepository.findUserEntitiesByUserName(username);
    }
    public Optional<UserEntity> checkIfUserExist(String username,String phoneNumber) {

        return userRepository.findUserEntitiesByUserNameOrPhoneNumber(username, phoneNumber);
    }
    public UserEntity saveUserDetails(UserEntity user) {

        return userRepository.save(user);
    }
}
