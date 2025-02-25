package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "Last_name")
    private String Last_name;
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Phone_number")
    private long Phone_number;
    @Column(name = "Role")
    private String Role;
    @Column(name = "status")
    private Boolean status;


}
