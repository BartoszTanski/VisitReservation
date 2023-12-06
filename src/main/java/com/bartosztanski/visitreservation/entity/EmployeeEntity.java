package com.bartosztanski.visitreservation.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bartosztanski.visitreservation.model.CustomUserDetails;
import com.bartosztanski.visitreservation.model.Role;

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
import lombok.extern.slf4j.Slf4j;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employees")
@Slf4j
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<VisitEntity> visits;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	@Column(name="email_address")
	private String emailAddress;
	private String password;
	private List<Role> roles;
	
	public CustomUserDetails toUserDetails() {
		
		if (this.emailAddress == null) return null;
		log.info(this.firstName+this.lastName+this.emailAddress);
		return new CustomUserDetails.Builder().withFirstName(this.firstName)
        .withLastName(this.lastName)
        .withEmail(this.emailAddress)
        .withUsername(this.emailAddress)
        .withPassword(this.password)
        .withAuthorities(Collections.unmodifiableCollection(
        		roleToAuthority(this.roles)))
        .build();
	}
	
	private List<SimpleGrantedAuthority> roleToAuthority(List<Role> roles) {
		if (roles==null)
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		return roles.stream().map((role) -> role.toSimpleGrantedAuthority()).toList();
	}
	
	public String toString() {
		return "id: "+id+" firstname:"+ firstName+" lastname: "+lastName+" email: "+emailAddress;
	}
}
