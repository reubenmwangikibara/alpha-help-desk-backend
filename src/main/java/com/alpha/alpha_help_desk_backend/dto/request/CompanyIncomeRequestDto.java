package com.alpha.alpha_help_desk_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyIncomeRequestDto implements Serializable {

    @NotNull(message = "receivedAmount.required")
    private Double receivedAmount;
    @NotBlank(message = "dateFrom.required")
    private String dateFrom;
    @NotBlank(message = "dateTo.required")
    private String dateTo;

}
