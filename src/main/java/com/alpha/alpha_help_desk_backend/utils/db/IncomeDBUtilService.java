package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.CompanyIncomeView;
import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;
import com.alpha.alpha_help_desk_backend.repository.IncomeViewRepository;
import com.alpha.alpha_help_desk_backend.repository.WeeklyIncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class IncomeDBUtilService {

    private final IncomeViewRepository incomeViewRepository;
    private final WeeklyIncomeRepository weeklyIncomeRepository;

    public CompanyIncomeView getWeeklyIncome(LocalDate dateFrom, LocalDate dateTo) throws Exception {

        var incomeByDateRange =  incomeViewRepository.findIncomeByDateRange(dateFrom ,dateTo);
        if (incomeByDateRange.isEmpty()) {
            throw new Exception("No Record Found For the provided date Range : Date From  " +dateFrom +" Date TO "+dateTo);
        }
        return incomeByDateRange.get();
    }
    public WeeklyCompanyIncome saveWeeklyIncome(WeeklyCompanyIncome weeklyCompanyIncome) {
        return weeklyIncomeRepository.save(weeklyCompanyIncome);
    }
}
