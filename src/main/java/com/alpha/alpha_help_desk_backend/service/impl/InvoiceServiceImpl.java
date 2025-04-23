package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.configs.ApplicationConfigs;
import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.InvoicePayment;
import com.alpha.alpha_help_desk_backend.service.InvoiceService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.db.EmployeeDBUtilService;
import com.alpha.alpha_help_desk_backend.utils.db.IncomeDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final EmployeeDBUtilService employeeDBUtilService;
    private final ApplicationConfigs applicationConfigs;
    private final IncomeDBUtilService incomeDBUtilService;
    private final ResponseService responseService;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchInvoices() throws Exception {
        var invoices = incomeDBUtilService.fetchInvoiceDetails(null,null);
        return responseService.buildSuccessApiResponseDto(invoices,invoices.size());
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse invoicePayment(InvoicePayment invoicePayment) throws Exception {
        var invoice = employeeDBUtilService.fetchInvoiceByIDAndActiveStatus(invoicePayment.getInvoiceID(),applicationConfigs.getActiveStatus());
        if(invoice.isEmpty()) {
            throw new Exception("Employee invoice with id " + invoicePayment.getInvoiceID() + " does not exist");
        }
        var invoiceDetails = invoice.get();
        log.info("Invoice Payment {}",invoiceDetails);
        var expectedSalary = invoiceDetails.getExpectedSalary();
//        var salaryDetails = new UtilService().salaryCalculator(expectedSalary,invoiceDetails.getForexRate(),0);
//        var employeeSalary  = new EmployeeSalary();
//        employeeSalary.setDeductions(0);
//        employeeSalary.setAmount(salaryDetails.getFinalSalary());
//        employeeSalary.setActualAmount(salaryDetails.getFinalSalary());
//        employeeSalary.setInvoiceID(invoiceDetails.getTid());

        return null;
    }
}
