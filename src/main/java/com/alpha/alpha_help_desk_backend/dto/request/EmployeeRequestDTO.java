package com.alpha.alpha_help_desk_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequestDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String areaOfResidence;
    private long nationalId;
    private String phoneNumber;
    private String status;



}
