package com.alpha.alpha_help_desk_backend.mapper;

import com.alpha.alpha_help_desk_backend.dto.response.EmployeeResponseDto;
import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.UserEntity;

public class EmployeeMapper {
    
    // Convert Entity to DTO
    public static EmployeeResponseDto mapTODto(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }
        
        UserEntity user = employee.getUserEntity(); // Get associated UserEntity
        
        return EmployeeResponseDto.builder()
                .tid(employee.getTid())
                .employeeNumber(employee.getEmployeeNumber())
                .status(employee.getStatus()==1? "ACTIVE":"DEACTIVATED")
                .areaOfResidence(employee.getAreaOfResidence())
                .nationalID(employee.getNationalId())
                .userId(user != null ? user.getTid() : null) // Avoid lazy-loading issue
                .employeeName(user != null ? user.getFirstName() +" "+user.getLastName() : null)
                .employeeNumber(employee.getEmployeeNumber())
                .phoneNumber(user != null ? user.getPhoneNumber() : null)
                .build();
    }
}
