package com.bartosztanski.visitreservation.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {
	protected String firstName;
	protected String lastName;
	protected Long phoneNumber;
	protected String emailAddress;
}
