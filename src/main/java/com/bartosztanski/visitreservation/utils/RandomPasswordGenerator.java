package com.bartosztanski.visitreservation.utils;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomPasswordGenerator {
	
	private final PasswordEncoder passwordEncoder;
	
	public String generate() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
	    int targetStringLength = 12;
	    Random random = new Random();
	
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    log.info(generatedString);
	    return passwordEncoder.encode(generatedString);
	}
}
