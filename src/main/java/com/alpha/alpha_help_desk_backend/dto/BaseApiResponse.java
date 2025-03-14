package com.alpha.alpha_help_desk_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseApiResponse {
    protected Object data;
    protected int status;
    protected String message;
    protected List<FieldErrorDto> errors = new ArrayList<>();


}
