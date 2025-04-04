package com.alpha.alpha_help_desk_backend.utils.db;

import com.alpha.alpha_help_desk_backend.entity.ExpenseEntity;
import com.alpha.alpha_help_desk_backend.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExpensesDBUtilService {

    private final ExpenseRepository expenseRepository;

    public Optional<ExpenseEntity> getExpenseDetails(Long expenseID) {

        return expenseRepository.findExpenseEntitiesByTid(expenseID);

    }

    public Optional<ExpenseEntity> checkIfExists(Long weekNo, String name) {

        return expenseRepository.findExpenseEntitiesByActiveStatusAndWeekNoAndName(1,weekNo,name);

    }

    public ExpenseEntity saveExpenseDetails(ExpenseEntity expense) {

        return expenseRepository.save(expense);

    }
    public List<ExpenseEntity> fetchAllExpenseDetails() {

        return expenseRepository.findAll();

    }
}
