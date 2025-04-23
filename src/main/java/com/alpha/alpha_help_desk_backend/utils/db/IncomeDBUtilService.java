package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.CompanyIncomeView;
import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;
import com.alpha.alpha_help_desk_backend.repository.IncomeViewRepository;
import com.alpha.alpha_help_desk_backend.repository.WeeklyIncomeRepository;
import com.alpha.alpha_help_desk_backend.utils.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeDBUtilService {

    private final IncomeViewRepository incomeViewRepository;
    private final WeeklyIncomeRepository weeklyIncomeRepository;
    private final UtilService utilService;

    public CompanyIncomeView getWeeklyIncome(LocalDate dateFrom, LocalDate dateTo) throws Exception {

        var incomeByDateRange =  incomeViewRepository.findIncomeByDateRange(dateFrom ,dateTo);
        if (incomeByDateRange.isEmpty()) {
            throw new Exception("No Record Found For the provided date Range : Date From  " +dateFrom +" Date TO "+dateTo);
        }
        return incomeByDateRange.get();
    }
    public List<CompanyIncomeView> fetchInvoiceDetails(LocalDate dateFrom, LocalDate dateTo) throws Exception {

        var incomeByDateRange =  incomeViewRepository.findInvoiceDetails(dateFrom ,dateTo);
        if (incomeByDateRange.isEmpty()) {
            throw new Exception("No Record Found For the provided date Range : Date From  " +dateFrom +" Date TO "+dateTo);
        }
        return incomeByDateRange;
    }
    public List<WeeklyCompanyIncome> fetchCompanyIncome(LocalDate dateFrom, LocalDate dateTo) throws Exception {

        if(dateFrom == null || dateTo == null) {
            dateFrom = UtilService.formatDate("2025-01-01");
            dateTo = UtilService.formatDate("2030-01-01");
        }
        var allIncomeDetails =  weeklyIncomeRepository.findAllIncomeDetails(dateFrom ,dateTo);
        if (allIncomeDetails.isEmpty()) {
            throw new Exception("No Record Found For the provided date Range : Date From  " +dateFrom +" Date TO "+dateTo);
        }
        return allIncomeDetails;
    }
    public WeeklyCompanyIncome saveWeeklyIncome(WeeklyCompanyIncome weeklyCompanyIncome) {
        return weeklyIncomeRepository.save(weeklyCompanyIncome);
    }
}
