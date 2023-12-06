package com.bartosztanski.visitreservation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	
	private final UserDetailsService userDetailsService;
	 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
        http
        	 .userDetailsService(userDetailsService)
	         .csrf(AbstractHttpConfigurer::disable)
			 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
			 .authorizeHttpRequests(auth -> {auth
				//.requestMatchers("/employees/*").hasAnyRole("ADMIN","USER")
				.requestMatchers("/clients/*").hasAnyRole("USER","ADMIN","EMPLOYEE")
				.requestMatchers("/visits/*").hasAnyRole("USER","ADMIN","EMPLOYEE")
				.requestMatchers("/api/*").hasAnyRole("EMPLOYEE")
				.requestMatchers("/mail/*")
				.authenticated()
				.anyRequest()
				.permitAll();
				})
			 .formLogin(form -> form
						.loginPage("/login")
						.permitAll()
					);
		return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }
}