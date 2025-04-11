package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.CompanyIncomeView;
import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeeklyIncomeRepository extends JpaRepository<WeeklyCompanyIncome, Long> {


    @Query("SELECT w FROM WeeklyCompanyIncome w")
    List<WeeklyCompanyIncome> findAllIncomeDetails(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
