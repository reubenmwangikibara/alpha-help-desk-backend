package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.InvoicePayment;
import com.alpha.alpha_help_desk_backend.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("fetch")
    public BaseApiResponse fetchInvoices ()throws Exception {

        return invoiceService.fetchInvoices();
    }
    @PostMapping("payment")
    public BaseApiResponse invoicePayment (@RequestBody @Valid InvoicePayment invoicePayment)throws Exception {

        return invoiceService.invoicePayment(invoicePayment);
    }
}
