package com.alpha.alpha_help_desk_backend.utils;

import com.alpha.alpha_help_desk_backend.dto.response.SalaryCalc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
@Slf4j
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

        return BigDecimal.valueOf(value)
                .setScale(places, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return (date!= null) ? LocalDate.parse((CharSequence) date, formatter) : null;
    }

    public SalaryCalc empSalaryCalculator(double salary,double securityDeposit,double bonus,double trainingAmount ,double forexRate,double deductions,double advance)
    {
        var usdExpectedSalary = usdExpectedSalary(salary,bonus,trainingAmount,securityDeposit);
        var actualSalary = usdExpectedSalary * forexRate;
        log.info("EMP DEDUCTIONS {} EMP ADVANCE {}", deductions, advance);
        var finalSalary = actualSalary - deductions - advance;
        return SalaryCalc.builder()
                .usdExpectedSalary(usdExpectedSalary)
                .finalSalary(finalSalary)
                .actualSalary(actualSalary).build();
    }

    public  double usdExpectedSalary(double salary,double bonus,double trainingAmount,double securityDeposit)
    {
        log.info("Salary {} security deposit {} bonus amt {} training amt  {} ",salary,securityDeposit,bonus,trainingAmount);

        var usdExpectedSalary = salary - securityDeposit + bonus + trainingAmount;
        log.info("USD EXPECTED SALARY: {}", usdExpectedSalary);
        return usdExpectedSalary;
    }
    public SalaryCalc companySalaryCalculator(double salary,double securityDeposit,double bonus,double trainingAmount ,double forexRate)
    {
        var usdExpectedSalary = usdExpectedSalary(salary,bonus,trainingAmount,securityDeposit);
        var actualSalary = usdExpectedSalary * forexRate;
        return SalaryCalc.builder()
                .usdExpectedSalary(usdExpectedSalary)
                .actualSalary(actualSalary).build();
    }

}
