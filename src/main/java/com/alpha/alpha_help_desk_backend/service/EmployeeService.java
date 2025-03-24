package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeDTO;





public interface EmployeeService {
    BaseApiResponse addEmployeeDetails (EmployeeDTO employeeDTO) throws Exception;

}
