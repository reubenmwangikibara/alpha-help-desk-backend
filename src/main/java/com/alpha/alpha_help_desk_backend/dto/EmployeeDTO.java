package com.alpha.alpha_help_desk_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private long tid;
    private long userId;
    private long employeeNumber;
    private String areaOfResidence;
    private long nationalId;
    private Boolean status;



}
