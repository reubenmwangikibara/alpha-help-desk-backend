package com.alpha.alpha_help_desk_backend.service.impl;

import com.alpha.alpha_help_desk_backend.dto.BaseApiResponse;
import com.alpha.alpha_help_desk_backend.dto.request.ExpenseDetailsDto;
import com.alpha.alpha_help_desk_backend.entity.ExpenseEntity;
import com.alpha.alpha_help_desk_backend.service.ExpenseService;
import com.alpha.alpha_help_desk_backend.utils.ResponseService;
import com.alpha.alpha_help_desk_backend.utils.db.ExpensesDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpensesDBUtilService expensesDBUtilService;
    private final ModelMapper modelMapper;
    private final ResponseService responseService;

    /**
     * @return
     */
    @Override
    public BaseApiResponse saveExpenseDetails(ExpenseDetailsDto expenseDetailsDto) throws Exception {
        var expenseDetails = expensesDBUtilService.checkIfExists(expenseDetailsDto.getWeekNo(),expenseDetailsDto.getName());
        if(expenseDetails.isPresent())
        {
            throw new Exception("Expense Details Already Exists");
        }
        var entity = new ExpenseEntity();
        modelMapper.map(expenseDetailsDto, entity);
        var totalAmount = expenseDetailsDto.getPrice() * expenseDetailsDto.getQuantity() * expenseDetailsDto.getNoOfDays();
        entity.setTotalAmount(totalAmount);
        entity.setActiveStatus(1);
        log.info("Saving expense details : {}", entity);
        var savedRecord = expensesDBUtilService.saveExpenseDetails(entity);
        return responseService.buildSuccessApiResponseDto(List.of(savedRecord),1);
    }

    /**
     * @return
     */
    @Override
    public BaseApiResponse fetchExpense() {
        var details = expensesDBUtilService.fetchAllExpenseDetails();
        return responseService.buildSuccessApiResponseDto(details,details.size());
    }

    /**
     * @param expenseDetailsDto
     * @return
     */
    @Override
    public BaseApiResponse updateExpense(Long tid,ExpenseDetailsDto expenseDetailsDto) throws Exception {
        log.info("Updating expense details : {}", expenseDetailsDto);
        var expenseDetails = expensesDBUtilService.getExpenseDetails(tid);
        if(expenseDetails.isEmpty())
        {
         throw new Exception("Expense Details Not Found");
        }
        var expenseEntity = expenseDetails.get();
        var totalAmount = expenseDetailsDto.getPrice() * expenseDetailsDto.getQuantity() * expenseDetailsDto.getNoOfDays();
        expenseEntity.setTotalAmount(totalAmount);
        modelMapper.map(expenseDetailsDto, expenseEntity);
        log.info("Updating expense details : {}", expenseEntity);
        var savedRecord = expensesDBUtilService.saveExpenseDetails(expenseEntity);
        return responseService.buildSuccessApiResponseDto(List.of(savedRecord),1);
    }

    /**
     * @param tid
     * @return
     * @throws Exception
     */
    @Override
    public BaseApiResponse deleteExpense(Long tid) throws Exception {
        log.info("Deleting expense details : {}", tid);
        var expenseDetails = expensesDBUtilService.getExpenseDetails(tid);
        if(expenseDetails.isEmpty())
        {
            throw new Exception("Expense Details Not Found");
        }
        var expenseEntity = expenseDetails.get();
        expenseEntity.setActiveStatus(0);
        var savedRecord = expensesDBUtilService.saveExpenseDetails(expenseEntity);
        return responseService.buildSuccessApiResponseDto(List.of(savedRecord),1);
    }
}
