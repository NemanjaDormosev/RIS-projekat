package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.Korisnik;

public class CustomUserDetails implements UserDetails {
	
	  private Korisnik k;
	
	  public CustomUserDetails(Korisnik k) {
		  this.k = k;
	  }
	  
	  public Korisnik getK() {
		  return k;
	  }
	  
	  public void setK(Korisnik k) {
		  this.k = k;
	  }
	  
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
		   Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		   authorities.add(new SimpleGrantedAuthority("ROLE_" + k.getUloga().getNaziv()));
		   return authorities;
	  }

	@Override
	public String getPassword() {
		return k.getPassword();
	}

	@Override
	public String getUsername() {
		return k.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	  
}


