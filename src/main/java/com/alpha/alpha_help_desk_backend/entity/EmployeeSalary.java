package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_invoice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;

    @Column(name = "invoice_id", nullable = false)
    private long invoiceID;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "deductions", nullable = false)
    private double deductions;

    @Column(name = "forex_rate", nullable = false)
    private double forexRate;

    @Column(name = "actual_amount", nullable = false)
    private double actualAmount;


}
