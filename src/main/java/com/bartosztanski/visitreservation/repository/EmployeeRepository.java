package com.bartosztanski.visitreservation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

}
