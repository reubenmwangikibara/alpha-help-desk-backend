package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="expense")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "no_of_days", nullable = false)
    private Long noOfDays;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "week_no", nullable = false)
    private int weekNo;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "active_status", nullable = false)
    private int activeStatus;
}
