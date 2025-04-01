package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeRequestDTO;





public interface EmployeeService {
    BaseApiResponse addEmployeeDetails (EmployeeRequestDTO employeeRequestDTO) throws Exception;

    BaseApiResponse updateEmployeeDetails (Long tid, EmployeeRequestDTO employeeRequestDTO) throws Exception;

    BaseApiResponse fetchEmployeeDetails () throws Exception;

}
