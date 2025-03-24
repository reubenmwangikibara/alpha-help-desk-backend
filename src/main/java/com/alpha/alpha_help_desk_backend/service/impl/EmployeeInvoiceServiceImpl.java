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
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

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

        var invoiceDetails = EmployeeInvoiceEntity
                .builder()
                .employeeEntity(empDetails)
                .weekNo(Math.toIntExact(employeeInvoiceRequestDto.getWeekNo()))
                .dateFrom(UtilService.formatDate(employeeInvoiceRequestDto.getDateFrom()))
                .dateTo(UtilService.formatDate(employeeInvoiceRequestDto.getDateTo()))
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

        assert finalInvoiceDetails != null;

        return responseService.buildSuccessApiResponseDto(List.of(finalInvoiceDetails),1);

    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchEmployeeInvoice(Long invoiceID,Long employeeID,Integer status,String month,String dateFrom,String dateTo ) throws Exception {
        // Convert String to LocalDate
        log.info("Fetching employee invoice for invoiceID {} employeeID {} status {} month {} date from {} date to {}",invoiceID,employeeID,status,month,dateFrom,dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate finalFromDate = (dateFrom != null) ? LocalDate.parse(dateFrom, formatter) : null;
        LocalDate finalToDate = (dateTo != null) ? LocalDate.parse(dateTo, formatter) : null;
        var finalMonth = month != null ? month.toUpperCase() : null;
        var invoices = employeeDBUtilService.fetchInvoices(invoiceID,employeeID,status,finalMonth,finalFromDate,finalToDate);
        log.info("Fetched employee invoices {}",invoices);

        var finalInvoiceDetails = invoices.stream().map(EmployeeInvoiceResponseDTO::new).toList();

        return responseService.buildSuccessApiResponseDto(finalInvoiceDetails, finalInvoiceDetails.size());
    }

    /**
     * @param invoiceID
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse updateEmployeeInvoice(Long invoiceID, EmployeeInvoiceRequestDto employeeInvoiceRequestDto) throws Exception {
        log.info("Updating employee invoice for employee id {}",invoiceID);
        //let get the employee details
        var optionalInvoice = employeeDBUtilService.fetchInvoiceByID(invoiceID);
        if (optionalInvoice.isEmpty()) {
            throw new Exception("Employee invoice with id " + invoiceID + " does not exist");
        }
        EmployeeInvoiceEntity existingInvoice = optionalInvoice.get();

        // Map DTO fields to existing entity (ignoring null values)
         modelMapper.map(employeeInvoiceRequestDto, existingInvoice);
         existingInvoice.setDateFrom(UtilService.formatDate(employeeInvoiceRequestDto.getDateFrom()));
         existingInvoice.setDateTo(UtilService.formatDate(employeeInvoiceRequestDto.getDateTo()));
        // Save the updated entity
        var updatedRecord = employeeDBUtilService.saveEmployeeInvoiceDetails(existingInvoice);
        log.info("Updated Employee Invoice {}",updatedRecord);
        var finalInvoiceDetails = Optional.ofNullable(updatedRecord)
                .map(EmployeeInvoiceResponseDTO::new)
                .orElse(null);

        assert finalInvoiceDetails != null;
        return responseService.buildSuccessApiResponseDto(List.of(finalInvoiceDetails),1);
    }
}
