package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeRequestDTO;
import com.alpha.alpha_help_desk_backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping("/add")
    public BaseApiResponse addEmployeeDetails (@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) throws Exception{

        return employeeService.addEmployeeDetails(employeeRequestDTO);
    }

    @GetMapping("/fetch")
    public BaseApiResponse fetchEmployeeDetails () throws Exception {

        return employeeService.fetchEmployeeDetails();
    }

    @PutMapping("/update/{tid}")
    public BaseApiResponse updateEmployeeDetails (@PathVariable Long tid,@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) throws Exception {

        return employeeService.updateEmployeeDetails(tid, employeeRequestDTO);
    }

}
