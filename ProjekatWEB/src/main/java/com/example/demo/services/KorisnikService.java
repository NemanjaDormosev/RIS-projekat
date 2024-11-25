package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.TopikRepository;
import com.example.demo.repositories.UlogaRepository;

import model.Korisnik;
import model.Topik;
import model.Uloga;

@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	UlogaRepository ur;
	
	@Autowired
	TopikRepository topikRepo;
	
	
	public void saveKorisnik(Korisnik k) {
		kr.save(k);
	}
	
	public void saveUloga(Uloga u) {
		ur.save(u);
	}
	
	public List<Uloga> getUloga(String naziv) {
		return ur.findByNaziv(naziv);
	}
		
	public List<Korisnik> getKorisnik(String username, String password) {
		return kr.findByUsernameAndPassword(username, password);
	}
	
	public List<Topik> getTopiks() {
		return topikRepo.findAll();
	}
	
	public Korisnik getNajaktivnijegDanas() {
		return null;
	}
	
}
