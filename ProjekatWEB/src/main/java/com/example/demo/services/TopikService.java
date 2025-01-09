package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.TopikRepository;

import model.Topik;

@Service
public class TopikService {
	
	@Autowired
	TopikRepository topikRepo;
	
	public void dodajTopik(Topik t) {
		topikRepo.save(t);
	}
	
	public Topik getTopik(Integer idTopik) {
		return topikRepo.findById(idTopik).get();
	}

}
