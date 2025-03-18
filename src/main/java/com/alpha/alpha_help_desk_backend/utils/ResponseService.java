package com.alpha.alpha_help_desk_backend.utils;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.GeneralApiListResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseService {

    public BaseApiResponse buildSuccessApiResponseDto(List<?> response, long count) {

       return new  GeneralApiListResponse(200,"success",response,count);
    }

}
