package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbUtilService {
    private final UserRepository userRepository;

    public UserEntity getUserByUsername(String username) {

        return userRepository.findUserEntitiesByUserName(username);
    }
    public UserEntity saveUserDetails(UserEntity user) {

        return userRepository.save(user);
    }
}
