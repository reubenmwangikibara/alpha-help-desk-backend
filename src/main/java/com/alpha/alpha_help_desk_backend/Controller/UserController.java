package com.alpha.alpha_help_desk_backend.Controller;

import com.alpha.alpha_help_desk_backend.DTO.UserDTO;
import com.alpha.alpha_help_desk_backend.Entity.User;
import com.alpha.alpha_help_desk_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping

    public User addNewUser (@RequestBody UserDTO userDTO){
        return userService.addNewUser(userDTO);
    }

}
