package com.alpha.alpha_help_desk_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="employee_invoice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;

    @Column(name = "employee_rate", nullable = false)
    private  double employeeRate;

    @Column(name = "company_rate", nullable = false)
    private  double companyRate;

    @Column(name = "security_deposit", nullable = false)
    private  double securityDeposit;

    @Column(name = "bonus_amt", nullable = false)
    private  double bonusAmt;

    @Column(name = "training_amt", nullable = false)
    private  double trainingAmt;

    @Column(name = "deductions", nullable = false)
    private  double deductions;

    @Column(name = "week_no", nullable = false)
    private  Integer weekNo;

    @Column(name = "month", nullable = false)
    private  String month;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_from", nullable = false)
    private LocalDate dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date_to", nullable = false)
    private LocalDate dateTo;

    @Column(name = "forex_rate", nullable = false)
    private Double forexRate;

    @Column(name = "hours_worked", nullable = false)
    private Double hoursWorked;

    @Column(name = "advance", nullable = false)
    private Double advance;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "expected_salary_usd", nullable = false)
    private Double usdExpectedSalary;

    @Column(name = "expectedSalary", nullable = false)
    private Double expectedSalary;

    @Column(name = "actualSalary", nullable = false)
    private Integer actualSalary;


    @Column(name = "status", nullable = false)
    private Integer status;


    @Column(name = "company_gross_salary_usd", nullable = false)
    private Double companyGrossSalaryUsd;

    @Column(name = "company_gross_salary", nullable = false)
    private Double companyGrossSalary;

    @Column(name = "company_net_salary", nullable = false)
    private Double companyNetSalary;


    @ManyToOne(fetch = FetchType.LAZY) // Use LAZY to optimize queries
    @JoinColumn(name = "employee_id", nullable = false) // Foreign key
    private EmployeeEntity employeeEntity;

}
