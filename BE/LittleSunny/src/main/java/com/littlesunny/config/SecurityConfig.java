package com.littlesunny.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final String[] PUBLIC_ENDPOINTS = {
			"/api/v1/auth/*",
			"/api/v1/user"
	};
	
	@Autowired
	private CustomJwtDecoder customJwtDecoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(request -> request
				.requestMatchers("/api/v1/auth/*").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
				.anyRequest().authenticated()
		);
		
		httpSecurity.oauth2ResourceServer(oauth2 -> {
			oauth2.jwt(jwt -> jwt
					.decoder(customJwtDecoder)
					.jwtAuthenticationConverter(jwtAuthenticationConverter()))
					.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
		});
		
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		return httpSecurity.build();
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
		
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		
		return jwtAuthenticationConverter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
