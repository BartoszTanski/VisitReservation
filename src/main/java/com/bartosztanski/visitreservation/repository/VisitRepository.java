package com.bartosztanski.visitreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, Long>{

	List<Visit> findByEmployee(EmployeeEntity employeeEntity);

}
