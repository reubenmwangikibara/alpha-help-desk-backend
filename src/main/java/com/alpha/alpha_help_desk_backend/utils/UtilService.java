package com.alpha.alpha_help_desk_backend.utils;

import java.security.SecureRandom;

public class UtilService {

    public static  int employeeNumberGenerator()
    {
        int employeeNumber = 0;
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            employeeNumber = secureRandom.nextInt(100000); // Generates numbers between 0 and 99
        }
        return employeeNumber;
    }
}
