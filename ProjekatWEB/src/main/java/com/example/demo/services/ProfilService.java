package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KategorijaRepository;
import com.example.demo.repositories.KomentarRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.ObjavaRepository;
import com.example.demo.repositories.PorukaRepository;

import model.Kategorija;
import model.Korisnik;
import model.Objava;
import model.Poruka;

@Service
public class ProfilService {
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
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
	
	public List<Korisnik> getPosiljaoce(Integer idKorisnik) {
		return korisnikRepo.pronadjiSvePosiljaoce(idKorisnik);
	}
	
	public Kategorija getKategorija(Integer idkategorija) {
		return kategorijaRepo.findById(idkategorija).get();
	}
	
	public List<Kategorija> getKategorije(Integer idKorisnik) {
		return kategorijaRepo.findByKorisnik_idkorisnik(idKorisnik);
	}
	
	public List<Objava> getObjaveZaKategoriju(Integer idkategorija) {
		return objavaRepo.pronadjiSveObjaveZaKategoriju(idkategorija);
	}

	public List<Poruka> getRazmenjenePoruke(Integer idkorisnik, Integer idkorisnik2) {
		return porukaRepo.pronadjiSveRazmenjenePoruke(idkorisnik, idkorisnik2);
	}

	public void saveKategorija(String naziv, Korisnik ulogovan, Objava o) {
		
		Kategorija k = new Kategorija();
		k.setNaziv(naziv);
		k.setKorisnik(ulogovan);
		k.getObjavas().add(o);
		o.getKategorijas().add(k);
		
		kategorijaRepo.save(k);
		objavaRepo.save(o);

	}

	public boolean proveriKategoriju(Integer idkategorija, Integer idobjava) {
		
		boolean postoji = false;
		
		List<Objava> listaObjava = objavaRepo.pronadjiKategoriju(idkategorija, idobjava);
		System.out.println("Ima objavu: " + listaObjava.size());
		if (listaObjava.size() != 0) {
			postoji = true;
		}
		
		return postoji;
	}

	public void dodajImaObjavu(Kategorija k, Objava o) {
		
		k.getObjavas().add(o);
		o.getKategorijas().add(k);
		
		kategorijaRepo.save(k);
		objavaRepo.save(o);
		
	}

}
