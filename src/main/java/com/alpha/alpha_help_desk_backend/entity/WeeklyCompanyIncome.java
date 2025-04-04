package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="company_weekly_income")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyCompanyIncome implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;

    @Column(name = "received_amount", nullable = false)
    private double receivedAmount;

    @Column(name = "expected_income", nullable = false)
    private double expectedIncome;

    @Column(name = "forex_profit", nullable = false)
    private double forexProfit;

    @Column(name = "net_income", nullable = false)
    private double netIncome;

    @Column(name = "actual_forex", nullable = false)
    private double actualForex;

    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;



}
