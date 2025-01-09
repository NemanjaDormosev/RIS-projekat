package com.example.demo.services;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.PorukaRepository;

import model.Korisnik;
import model.Poruka;

@Service
public class PorukaService {
	
	@Autowired
	PorukaRepository porukaRepo;
	
	
	public void savePoruka(String sadrzaj, Korisnik posiljalac, Korisnik primalac) {
		
		Poruka p = new Poruka();
		p.setSadrzaj(sadrzaj);
		p.setKorisnik1(posiljalac);
		p.setKorisnik2(primalac);
		p.setDatum(new Date());
		p.setVreme(Time.valueOf(LocalTime.now()));
		porukaRepo.save(p);
		
	}

}
