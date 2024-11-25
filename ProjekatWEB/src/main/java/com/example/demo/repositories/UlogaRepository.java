package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Uloga;

@Repository
public interface UlogaRepository extends JpaRepository<Uloga, Integer> {
	
	public List<Uloga> findByNaziv(String naziv);
}
