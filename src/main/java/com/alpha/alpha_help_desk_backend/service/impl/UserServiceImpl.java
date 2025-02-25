package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.service.UserService;
import com.alpha.alpha_help_desk_backend.utils.db.UserDbUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDbUtilService userDbUtilService;

    public UserEntity addNewUser (UserDTO userDTO) throws Exception {
        //check if the user exist
        var userEntity = userDbUtilService.getUserByUsername(userDTO.getUserName());
        if (userEntity != null) {
            throw new Exception("user already exists");
        }
        var user = UserEntity.builder()
                .status(1)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .userName(userDTO.getUserName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .build();
        log.info("We are about to create a new user {}",new Gson().toJson(user));
        var savedUser = userDbUtilService.saveUserDetails(user);
        log.info("User Created Successfully");
        return savedUser;
    }
}