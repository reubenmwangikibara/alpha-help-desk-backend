package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;
import com.alpha.alpha_help_desk_backend.dto.request.InvoicePayment;

public interface EmployeeInvoiceService {

    BaseApiResponse addEmployeeInvoice (EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception;

    BaseApiResponse fetchEmployeeInvoice (Long invoiceID,Long employeeID,Integer status,String month,String dateFrom,String dateTo) throws Exception;


    BaseApiResponse updateEmployeeInvoice (Long invoiceID, EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception;

    BaseApiResponse deactivateInvoice (Long invoiceID) throws Exception;

    BaseApiResponse invoicePayment (InvoicePayment invoicePayment) throws Exception;

}
