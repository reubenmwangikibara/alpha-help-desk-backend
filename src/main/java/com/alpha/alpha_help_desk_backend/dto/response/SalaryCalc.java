package com.alpha.alpha_help_desk_backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SalaryCalc {
    private double actualSalary;
    private double finalSalary;
    private double usdExpectedSalary;
}
