package com.alpha.alpha_help_desk_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String expiresAt;
    private String duration;
}
