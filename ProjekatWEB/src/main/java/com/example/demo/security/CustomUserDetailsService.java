package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;

import model.Korisnik;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	KorisnikRepository korisnikRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Korisnik k = korisnikRepo.findByUsername(username).get();
	   UserDetails ud = new CustomUserDetails(k);
	   return ud;
	}
	
}
