package com.alpha.alpha_help_desk_backend.controller;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.ExpenseDetailsDto;
import com.alpha.alpha_help_desk_backend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "api/v1/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/add")
    public BaseApiResponse addExpense (@RequestBody @Valid ExpenseDetailsDto expenseDetailsDto) throws Exception {

        return expenseService.saveExpenseDetails(expenseDetailsDto);
    }

    @GetMapping("/fetch")
    public BaseApiResponse fetchExpense () throws Exception {
        return expenseService.fetchExpense();
    }

    @PostMapping("/update/{tid}")
    public BaseApiResponse updateExpense (@PathVariable Long tid,@RequestBody @Valid ExpenseDetailsDto expenseDetailsDto) throws Exception {

        return expenseService.updateExpense(tid,expenseDetailsDto);
    }

    @DeleteMapping("/delete/{tid}")
    public BaseApiResponse deleteExpense (@PathVariable Long tid) throws Exception {

        return expenseService.deleteExpense(tid);
    }
}
