package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.request.LoginDTO;
import com.alpha.alpha_help_desk_backend.dto.request.UserDTO;
import com.alpha.alpha_help_desk_backend.dto.response.AuthResponseDto;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;

public interface UserService {

    UserEntity addNewUser (UserDTO userDTO) throws Exception;

    AuthResponseDto userLogin (LoginDTO loginDTO) throws Exception;

}
