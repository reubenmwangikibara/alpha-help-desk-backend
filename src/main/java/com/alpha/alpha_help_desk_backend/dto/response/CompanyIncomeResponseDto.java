package com.alpha.alpha_help_desk_backend.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIncomeResponseDto {
    Long tid;
    private double receivedAmount;
    private double expectedIncome;
    private double forexProfit;
    private double netIncome;
    private double actualForex;
    private String dateFrom;
    private String dateTo;
}
