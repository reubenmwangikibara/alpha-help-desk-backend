package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/expense")
@RequiredArgsConstructor
public class ExpenseController {

    @PostMapping("/add")
    public String addExpense (@RequestBody UserDTO userDTO) throws Exception {

        return "success";
    }
}
