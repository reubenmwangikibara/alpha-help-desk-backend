package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;
import com.alpha.alpha_help_desk_backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDBUtilService {
    private final EmployeeRepository employeeRepository;

    public EmployeeEntity getEmployeeByEmployeeNumber(Long employee_Number) {

        return employeeRepository.findEmployeeByEmployeeNumber(employee_Number);

    }
    public EmployeeEntity saveEmployeeDetails(EmployeeEntity employee) {

        return employeeRepository.save(employee);
    }


}
