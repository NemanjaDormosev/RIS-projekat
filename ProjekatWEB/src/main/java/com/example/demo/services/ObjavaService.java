package com.example.demo.services;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.KomentarRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.ObjavaRepository;

import model.Komentar;
import model.Korisnik;
import model.Objava;
import model.Topik;

@Service
public class ObjavaService {
	
	@Autowired
	ObjavaRepository objavaRepo;
	
	@Autowired
	KomentarRepository komentarRepo;
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	public List<Objava> getObjaveZaTopik(Integer idtopik) {
		return objavaRepo.findByTopik_idtopik(idtopik);
	}
	
	public Objava getObjava(Integer idObjava) {
		return objavaRepo.findById(idObjava).get();
	}
	
	public List<Komentar> getKomentareZaObjavu(Integer idObjava) {
		return komentarRepo.findByObjava_idobjava(idObjava);
	}

	public List<Objava> getObjavePoKriterijumu(int idtopik, String pretraga, String strDatum) {
		
		List<Objava> listaObjava = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date datum = null;
		try {
			datum = sdf.parse(strDatum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("DATUM NIJE ODABRAN");
		}
				
		if (!pretraga.equals("")) {
			
			if (datum == null) {
				listaObjava = objavaRepo.pronadjiObjavePoReci(idtopik, pretraga);
			}
			
			else {
				listaObjava = objavaRepo.pronadjiObjavePoKriterijumu(idtopik, pretraga, datum);
			}
			
		}
		
		else {
			
			if (datum != null) {
				listaObjava = objavaRepo.pronadjiObjavePoDatumu(idtopik, datum);
			}
			
			else {
				listaObjava = objavaRepo.findByTopik_idtopik(idtopik);
			}
		}
		
		return listaObjava;
		
	}

	public List<Objava> getSortiraneObjave(int idtopik, String kriterijum) {
		
		List<Objava> listaSortiranihObjava;
		
		if (kriterijum.equals("naslov")) {
			listaSortiranihObjava = objavaRepo.getSortiraneObjavePoNaslovu(idtopik);
		}
		else {
			listaSortiranihObjava = objavaRepo.getSortiraneObjavePoDatumu(idtopik);
		}
		
		return listaSortiranihObjava;
	}
	
	public void postaviObjavu(String naslov, String sadrzaj, Topik t, Korisnik k) {
		
		//postavljanje novog statusa korisniku
		
		int brojObjava = objavaRepo.countByKorisnik_idkorisnik(k.getIdkorisnik());
		int brojKomentara = komentarRepo.countByKorisnik_idkorisnik(k.getIdkorisnik());
		
		int ukupno = brojObjava + brojKomentara + 1;
		
		String[] statusi = {"pocetnik", "novajlija", "veteran", "hardcore"};
		String trenutniStatus = null;
		
		if (ukupno >= 5 && ukupno <= 9) {
			trenutniStatus = statusi[1];
		}
		
		else if (ukupno >= 10 && ukupno <= 14) {
			trenutniStatus = statusi[2];
		}
		
		else if (ukupno >= 15) {
			trenutniStatus = statusi[3];
		}
		
		
		//provera da li je zadovoljen uslov za novi status;
		
		if (trenutniStatus != null) {
			k.setStatus(trenutniStatus);
			korisnikRepo.save(k);
		}
		
		Objava o = new Objava();
		o.setNaslov(naslov);
		o.setSadrzaj(sadrzaj);
		o.setTopik(t);
		o.setKorisnik(k);
		o.setDatum(new Date());
		o.setVreme(Time.valueOf(LocalTime.now()));
		
		objavaRepo.save(o);
		
	}

	public void izbrisiObjavu(Integer idobjava, Integer idkorisnik, String strDatum) {
		
		Korisnik k = korisnikRepo.findById(idkorisnik).get();
		
		//postavljanje novog statusa korisniku
		
		int brojObjava = objavaRepo.countByKorisnik_idkorisnik(k.getIdkorisnik());
		int brojKomentara = komentarRepo.countByKorisnik_idkorisnik(k.getIdkorisnik());
		
		int ukupno = brojObjava + brojKomentara - 1;
		
		String[] statusi = {"pocetnik", "novajlija", "veteran", "hardcore"};
		String trenutniStatus = null;
		
		if (ukupno >= 0 && ukupno <= 4) {
			trenutniStatus = statusi[0];
		}
		
		else if (ukupno >= 5 && ukupno <= 9) {
			trenutniStatus = statusi[1];
		}
		
		else if (ukupno >= 10 && ukupno <= 14) {
			trenutniStatus = statusi[2];
		}
		
		else if (ukupno >= 15) {
			trenutniStatus = statusi[3];
		}
		
		
		//provera da li je zadovoljen uslov za novi status
		
		boolean promena = false;
		
		if (trenutniStatus != null) {
			k.setStatus(trenutniStatus);
			promena = true;
		}
				
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date datum = null;
		try {
			datum = sdf.parse(strDatum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("DATUM NIJE ODABRAN");
		}
		
		if (datum != null) {
			k.setZabrana(datum);
			promena = true;
		}
		
		//provera da li se desila bilo kakva promena
		
		if (promena) {
			korisnikRepo.save(k);
		}
		
		Objava o = objavaRepo.findById(idobjava).get();
		objavaRepo.delete(o);
	}

	public void dodajKomentar(String sadrzaj, Objava o, Korisnik ulogovan) {
		
		//postavljanje novog statusa korisniku
		
		int brojObjava = objavaRepo.countByKorisnik_idkorisnik(ulogovan.getIdkorisnik());
		int brojKomentara = komentarRepo.countByKorisnik_idkorisnik(ulogovan.getIdkorisnik());
		
		int ukupno = brojObjava + brojKomentara + 1;
		
		String[] statusi = {"pocetnik", "novajlija", "veteran", "hardcore"};
		String trenutniStatus = null;
		
		if (ukupno >= 5 && ukupno <= 9) {
			trenutniStatus = statusi[1];
		}
		
		else if (ukupno >= 10 && ukupno <= 14) {
			trenutniStatus = statusi[2];
		}
		
		else if (ukupno >= 15) {
			trenutniStatus = statusi[3];
		}
		
		
		//provera da li je zadovoljen uslov za novi status
		
		if (trenutniStatus != null) {
			ulogovan.setStatus(trenutniStatus);
			korisnikRepo.save(ulogovan);
		}
		
		Komentar k = new Komentar();
		k.setSadrzaj(sadrzaj);
		k.setObjava(o);
		k.setKorisnik(ulogovan);
		k.setDatum(new Date());
		k.setVreme(Time.valueOf(LocalTime.now()));
		
		komentarRepo.save(k);
		
	}
	
}
