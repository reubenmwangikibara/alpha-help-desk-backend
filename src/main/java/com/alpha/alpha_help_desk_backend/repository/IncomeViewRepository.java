package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.CompanyIncomeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeViewRepository extends JpaRepository<CompanyIncomeView,Long> {

    @Query("SELECT c FROM CompanyIncomeView c WHERE c.dateFrom >= :startDate AND c.dateTo <= :endDate")
    Optional<CompanyIncomeView> findIncomeByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT c FROM CompanyIncomeView c")
    List<CompanyIncomeView> findInvoiceDetails(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);



}
