package com.alpha.alpha_help_desk_backend.dto.response;

import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import lombok.*;

import java.time.LocalDate;

@Data
public class EmployeeInvoiceResponseDTO {
    private long tid;
    private double rate;
    private double securityDeposit;
    private double bonusAmt;
    private double trainingAmt;
    private double deductions;
    private Integer weekNo;
    private String month;
    private String dateFrom;
    private String dateTo;
    private Double forexRate;
    private Double hoursWorked;
    private Double advance;
    private Double salary;
    private Double usdExpectedSalary;
    private Double expectedSalary;
    private Double actualSalary;
    private Integer status;
    private String employeeID; // Only include necessary employee fields

    public EmployeeInvoiceResponseDTO(EmployeeInvoiceEntity invoice) {
        this.tid = invoice.getTid();
        this.rate = invoice.getRate();
        this.securityDeposit = invoice.getSecurityDeposit();
        this.bonusAmt = invoice.getBonusAmt();
        this.trainingAmt = invoice.getTrainingAmt();
        this.deductions = invoice.getDeductions();
        this.weekNo = invoice.getWeekNo();
        this.month = invoice.getMonth();
        this.dateFrom = String.valueOf(invoice.getDateFrom());
        this.dateTo = String.valueOf(invoice.getDateTo());
        this.forexRate = invoice.getForexRate();
        this.hoursWorked = invoice.getHoursWorked();
        this.advance = invoice.getAdvance();
        this.salary = invoice.getSalary();
        this.usdExpectedSalary = invoice.getUsdExpectedSalary();
        this.expectedSalary = invoice.getExpectedSalary();
        this.actualSalary = invoice.getActualSalary();
        this.status = invoice.getStatus();

        // Prevent Lazy Loading issues
        if (invoice.getEmployeeEntity() != null) {
            this.employeeID = String.valueOf(invoice.getEmployeeEntity().getEmployeeNumber()); // Only include name
        }
    }
}
