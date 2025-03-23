package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import com.alpha.alpha_help_desk_backend.repository.EmployeeInvoiceRepository;
import com.alpha.alpha_help_desk_backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDBUtilService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeInvoiceRepository employeeInvoiceRepository;

    public Optional<EmployeeEntity> getEmployeeByEmployeeEmployeeID(Long employeeID) {

        return employeeRepository.findEmployeeEntitiesByTid(employeeID);

    }

    public EmployeeEntity saveEmployeeDetails(EmployeeEntity employee) {

        return employeeRepository.save(employee);
    }

    public EmployeeInvoiceEntity saveEmployeeInvoiceDetails(EmployeeInvoiceEntity employeeInvoiceEntity) {

        return employeeInvoiceRepository.save(employeeInvoiceEntity);
    }

    public List<EmployeeInvoiceEntity> getEmployeeByEmployInvoiceDetails(Long employeeID, Integer weekNo, String month) {

        return employeeInvoiceRepository.findEmployInvoiceEntity(employeeID,weekNo,month);

    }

    public List<EmployeeInvoiceEntity> fetchInvoices(Long employeeId, Integer status, String month, Date dateFrom, Date dateTo) {

        return employeeInvoiceRepository.fetchInvoices(employeeId, status, month, dateFrom, dateTo);

    }

    public Optional<EmployeeInvoiceEntity> fetchInvoiceByID(Long invoiceID) {
        log.info("Fetching employee invoice by ID {}", invoiceID);
        return employeeInvoiceRepository.findById(invoiceID);

    }

}
