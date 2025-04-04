package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.WeeklyCompanyIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyIncomeRepository extends JpaRepository<WeeklyCompanyIncome, Long> {


}
