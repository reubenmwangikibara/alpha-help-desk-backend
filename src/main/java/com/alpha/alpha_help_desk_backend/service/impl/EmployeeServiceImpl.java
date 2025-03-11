package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.EmployeeDTO;
import com.alpha.alpha_help_desk_backend.dto.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.service.EmployeeService;
import com.alpha.alpha_help_desk_backend.service.UserService;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import com.alpha.alpha_help_desk_backend.utils.db.UserDbUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDBUtilService employeeDBUtilService;
    private final UserService userService;
    public EmployeeEntity addEmployeeDetails (EmployeeDTO employeeDTO) throws Exception {
       //let's create a user
        char firstChar = employeeDTO.getFirstName().charAt(0);
        var employeeNumber =UtilService.employeeNumberGenerator();
        var userName = firstChar+employeeDTO.getLastName()+employeeNumber;
        var userDetails = UserDTO.builder()
                .phoneNumber(employeeDTO.getPhoneNumber())
                .userName(userName)
                .status(1)
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .password(userName)
                .role("USER")
                .build();
        var createdUser = userService.addNewUser(userDetails);

        System.out.println("user created: " + new Gson().toJson(createdUser));


        var employee = EmployeeEntity.builder()
                .status(1)
                .employeeNumber(employeeNumber)
                .nationalID(employeeDTO.getNationalId())
                .areaOfResidence(employeeDTO.getAreaOfResidence())
                .userID(createdUser.getTid())
                .build();

        log.info("We are about to save employee details {}",new Gson().toJson(employee));
        var savedEmployee = employeeDBUtilService.saveEmployeeDetails(employee);
        log.info("employee details saved Successfully");
        return savedEmployee;
    }
}
