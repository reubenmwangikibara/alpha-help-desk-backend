package com.alpha.alpha_help_desk_backend.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceDetailsExistException extends Exception {
    final String message;
    @Override
    public String getMessage() {return message;}
}
