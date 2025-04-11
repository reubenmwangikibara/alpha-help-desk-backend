package com.alpha.alpha_help_desk_backend.mapper;

import com.alpha.alpha_help_desk_backend.dto.response.CompanyIncomeResponseDto;
import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;

import java.util.List;
import java.util.stream.Collectors;

public class WeeklyCompanyIncomeMapper {

    public static CompanyIncomeResponseDto toDTO(WeeklyCompanyIncome entity) {
        CompanyIncomeResponseDto dto = new CompanyIncomeResponseDto();
        dto.setTid(entity.getTid());
        dto.setReceivedAmount(entity.getReceivedAmount());
        dto.setExpectedIncome(entity.getExpectedIncome());
        dto.setForexProfit(entity.getForexProfit());
        dto.setNetIncome(entity.getNetIncome());
        dto.setActualForex(entity.getActualForex());
        dto.setDateFrom(String.valueOf(entity.getDateFrom()));
        dto.setDateTo(String.valueOf(entity.getDateTo()));
        return dto;
    }

    public static List<CompanyIncomeResponseDto> toList(List<WeeklyCompanyIncome> entities) {
        return entities.stream()
                .map(WeeklyCompanyIncomeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
