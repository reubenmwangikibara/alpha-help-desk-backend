package com.alpha.alpha_help_desk_backend.dto;



import lombok.Data;
import lombok.Setter;

import java.util.List;

@Setter
@Data
public class GeneralApiListResponse extends  BaseApiResponse {

    private Long total;
    private List<?> data;

    public GeneralApiListResponse() {
    }

    public GeneralApiListResponse(List<?> data, Long total, Integer status, String message) {
        this.data = data;
        this.total = total;
        this.status = status;
        this.message = message;
    }

    public GeneralApiListResponse(List<?> data, Long total, Integer status, String message, List<FieldErrorDto> errors) {
        this.data = data;
        this.total = total;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    @Override
    public List<?> getData() {
        return data;
    }

}
