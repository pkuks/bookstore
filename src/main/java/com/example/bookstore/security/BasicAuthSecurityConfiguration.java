package com.example.bookstore.security;

/**
 * This class is for Basic Authentication Security Configuration
 * 
 * Created By Prakash K
 * Created 9th Aug 2024 
 */
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(
				auth -> {
					auth
					.anyRequest().authenticated();
				});

		http.sessionManagement(
				session -> 
					session.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS)
				);

		//http.formLogin();
		http.httpBasic(withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));

		return http.build();

	}
	
	
//	@Bean
//	public UserDetailsService userDetailService() {
//		
//		var user = User.withUsername("user2")
//			.password("{noop}dummy")
//			.roles("USER")
//			.build();
//		
//		var admin = User.withUsername("admin")
//				.password("{noop}dummy")
//				.roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) throws Exception{
		var user1 = User.withUsername("user1")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("USER")
				.build();

		var user2 = User.withUsername("user2")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("USER")
				.build();

		var admin = User.withUsername("admin")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN","USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user1);
		jdbcUserDetailsManager.createUser(admin);
		jdbcUserDetailsManager.createUser(user2);
		
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
