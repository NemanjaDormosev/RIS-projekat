package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KategorijaRepository;
import com.example.demo.repositories.KomentarRepository;
import com.example.demo.repositories.ObjavaRepository;
import com.example.demo.repositories.PorukaRepository;

import model.Kategorija;
import model.Komentar;
import model.Objava;
import model.Poruka;

@Service
public class ProfilService {
	
	@Autowired
	ObjavaRepository objavaRepo;
	
	@Autowired
	KomentarRepository komentarRepo;
	
	@Autowired
	PorukaRepository porukaRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	public int getBrojObjava(Integer idKorisnik) {
		return objavaRepo.countByKorisnik_idkorisnik(idKorisnik);
	}
	
	public int getBrojKomentara(Integer idKorisnik) {
		return komentarRepo.countByKorisnik_idkorisnik(idKorisnik);
	}
	
	public int getBrojPoruka(Integer idKorisnik) {
		return porukaRepo.pronadjiSvePoruke(idKorisnik);
	}
	
	public List<Kategorija> getKategorije(Integer idKorisnik) {
		return kategorijaRepo.findByKorisnik_idkorisnik(idKorisnik);
	}

}
