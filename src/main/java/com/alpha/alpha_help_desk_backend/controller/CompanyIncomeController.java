package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.CompanyIncomeRequestDto;
import com.alpha.alpha_help_desk_backend.service.CompanyIncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/company-income")
@RequiredArgsConstructor
public class CompanyIncomeController {

    private final CompanyIncomeService companyIncomeService;

    @PostMapping("/add")
    public BaseApiResponse addEmployeeDetails (@Valid @RequestBody CompanyIncomeRequestDto companyIncomeRequestDto) throws Exception{

        return companyIncomeService.addCompanyIncome(companyIncomeRequestDto);
    }
    @PostMapping("/fetch")
    public BaseApiResponse fetchIncomeDetails () throws Exception {

        return companyIncomeService.fetchCompanyIncome(null, null);
    }
}
