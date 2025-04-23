package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.InvoicePayment;

public interface InvoiceService {

    BaseApiResponse fetchInvoices () throws Exception;
    BaseApiResponse invoicePayment (InvoicePayment invoicePayment) throws Exception;

}
