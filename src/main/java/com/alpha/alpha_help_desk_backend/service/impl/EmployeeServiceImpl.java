package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeDTO;
import com.alpha.alpha_help_desk_backend.dto.request.UserDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.mapper.EmployeeMapper;
import com.alpha.alpha_help_desk_backend.service.EmployeeService;
import com.alpha.alpha_help_desk_backend.service.UserService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDBUtilService employeeDBUtilService;
    private final UserService userService;
    private final ResponseService responseService;
    public BaseApiResponse addEmployeeDetails (EmployeeDTO employeeDTO) throws Exception {
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

        var employee = EmployeeEntity.builder()
                .status(1)
                .employeeNumber(employeeNumber)
                .nationalID(employeeDTO.getNationalId())
                .areaOfResidence(employeeDTO.getAreaOfResidence())
                .userEntity(createdUser)
                .build();

        log.info("We are about to save employee details {}",new Gson().toJson(employee));
        var savedEmployee = employeeDBUtilService.saveEmployeeDetails(employee);
        log.info("employee details saved Successfully");
        return responseService.buildSuccessApiResponseDto(List.of(savedEmployee),1);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchEmployeeDetails() throws Exception {
        var employees =  employeeDBUtilService.getActiveEmployees();
        log.info("Fetched employees {}",employees);
        var empDetails = employees.stream()
                .map(EmployeeMapper::mapTODto)
                .toList();
        return responseService.buildSuccessApiResponseDto(List.of(empDetails),employees.size());

    }


}
