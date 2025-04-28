package com.alpha.alpha_help_desk_backend.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
