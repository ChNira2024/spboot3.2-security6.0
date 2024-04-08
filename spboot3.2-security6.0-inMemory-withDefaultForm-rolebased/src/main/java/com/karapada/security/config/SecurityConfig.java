package com.karapada.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetails()
	{
		UserDetails user1 = User.withUsername("raja").password(passwordEncoder().encode("1234")).roles("USER").build();
		UserDetails user2 = User.withUsername("sisu").password(passwordEncoder().encode("5678")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterApiRequest(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/emp/").permitAll()
		.and().authorizeHttpRequests().anyRequest().authenticated().and().formLogin();
		return httpSecurity.build();
	}
}
