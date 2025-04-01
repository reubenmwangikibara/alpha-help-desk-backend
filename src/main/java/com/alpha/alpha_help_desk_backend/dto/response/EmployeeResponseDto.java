package com.alpha.alpha_help_desk_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {
    private long tid;
    private long employeeNumber;
    private String status;
    private String areaOfResidence;
    private long nationalID;
    private Long userId;
    private String employeeName;
    private String phoneNumber;
}
