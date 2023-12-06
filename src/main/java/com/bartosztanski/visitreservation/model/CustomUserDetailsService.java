package com.bartosztanski.visitreservation.model;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.repository.ClientRepository;
import com.bartosztanski.visitreservation.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	EmployeeEntity employeeEntity = employeeRepository.getByEmailAddressIgnoreCase(username).get();
    	log.info(employeeEntity.toString());
    	CustomUserDetails userDetails = getTestUserDetails(username);
//		CustomUserDetails userDetails = employeeRepository
//				.getByEmailAddressIgnoreCase(username)
//				.orElseGet(() -> new EmployeeEntity())
//				.toUserDetails();
		log.info( "user details" + userDetails.toString());
		log.info(userDetails.getFirstName());
        if (userDetails.getUsername() == null) {
        	userDetails = clientRepository
        			.getByEmailAddressIgnoreCase(username)
        			.orElseThrow(()-> new UsernameNotFoundException(
        					username))
        			.toUserDetails();
        }
        final CustomUserDetails immutableUserDetails = userDetails;
        return immutableUserDetails;
    }
    
    private CustomUserDetails getTestUserDetails(String username) {
    	String pass = employeeRepository.getByEmailAddressIgnoreCase(username).get().getPassword();
    	return new CustomUserDetails.Builder().withFirstName("firstName")
    	        .withLastName("lastName")
    	        .withEmail("emailAddress")
    	        .withUsername("username")
    	        .withPassword(pass)
    	        .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
    	        .build();
    }
}