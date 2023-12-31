package com.bartosztanski.visitreservation.entity;

import java.util.Date;

import com.bartosztanski.visitreservation.model.MedicalField;
import com.bartosztanski.visitreservation.model.VisitType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="visits")
public class VisitEntity {
	
	@Id
	@GeneratedValue
	(strategy = GenerationType.SEQUENCE,generator = "visit_generator")
	@SequenceGenerator
	(name="visit_generator", sequenceName = "visit_seq", 
	initialValue = 100000 )
	private Long id;
	private Date startTime;
	@ManyToOne
	@JoinColumn(name="client_id")
	private ClientEntity client;
	private int duration;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id")
	private EmployeeEntity employee;
	private VisitType type;
	private MedicalField field;
	private boolean available;
	
}
