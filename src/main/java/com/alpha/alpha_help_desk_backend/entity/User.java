package com.alpha.alpha_help_desk_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
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
    private Boolean status = true;


    public User(long tid, String last_name,
                String first_name,
                String username,
                String password,
                long phone_number,
                String role,
                Boolean status) {
        this.tid = tid;
        Last_name = last_name;
        this.first_name = first_name;
        Username = username;
        Password = password;
        Phone_number = phone_number;
        Role = role;
        this.status = status;
    }

    public long getTid() {

        return tid;
    }

    public void setTid(long tid) {

        this.tid = tid;
    }

    public String getFirst_name() {

        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(long phone_number) {
        Phone_number = phone_number;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
