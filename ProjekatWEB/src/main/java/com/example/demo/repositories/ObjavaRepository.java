package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;
import model.Objava;

@Repository
public interface ObjavaRepository extends JpaRepository<Objava, Integer> {
	
	int countByKorisnik_idkorisnik(Integer idKorisnik);

}
