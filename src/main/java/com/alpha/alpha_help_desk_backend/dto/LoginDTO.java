package com.alpha.alpha_help_desk_backend.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO  implements Serializable {
    private String userName;
    private String password;
}
