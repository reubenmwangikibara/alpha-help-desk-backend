package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;

public interface UserService {

    UserEntity addNewUser (UserDTO userDTO) throws Exception;

}
