package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import com.alpha.alpha_help_desk_backend.entity.EmployeeInvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeInvoiceRepository extends JpaRepository<EmployeeInvoiceEntity,Long> {

    @Query("SELECT e FROM EmployeeInvoiceEntity e WHERE e.employeeEntity.id = :employeeId")
    List<EmployeeInvoiceEntity> findByEmployeeId(@Param("employeeId") Long employeeId);


    @Query("SELECT e FROM EmployeeInvoiceEntity e WHERE e.tid = :tid")
    Optional<EmployeeInvoiceEntity> findById(@Param("tid") Long tid);



    @Query("SELECT e FROM EmployeeInvoiceEntity e WHERE e.employeeEntity.id = :employeeId AND e.weekNo = :weekNo AND e.month =:month")
    List<EmployeeInvoiceEntity> findEmployInvoiceEntity(@Param("employeeId") Long employeeId, @Param("weekNo") Integer weekNo, @Param("month") String month);

    @Query("SELECT e FROM EmployeeInvoiceEntity e " +
            "WHERE (:invoiceId IS NULL OR e.id = :invoiceId) " +
            "AND (:employeeId IS NULL OR e.employeeEntity.id = :employeeId) " +
            "AND (:status IS NULL OR e.status = :status) " +
            "AND (:month IS NULL OR e.month LIKE %:month%) " +
            "AND (:dateFrom IS NULL OR e.dateFrom >= :dateFrom) " +
            "AND (:dateTo IS NULL OR e.dateTo <= :dateTo)")
    List<EmployeeInvoiceEntity> fetchInvoices(
            @Param("invoiceId") Long invoiceID,
            @Param("employeeId") Long employeeId,
            @Param("status") Integer status,
            @Param("month") String month,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

}
