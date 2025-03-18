package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity,Long> {
    EmployeeEntity findByUserID (Long userID);


    Optional<EmployeeEntity> findEmployeeEntitiesByTid (Long employeeID);




}
