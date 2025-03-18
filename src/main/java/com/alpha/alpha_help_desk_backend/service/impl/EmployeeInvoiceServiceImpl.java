package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.EmployeeInvoiceRequestDto;
import com.alpha.alpha_help_desk_backend.dto.response.EmployeeInvoiceResponseDTO;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import com.alpha.alpha_help_desk_backend.exceptions.EmployeeNotFoundException;
import com.alpha.alpha_help_desk_backend.exceptions.InvoiceDetailsExistException;
import com.alpha.alpha_help_desk_backend.service.EmployeeInvoiceService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


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
        var employeeDetails = employeeDBUtilService.getEmployeeByEmployeeEmployeeID(employeeInvoiceRequestDto.getEmployeeId());

        if(employeeDetails.isEmpty())
        {
            throw new EmployeeNotFoundException("Employee not found");
        }
        var empDetails = employeeDetails.get();
        //adding employee invoice details
        var salary = employeeInvoiceRequestDto.getHoursWorked() * employeeInvoiceRequestDto.getRate();

        //check if the employee record for the same week and month exists

        var invoiceExist = employeeDBUtilService.getEmployeeByEmployInvoiceDetails(employeeInvoiceRequestDto.getEmployeeId(),employeeInvoiceRequestDto.getWeekNo(),employeeInvoiceRequestDto.getMonth());
        if(!invoiceExist.isEmpty())
        {
            throw new InvoiceDetailsExistException("Invoice already exists");
        }
        var usdExpectedSalary = salary - employeeInvoiceRequestDto.getSecurityDeposit() + employeeInvoiceRequestDto.getBonusAmt() + employeeInvoiceRequestDto.getTrainingAmt();
        log.info("USD EXPECTED SALARY: {}", usdExpectedSalary);
        var actualSalary = usdExpectedSalary*employeeInvoiceRequestDto.getForexRate();
        var finalSalary = actualSalary - employeeInvoiceRequestDto.getAdvance();
        log.info("Salary {} security deposit {} bonus amt {} training amt  {} ",salary,employeeInvoiceRequestDto.getSecurityDeposit(),employeeInvoiceRequestDto.getBonusAmt(),employeeInvoiceRequestDto.getTrainingAmt());

        log.info("Expected Salary {} ,Actual Salary {} final Salary {}",usdExpectedSalary, actualSalary,finalSalary);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = (employeeInvoiceRequestDto.getDateFrom() != null) ? LocalDate.parse((CharSequence) employeeInvoiceRequestDto.getDateFrom(), formatter) : null;
        LocalDate toDate = (employeeInvoiceRequestDto.getDateTo() != null) ? LocalDate.parse((CharSequence) employeeInvoiceRequestDto.getDateTo(), formatter) : null;

        var invoiceDetails = EmployeeInvoiceEntity
                .builder()
                .employeeEntity(empDetails)
                .weekNo(Math.toIntExact(employeeInvoiceRequestDto.getWeekNo()))
                .dateFrom(fromDate)
                .dateTo(toDate)
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
                .usdExpectedSalary(UtilService.round(usdExpectedSalary,2))
                .status(0)
                .build();


        var savedInvoice = employeeDBUtilService.saveEmployeeInvoiceDetails(invoiceDetails);

        var finalInvoiceDetails = Optional.ofNullable(savedInvoice)
                .map(EmployeeInvoiceResponseDTO::new)
                .orElse(null);  // Handle potential null values

        return responseService.buildSuccessApiResponseDto(List.of(finalInvoiceDetails),1);

    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchEmployeeInvoice(Long employeeId,Integer status,String month,String dateFrom,String dateTo ) throws Exception {
        // Convert String to LocalDate
        log.info("Fetching employee invoice for employee id {}",employeeId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = (dateFrom != null) ? LocalDate.parse(dateFrom, formatter) : null;
        LocalDate toDate = (dateTo != null) ? LocalDate.parse(dateTo, formatter) : null;
        var invoices = employeeDBUtilService.fetchInvoices(null,null,null,null,null);
        log.info("Fetched employee invoices {}",invoices);

        var finalInvoiceDetails = invoices.stream().map(EmployeeInvoiceResponseDTO::new).toList();

        return responseService.buildSuccessApiResponseDto(finalInvoiceDetails,1);
    }
}
