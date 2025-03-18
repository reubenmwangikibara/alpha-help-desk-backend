package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;

public interface EmployeeInvoiceService {

    BaseApiResponse addEmployeeInvoice (EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception;

    BaseApiResponse fetchEmployeeInvoice (Long employeeId,Integer status,String month,String dateFrom,String dateTo) throws Exception;

}
