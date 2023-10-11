package com.bartosztanski.visitreservation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID>{

	Optional<ClientEntity> findByFirstNameLastName(String fName, String lName);

	Optional<ClientEntity> findByPhoneNumber(Long phoneNumber);

	Optional<ClientEntity> findByEmailAddress(String email);

}
