package com.alpha.alpha_help_desk_backend.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeNotFoundException extends Exception {

    final String message;
    @Override
    public String getMessage() {return message;}
}
