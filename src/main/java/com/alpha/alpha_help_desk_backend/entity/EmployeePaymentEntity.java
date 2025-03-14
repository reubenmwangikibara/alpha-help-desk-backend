package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="employee_payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;

    @Column(name = "employee_id", nullable = false)
    private  Integer employeeId;

    @Column(name = "rate", nullable = false)
    private  double rate;

    @Column(name = "security_deposit", nullable = false)
    private  double securityDeposit;

    @Column(name = "bonus_amt", nullable = false)
    private  double bonusAmt;

    @Column(name = "training_amt", nullable = false)
    private  double trainingAmt;

    @Column(name = "deductions", nullable = false)
    private  double deductions;

    @Column(name = "week_no", nullable = false)
    private  double weekNo;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "forex_rate", nullable = false)
    private Double forexRate;

    @Column(name = "hours_worked", nullable = false)
    private Double hoursWorked;

    @Column(name = "status", nullable = false)
    private Integer status;

}
