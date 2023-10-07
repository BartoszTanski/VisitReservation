package com.bartosztanski.visitreservation.entity;

import java.util.List;
import java.util.UUID;

import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Person;
import com.bartosztanski.visitreservation.model.Visit;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private String id;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	private List<VisitEntity> visits;
	
	@Builder
    public ClientEntity(String id, String firstName, String lastName, Long phoneNumber, String emailAddress, List<VisitEntity> visits) {
        super(firstName, lastName, phoneNumber, emailAddress);
        this.id = id;
        this.visits = visits;
    }
}
