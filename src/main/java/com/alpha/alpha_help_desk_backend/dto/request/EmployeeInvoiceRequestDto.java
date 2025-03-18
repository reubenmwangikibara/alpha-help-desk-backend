package com.alpha.alpha_help_desk_backend.dto.request;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInvoiceRequestDto implements Serializable {

    @NotNull(message = "employeeId.required")
    private  Long employeeId;

    @NotNull(message = "rate.required")
    private  double rate;

    @NotNull(message = "securityDeposit.required")
    private  double securityDeposit;

    @NotNull(message = "bonusAmt.required")
    private  double bonusAmt;

    @NotNull(message = "trainingAmt.required")
    private  double trainingAmt;

    @NotNull(message = "deductions.required")
    private  double deductions;

    @NotNull(message = "weekNo.required")
    private  Integer weekNo;

    @NotNull(message = "dateFrom date is required")
    private Date dateFrom;

    @NotNull(message = "dateTo date is required")
    private Date dateTo;

    @NotNull(message = "forexRate.required")
    private Double forexRate;

    @NotNull(message = "hoursWorked.required")
    private Double hoursWorked;

    @NotNull(message = "advance.required")
    private Double advance;

    @NotNull(message = "month.required")
    private String month;
}
