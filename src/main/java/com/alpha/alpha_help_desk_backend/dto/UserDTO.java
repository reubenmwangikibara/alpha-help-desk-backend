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
    private String first_name;
    private String Last_name;
    private String Username;
    private String Password;
    private long Phone_number;
    private String Role;
    private Boolean status ;


}
