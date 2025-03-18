package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import com.alpha.alpha_help_desk_backend.exceptions.EmployeeNotFoundException;
import com.alpha.alpha_help_desk_backend.service.EmployeeInvoiceService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeInvoiceServiceImpl implements EmployeeInvoiceService {
    private final EmployeeDBUtilService employeeDBUtilService;
    private final ResponseService responseService;

    /**
     * @param employeeInvoiceRequestDto
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse addEmployeeInvoice(EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception {

        //let's check if the employee exist
        var employeeDetails = employeeDBUtilService.getEmployeeByEmployeeEmployeeID(Long.valueOf(employeeInvoiceRequestDto.getEmployeeId()));

        if(employeeDetails.isEmpty())
        {
            throw new EmployeeNotFoundException("Employee not found");
        }
        var empDetails = employeeDetails.get();
        //adding employee invoice details
        var salary = employeeInvoiceRequestDto.getHoursWorked() * employeeInvoiceRequestDto.getRate();

        var usdExpectedSalary = salary - employeeInvoiceRequestDto.getSecurityDeposit() + employeeInvoiceRequestDto.getBonusAmt() + employeeInvoiceRequestDto.getTrainingAmt();
        log.info("USD EXPECTED SALARY: {}", usdExpectedSalary);
        var actualSalary = usdExpectedSalary*employeeInvoiceRequestDto.getForexRate();
        var finalSalary = actualSalary - employeeInvoiceRequestDto.getAdvance();
        log.info("Salary {} security deposit {} bonus amt {} training amt  {} ",salary,employeeInvoiceRequestDto.getSecurityDeposit(),employeeInvoiceRequestDto.getBonusAmt(),employeeInvoiceRequestDto.getTrainingAmt());

        log.info("Expected Salary {} ,Actual Salary {} final Salary {}",usdExpectedSalary, actualSalary,finalSalary);
        var invoiceDetails = EmployeeInvoiceEntity
                .builder()
                .employeeId((int) empDetails.getTid())
                .weekNo((int) employeeInvoiceRequestDto.getWeekNo())
                .dateFrom(employeeInvoiceRequestDto.getDateFrom())
                .dateTo(employeeInvoiceRequestDto.getDateTo())
                .hoursWorked(employeeInvoiceRequestDto.getHoursWorked())
                .rate(employeeInvoiceRequestDto.getRate())
                .salary(salary)
                .bonusAmt(employeeInvoiceRequestDto.getBonusAmt())
                .trainingAmt(employeeInvoiceRequestDto.getTrainingAmt())
                .securityDeposit(employeeInvoiceRequestDto.getSecurityDeposit())
                .advance(employeeInvoiceRequestDto.getAdvance())
                .expectedSalary(UtilService.round(usdExpectedSalary* employeeInvoiceRequestDto.getForexRate(),2))
                .actualSalary(UtilService.round(actualSalary,2))
                .forexRate(employeeInvoiceRequestDto.getForexRate())
                .month(employeeInvoiceRequestDto.getMonth())
                .usdExpectedSalary(usdExpectedSalary)
//                .status(0)
                .build();

        var savedInvoice = employeeDBUtilService.saveEmployeeInvoiceDetails(invoiceDetails);

        return responseService.buildSuccessApiResponseDto(List.of(savedInvoice),1);

    }
}
