package com.alpha.alpha_help_desk_backend.repository;

import com.alpha.alpha_help_desk_backend.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity,Long> {


    Optional<EmployeeEntity> findEmployeeEntitiesByTid (Long employeeID);

    @Query("SELECT e FROM EmployeeEntity e WHERE  e.status=1 order by e.tid desc ")
    List<EmployeeEntity> findAllEmployees ();

}
