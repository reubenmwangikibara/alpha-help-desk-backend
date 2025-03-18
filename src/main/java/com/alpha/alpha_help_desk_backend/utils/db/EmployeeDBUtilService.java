package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import com.alpha.alpha_help_desk_backend.repository.EmployeeInvoiceRepository;
import com.alpha.alpha_help_desk_backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
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


}
