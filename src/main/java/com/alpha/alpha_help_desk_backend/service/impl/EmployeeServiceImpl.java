package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.EmployeeDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.service.EmployeeService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDBUtilService employeeDBUtilService;
    public EmployeeEntity addEmployeeDetails (EmployeeDTO employeeDTO) throws Exception {
        //var
        EmployeeEntity employeeEntity = employeeDBUtilService.getEmployeeByEmployeeNumber(employeeDTO.getEmployeeNumber());

//        if (employeeEntity != null) {
//          throw new Exception("Employee already exists");
//        }

        var employee = EmployeeEntity.builder()
                .Status(1)
                .employeeNumber(employeeDTO.getEmployeeNumber())
                .employeeNumber(employeeDTO.getNational_id())
                .Area_Of_residence(employeeDTO.getArea_Of_residence())
                .employeeNumber(employeeDTO.getEmployeeNumber())
                .build();
        log.info("We are about to save employee details {}",new Gson().toJson(employee));
        var savedEmployee = employeeDBUtilService.saveEmployeeDetails(employee);
        log.info("employee details saved Successfully");
        return savedEmployee;
    }
}
