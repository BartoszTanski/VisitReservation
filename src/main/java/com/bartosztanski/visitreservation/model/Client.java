package com.bartosztanski.visitreservation.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person {
	
	private UUID id;
	private List<Visit> visits;
	
	@Builder
    public Client(UUID id, String firstName, String lastName, Long phoneNumber, String emailAddress, List<Visit> visits) {
        super(firstName, lastName, phoneNumber, emailAddress);
        this.id = id;
        this.visits = visits;
    }
}
