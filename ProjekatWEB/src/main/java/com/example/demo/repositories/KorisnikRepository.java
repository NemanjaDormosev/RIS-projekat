package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	List<Korisnik> findByUsernameAndPassword(String username, String password);
	
//	@Query()
//	List<Korisnik> pronadjiNajaktivnijeZaDatum(Date datum);
	
}
