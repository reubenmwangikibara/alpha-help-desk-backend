package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.User;
import com.alpha.alpha_help_desk_backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public User addNewUser (UserDTO userDTO){
        User user=modelMapper.map(userDTO, User.class);
        return userRepository.save(user);


    }
}