package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests(requests -> requests
                                .requestMatchers("/objava/izbrisiObjavu").hasRole("moderator")
                                .requestMatchers("/objava/getBrisanje").hasRole("moderator")
                                .requestMatchers("/izvestaj/**").hasRole("moderator")
                                .requestMatchers("/statistika/**").hasRole("moderator")
                                .requestMatchers("/topik/**").hasRole("moderator")
        						.requestMatchers("/korisnik/**").permitAll()
        						.requestMatchers("css/**").permitAll()
        						.anyRequest().authenticated())
		                		.formLogin(form -> form
			                        .loginPage("/Logovanje.jsp").permitAll()
			                        .loginProcessingUrl("/login")
			                        .defaultSuccessUrl("/korisnik/getPocetnaUlogovan", true))
		                		.logout(logout -> logout
		                			 .logoutSuccessUrl("/korisnik/odjavljivanje").permitAll())
		                		.exceptionHandling(handling -> handling.accessDeniedPage("/access_denied.jsp"))
		                		.csrf(csrf -> csrf.disable());
		return http.build();
		
	}
	
	
	@Bean
	UserDetailsService userDetailsService() {
		
//		UserDetails korisnik = User.withUsername("test")
//		        .password("123")
//		        .roles("korisnik")
//		        .build();
//		
//		return new InMemoryUserDetailsManager(korisnik);
		
		return new CustomUserDetailsService();
	}
   
	
	@Bean
	 AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	  }
	
	@Bean
	 PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

}
