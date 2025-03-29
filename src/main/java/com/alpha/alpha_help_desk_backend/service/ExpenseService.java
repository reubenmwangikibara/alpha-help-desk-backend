package com.alpha.alpha_help_desk_backend.service;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.ExpenseDetailsDto;

public interface ExpenseService {

    BaseApiResponse saveExpenseDetails(ExpenseDetailsDto expenseDetailsDto) throws Exception;
    BaseApiResponse fetchExpense();
    BaseApiResponse updateExpense(Long tid,ExpenseDetailsDto expenseDetailsDto) throws Exception;
    BaseApiResponse deleteExpense(Long tid) throws Exception;

}
