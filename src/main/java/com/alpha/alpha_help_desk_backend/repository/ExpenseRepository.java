package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Long> {

    Optional<ExpenseEntity> findExpenseEntitiesByTid(Long id);
    Optional<ExpenseEntity> findExpenseEntitiesByActiveStatusAndWeekNoAndName(int id,Long weekNo,String name);

    @Query("SELECT e FROM ExpenseEntity e where e.activeStatus = 1")
    List<ExpenseEntity> findAll();
}
