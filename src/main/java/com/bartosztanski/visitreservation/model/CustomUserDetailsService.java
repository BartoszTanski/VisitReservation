package com.bartosztanski.visitreservation.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.repository.ClientRepository;
import com.bartosztanski.visitreservation.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails userDetails = employeeRepository
				.getByEmailAddressIgnoreCase(username)
				.orElseGet(() -> new EmployeeEntity())
				.toUserDetails();
		
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
}