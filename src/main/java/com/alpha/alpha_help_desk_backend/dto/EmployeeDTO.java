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
    private long user_id;
    private long employeenumber;
    private String Area_Of_residence;
    private long national_id;
    private Boolean status;



}
