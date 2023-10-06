package com.bartosztanski.visitreservation.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	@OneToMany(mappedBy = "visits")
	private ClientEntity client;
	private int duration;
	@OneToMany(mappedBy = "visits")
	private EmployeeEntity employee;
	private boolean available;
	
}
