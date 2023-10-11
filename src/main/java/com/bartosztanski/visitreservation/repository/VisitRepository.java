package com.bartosztanski.visitreservation.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.VisitEntity;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>{

	List<VisitEntity> findAllByEmployeeId(UUID id);
	List<VisitEntity> findAllByClientId(UUID id);
	
	
}
