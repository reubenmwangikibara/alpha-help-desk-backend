package com.alpha.alpha_help_desk_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="company_income_view")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIncomeView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;

    @Column(name = "company_salary_usd", nullable = false)
    private Double companySalaryUsd;

    @Column(name = "company_salary_ksh", nullable = false)
    private Double companySalaryKsh;

    @Column(name = "employee_salary_ksh", nullable = false)
    private Double employeeSalaryKsh;

    @Column(name = "expected_income", nullable = false)
    private Double expectedIncome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;
}
