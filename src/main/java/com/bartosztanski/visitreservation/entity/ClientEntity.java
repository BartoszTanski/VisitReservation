package com.bartosztanski.visitreservation.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bartosztanski.visitreservation.model.CustomUserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class ClientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL)
	private List<VisitEntity> visits;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	@Column(name="email_address")
	private String emailAddress;
	private String password;

	
	public CustomUserDetails toUserDetails() {
		
		if (this.emailAddress == null) return null;
		
		return new CustomUserDetails.Builder().withFirstName(this.firstName)
        .withLastName(this.lastName)
        .withEmail(this.emailAddress)
        .withUsername(this.emailAddress)
        .withPassword(this.password)
        .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
        .build();
	}
}
