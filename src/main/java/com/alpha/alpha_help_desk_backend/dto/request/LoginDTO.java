package com.alpha.alpha_help_desk_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO  implements Serializable {
    @NotBlank(message = "userName date is required")
    private String userName;
    @NotBlank(message = "password date is required")
    private String password;
}
