package com.bartosztanski.visitreservation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.model.CustomUserDetails;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID>{

	Optional<ClientEntity> findByFirstNameAndLastName(String firstName, String lastName);

	Optional<ClientEntity> findByPhoneNumber(Long phoneNumber);

	Optional<ClientEntity> findByEmailAddress(String email);

	Optional<ClientEntity> getByEmailAddressIgnoreCase(String username);

}
