package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;
import com.alpha.alpha_help_desk_backend.service.EmployeeInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "api/v1/employee-invoice")
@RequiredArgsConstructor
public class EmployeeInvoiceController {
    private final EmployeeInvoiceService employeeInvoiceService;

    @PostMapping("/add")
    public BaseApiResponse addEmployeeInvoice (@RequestBody @Valid EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception {

        return employeeInvoiceService.addEmployeeInvoice(employeeInvoiceRequestDto);
    }

    @GetMapping("/fetch")
    public BaseApiResponse fetchEmployeeInvoice (
            @RequestParam(required = false) Long invoiceID,
            @RequestParam(required = false) Long employeeID,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String dateFrom, // String input
            @RequestParam(required = false) String dateTo   // String input
    ) throws Exception {

        return employeeInvoiceService.fetchEmployeeInvoice(invoiceID,employeeID,status,month,dateFrom,dateTo);
    }

    @PutMapping("update/{tid}")
    public BaseApiResponse updateEmployeeInvoice (@PathVariable Long tid,@RequestBody @Valid EmployeeInvoiceRequestDto employeeInvoiceRequestDto)throws Exception {

        return employeeInvoiceService.updateEmployeeInvoice(tid,employeeInvoiceRequestDto);
    }
    @PostMapping("deactivate/{tid}")
    public BaseApiResponse deactivateInvoice (@PathVariable Long tid)throws Exception {

        return employeeInvoiceService.deactivateInvoice(tid);
    }

}
