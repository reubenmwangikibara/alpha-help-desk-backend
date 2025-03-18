package com.alpha.alpha_help_desk_backend.utils;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.GeneralApiListResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseService {

    public BaseApiResponse buildSuccessApiResponseDto(List<?> response, int count) {

       return GeneralApiListResponse
                .builder()
               .data(response)
               .message("Success")
               .status(200)
               .build();
    }

}
