package com.alpha.alpha_help_desk_backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative");

        return new BigDecimal(value)
                .setScale(places, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return (date!= null) ? LocalDate.parse((CharSequence) date, formatter) : null;
    }
}
