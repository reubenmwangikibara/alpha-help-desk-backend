package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInvoiceRepository extends JpaRepository<EmployeeInvoiceEntity,Long> {

    EmployeeInvoiceEntity findEmployeeInvoiceEntitiesByEmployeeId (Long userID);

}
