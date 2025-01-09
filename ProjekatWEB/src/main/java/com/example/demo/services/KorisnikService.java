package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.query.KorisnikStatistika;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.TopikRepository;
import com.example.demo.repositories.UlogaRepository;

import jakarta.persistence.Tuple;
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
	
	public void ukloniZabranu(Korisnik k) {
		k.setZabrana(null);
		kr.save(k);
	}
	
	public List<Uloga> getUloga(String naziv) {
		return ur.findByNaziv(naziv);
	}
	
	public Korisnik getKorisnik(Integer idkorisnik) {
		return kr.findById(idkorisnik).get();
	}
		
	public List<Korisnik> getKorisnik(String username, String password) {
		return kr.findByUsernameAndPassword(username, password);
	}
	
	public List<Topik> getTopiks() {
		return topikRepo.findAll();
	}
	
	public Korisnik getNajaktivnijegDanas() {
		
		// za proizvoljan datum
//		Date now = Date.from(LocalDate.of(2024, 11, 23).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		Date now = new Date();
		List<Korisnik> lista = kr.pronadjiNajaktivnijeZaDatum(now);
		
		if (lista.size() != 0) {
			return lista.get(0);
		}
		return null;
	}
	
	public List<Korisnik> getSveAktivneKorisnike() {
		return kr.pronadjiSveAktivneKorisnike();
	}
	
	public List<Tuple> getStatistikaObjava() {
		return kr.getStatistikaObjava();
	}
	
	public List<Tuple> getStatistikaKomentara() {
		return kr.getStatistikaKomentara();
	}
	
	public void hashAllPasswords() {
		List<Korisnik> listaKorisnika = kr.findAll();
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		for (Korisnik k: listaKorisnika) {
			
			String userPassword = pwEncoder.encode(k.getPassword());
			Integer userID = k.getIdkorisnik();
			kr.updatePassword(userPassword, userID);
			
		}
	}
	
}
