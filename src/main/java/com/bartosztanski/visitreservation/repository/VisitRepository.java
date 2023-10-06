package com.bartosztanski.visitreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.VisitEntity;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>{

}
