package com.bartosztanski.visitreservation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

	Optional<EmployeeEntity> findByFirstNameLastName(String fName, String lName);

}
