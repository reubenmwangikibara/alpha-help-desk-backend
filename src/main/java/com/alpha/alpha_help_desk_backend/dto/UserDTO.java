package com.alpha.alpha_help_desk_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private long tid;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private long phoneNumber;
    private String role;
    private Boolean status ;


}
