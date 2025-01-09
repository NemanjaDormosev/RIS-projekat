package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import model.Kategorija;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {
	
	List<Kategorija> findByKorisnik_idkorisnik(Integer idKorisnik);
	
}
