package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "Last_name")
    private String lastName;
    @Column(name = "Username")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "Phone_number")
    private long phoneNumber;
    @Column(name = "Role")
    private String role;
    @Column(name = "status")
    private Integer status;


}
