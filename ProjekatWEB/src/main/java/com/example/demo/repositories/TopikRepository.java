package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Topik;

@Repository
public interface TopikRepository extends JpaRepository<Topik, Integer> {
	

}
