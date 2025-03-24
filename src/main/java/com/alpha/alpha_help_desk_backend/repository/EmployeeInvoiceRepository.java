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

    @Query(value = "SELECT eie.* FROM employee_invoice eie " +
            "JOIN employee ee ON ee.tid = eie.employee_id " +
            "WHERE (CAST(:invoiceId AS BIGINT) IS NULL OR eie.tid = :invoiceId) " +
            "AND (CAST(:employeeId AS BIGINT) IS NULL OR ee.tid = :employeeId) " +
            "AND (CAST(:status AS INTEGER) IS NULL OR eie.status = :status) " +
            "AND (CAST(:month AS TEXT) IS NULL OR eie.month LIKE CONCAT('%', :month, '%')) " +
            "AND (CAST(:dateFrom AS DATE) IS NULL OR eie.date_from >= :dateFrom) " +
            "AND (CAST(:dateTo AS DATE) IS NULL OR eie.date_to <= :dateTo)",
            nativeQuery = true)
    List<EmployeeInvoiceEntity> fetchInvoices(
            @Param("invoiceId") Long invoiceID,
            @Param("employeeId") Long employeeId,
            @Param("status") Integer status,
            @Param("month") String month,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

}
