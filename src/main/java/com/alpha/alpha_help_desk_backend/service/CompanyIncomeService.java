package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.CompanyIncomeRequestDto;

public interface CompanyIncomeService {

    BaseApiResponse addCompanyIncome(CompanyIncomeRequestDto companyIncomeRequestDto) throws Exception;
}
