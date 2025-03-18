package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;
import com.alpha.alpha_help_desk_backend.service.EmployeeInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/employee-invoice")
@RequiredArgsConstructor
public class EmployeeInvoiceController {
    private final EmployeeInvoiceService employeeInvoiceService;

    @PostMapping("/add")
    public BaseApiResponse addEmployeeInvoice (@RequestBody @Valid EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception {

        return employeeInvoiceService.addEmployeeInvoice(employeeInvoiceRequestDto);
    }
}
