package com.bartosztanski.visitreservation.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
	ROLE_USER("ROLE_USER"),ROLE_EMPLOYEE("ROLE_EMPLOYEE"),ROLE_ADMIN("ROLE_ADMIN");
	
	private String value;
	
	private Role(String value) {
        this.value = value;
    }
	
	public SimpleGrantedAuthority toSimpleGrantedAuthority() {
		return new SimpleGrantedAuthority(this.value);	
	}
}
