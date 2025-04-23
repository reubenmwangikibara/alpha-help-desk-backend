package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.request.LoginDTO;
import com.alpha.alpha_help_desk_backend.dto.request.UserDTO;
import com.alpha.alpha_help_desk_backend.dto.response.AuthResponseDto;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.exceptions.InvalidTokenException;
import com.alpha.alpha_help_desk_backend.exceptions.UserExistException;
import com.alpha.alpha_help_desk_backend.security.JwtUtil;
import com.alpha.alpha_help_desk_backend.service.UserService;
import com.alpha.alpha_help_desk_backend.utils.db.UserDbUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDbUtilService userDbUtilService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserEntity addNewUser (UserDTO userDTO) throws Exception {
        //check if the user exist
        var userEntity = userDbUtilService. checkIfUserExist(userDTO.getUserName(), userDTO.getPhoneNumber());
        log.info("userEntity: {}", userEntity.isPresent());
        if (userEntity.isPresent()) {
            log.info("user already exist");
            throw new UserExistException("user already exists");
        }
        var user = UserEntity.builder()
                .status(1)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .userName(userDTO.getUserName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();
        log.info("We are about to create a new user {}",new Gson().toJson(user));
        var savedUser = userDbUtilService.saveUserDetails(user);
        log.info("User Created Successfully");
        return savedUser;
    }

    @Override
    public AuthResponseDto  userLogin(LoginDTO loginDTO) throws Exception {
        Map<String,Object> response = new HashMap<>();
        log.info("We are about to handle user login  {}",new Gson().toJson(loginDTO.getUserName()));
        var userEntity = userDbUtilService.getUserByUsername(loginDTO.getUserName());
        if (userEntity.isPresent()) {
            log.info("User Found");
            UserEntity user = userEntity.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                var authResponseDto = jwtUtil.generateToken(loginDTO.getUserName()); // Generate JWT

                log.info("User logged in successfully");
                return authResponseDto;
            }
            else {
                throw new AuthenticationException("Incorrect Username / password");
            }
        }
        log.info("User Login Failed : User Not Found");
        throw new InvalidTokenException("Invalid Username / password");

    }

}