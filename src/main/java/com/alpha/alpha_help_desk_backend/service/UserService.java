package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.LoginDTO;
import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.dto.response.AuthResponseDto;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;

import java.util.Map;

public interface UserService {

    UserEntity addNewUser (UserDTO userDTO) throws Exception;
    AuthResponseDto userLogin (LoginDTO loginDTO) throws Exception;

}
