package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Table(name="employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name="employee_number")
    private long employeeNumber;
    @Column(name="status")
    private Integer status;
    @Column(name="residence")
    private String areaOfResidence;
    @Column(name="national_id")
    private long nationalID;
    @ManyToOne(fetch = FetchType.LAZY) // Use LAZY to optimize queries
    @JoinColumn(name = "user_id", nullable = false) // Foreign key
    private UserEntity userEntity;

}
