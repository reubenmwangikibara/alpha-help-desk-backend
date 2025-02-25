package com.alpha.alpha_help_desk_backend.DTO;

public class UserDTO {
    private long tid;
    private String first_name;
    private String Last_name;
    private String Username;
    private String Password;
    private long Phone_number;
    private String Role;
    private Boolean status = true;

    //constructors

    public UserDTO(long tid,
                   String first_name,
                   String last_name,
                   String username, String password,
                   long phone_number,
                   String role,
                   Boolean status) {
        this.tid = tid;
        this.first_name = first_name;
        Last_name = last_name;
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
