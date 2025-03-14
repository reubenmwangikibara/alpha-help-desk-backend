package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.request.EmployeeDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;





public interface EmployeeService {
    EmployeeEntity addEmployeeDetails (EmployeeDTO employeeDTO) throws Exception;
}
