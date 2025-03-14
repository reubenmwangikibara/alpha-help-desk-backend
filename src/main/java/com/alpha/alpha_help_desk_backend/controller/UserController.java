package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.request.LoginDTO;
import com.alpha.alpha_help_desk_backend.dto.request.UserDTO;
import com.alpha.alpha_help_desk_backend.dto.response.AuthResponseDto;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public UserEntity addNewUser (@RequestBody UserDTO userDTO) throws Exception {

        return userService.addNewUser(userDTO);
    }

    @PostMapping("/login")
    public AuthResponseDto userLogin (@RequestBody LoginDTO loginDTO) throws Exception {

        return userService.userLogin(loginDTO);
    }

}
