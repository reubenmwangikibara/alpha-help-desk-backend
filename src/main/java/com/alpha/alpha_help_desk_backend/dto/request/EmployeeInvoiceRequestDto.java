package com.alpha.alpha_help_desk_backend.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInvoiceRequestDto implements Serializable {

    @NotNull(message = "employeeId.required")
    private  Long employeeId;

    @NotNull(message = "employeeRate.required")
    private  double employeeRate;

    @NotNull(message = "companyRate.required")
    private  double companyRate;

    @NotNull(message = "securityDeposit.required")
    private  double securityDeposit;

    @NotNull(message = "bonusAmt.required")
    private  double bonusAmt;

    @NotNull(message = "trainingAmt.required")
    private  double trainingAmt;

    @NotNull(message = "deductions.required")
    private  double deductions;

    private  Integer weekNo;

    @NotBlank(message = "dateFrom date is required")
    private String dateFrom;

    @NotBlank(message = "dateTo date is required")
    private String dateTo;

    @NotNull(message = "forexRate.required")
    private Double forexRate;

    @NotNull(message = "hoursWorked.required")
    private Double hoursWorked;

    @NotNull(message = "advance.required")
    private Double advance;

    private String month;


}
