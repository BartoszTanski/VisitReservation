package com.bartosztanski.visitreservation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bartosztanski.visitreservation.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID>{

}
