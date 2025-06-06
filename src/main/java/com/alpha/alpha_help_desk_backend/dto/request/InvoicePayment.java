package com.alpha.alpha_help_desk_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePayment {

    @NotNull(message = "invoiceID  is required")
    private Long invoiceID;

    @NotNull(message = "forex  is required")
    private Long forex;

    @NotNull(message = "comment  is required")
    private String comments;

}
