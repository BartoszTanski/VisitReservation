package com.bartosztanski.visitreservation.entity;

import java.util.List;
import java.util.UUID;

import com.bartosztanski.visitreservation.model.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class ClientEntity extends Person {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	private List<VisitEntity> visits;
}
