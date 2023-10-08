package com.bartosztanski.visitreservation.model;

import jakarta.persistence.Column;
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
	@Column(name="email_address")
	protected String emailAddress;
}
