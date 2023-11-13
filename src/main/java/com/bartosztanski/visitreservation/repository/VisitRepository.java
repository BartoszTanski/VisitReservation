package com.bartosztanski.visitreservation.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.VisitEntity;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>{

	List<VisitEntity> findAllByEmployeeId(UUID id);
	List<VisitEntity> findAllByClientId(UUID id);
//	@Query(value="SELECT * FROM visits v WHERE v.startTime BETWEEN ?1 AND ?2 ORDER BY v.startTime")
//	List<VisitEntity> find(Date thisWeek, Date nextWeek);
	List<VisitEntity> findAllByAvailable(boolean b);
	
	
}
