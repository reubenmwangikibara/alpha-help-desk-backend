package com.alpha.alpha_help_desk_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
    private long tid;
    private String firstName;
    private String lastName;
    private String areaOfResidence;
    private long nationalId;
    private String phoneNumber;



}
