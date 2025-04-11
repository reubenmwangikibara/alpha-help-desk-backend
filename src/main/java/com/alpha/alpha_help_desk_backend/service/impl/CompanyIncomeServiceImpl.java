package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.CompanyIncomeRequestDto;
import com.alpha.alpha_help_desk_backend.dto.response.CompanyIncomeResponseDto;
import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;
import com.alpha.alpha_help_desk_backend.mapper.WeeklyCompanyIncomeMapper;
import com.alpha.alpha_help_desk_backend.service.CompanyIncomeService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.db.IncomeDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyIncomeServiceImpl implements CompanyIncomeService {
    private final IncomeDBUtilService incomeDBUtilService;
    private final ResponseService responseService;

    /**
     * @param companyIncomeRequestDto
     * @return
     */
    @Override
    public BaseApiResponse addCompanyIncome(CompanyIncomeRequestDto companyIncomeRequestDto) throws Exception {
        log.info("Add company income request: {}", new Gson().toJson(companyIncomeRequestDto));
        //fetch salary details
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate finalFromDate = (companyIncomeRequestDto.getDateFrom() != null) ? LocalDate.parse(companyIncomeRequestDto.getDateFrom(), formatter) : null;
        LocalDate finalToDate = (companyIncomeRequestDto.getDateTo() != null) ? LocalDate.parse(companyIncomeRequestDto.getDateTo(), formatter) : null;
        var incomeDetails = incomeDBUtilService.getWeeklyIncome(finalFromDate, finalToDate);
        log.info("Company income details:  date from {} date to {} : {}", companyIncomeRequestDto.getDateFrom() ,companyIncomeRequestDto.getDateTo(),incomeDetails);
        var forexProfit = companyIncomeRequestDto.getReceivedAmount() - incomeDetails.getCompanySalaryKsh();
        var netProfit = companyIncomeRequestDto.getReceivedAmount() - incomeDetails.getEmployeeSalaryKsh();
        var actualForex = companyIncomeRequestDto.getReceivedAmount() / incomeDetails.getCompanySalaryUsd();
        log.info("Actual Forex {} Forex profit: {}  Actual Profit {}", actualForex,forexProfit,actualForex);
        var entity = WeeklyCompanyIncome.builder()
                .receivedAmount(companyIncomeRequestDto.getReceivedAmount())
                .expectedIncome(incomeDetails.getCompanySalaryKsh())
                .netIncome(netProfit)
                .forexProfit(forexProfit)
                .actualForex(actualForex)
                .dateFrom(finalFromDate)
                .dateTo(finalToDate)
                .build();
        var savedIncomeDetails = incomeDBUtilService.saveWeeklyIncome(entity);

        return responseService.buildSuccessApiResponseDto(List.of(savedIncomeDetails),1);
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse fetchCompanyIncome(String dateFrom,String dateTo) throws Exception {
        var incomeDetails = incomeDBUtilService.fetchCompanyIncome(null,null);
        List<CompanyIncomeResponseDto> dtoList = WeeklyCompanyIncomeMapper.toList(incomeDetails);
        return responseService.buildSuccessApiResponseDto(List.of(dtoList),incomeDetails.size());

    }
}
