package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Poruka;

@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Integer> {
	
	@Query("select count(p) from Poruka p where p.korisnik1.idkorisnik = :idkorisnik or p.korisnik2.idkorisnik = :idkorisnik")
	int pronadjiSvePoruke(@Param("idkorisnik") Integer idKorisnik);
	
	@Query("select p from Poruka p where (p.korisnik1.idkorisnik = :prviKorisnik and p.korisnik2.idkorisnik = :drugiKorisnik) or (p.korisnik1.idkorisnik = :drugiKorisnik and p.korisnik2.idkorisnik = :prviKorisnik) order by datum asc, vreme asc")
	List<Poruka> pronadjiSveRazmenjenePoruke(@Param("prviKorisnik") Integer idkorisnik, @Param("drugiKorisnik") Integer idkorisnik2);
	
	
}
