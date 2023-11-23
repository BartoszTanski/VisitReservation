package com.bartosztanski.visitreservation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.model.CustomUserDetails;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

	Optional<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);

	Optional<EmployeeEntity> findByPhoneNumber(Long phoneNumber);

	Optional<EmployeeEntity> getByEmailAddressIgnoreCase(String email);

}
