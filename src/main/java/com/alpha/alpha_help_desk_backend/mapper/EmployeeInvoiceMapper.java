package com.alpha.alpha_help_desk_backend.mapper;

import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import lombok.*;

@Data
public class EmployeeInvoiceMapper {
    private long tid;
    private double employeeRate;
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
    private Integer actualSalary;
    private Integer status;
    private Double companyGrossSalaryUsd;
    private Double companyGrossSalary;
    private String employeeID; // Only include necessary employee fields

    public EmployeeInvoiceMapper(EmployeeInvoiceEntity invoice) {
        this.tid = invoice.getTid();
        this.employeeRate = invoice.getEmployeeRate();
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
        this.companyGrossSalaryUsd = invoice.getCompanyGrossSalaryUsd();
        this.companyGrossSalary = invoice.getCompanyGrossSalary();

        // Prevent Lazy Loading issues
        if (invoice.getEmployeeEntity() != null) {
            this.employeeID = String.valueOf(invoice.getEmployeeEntity().getTid()); // Only include name
        }
    }
}
