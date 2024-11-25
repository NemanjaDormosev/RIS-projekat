package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Komentar;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Integer> {
	
	int countByKorisnik_idkorisnik(Integer idKorisnik);

}
