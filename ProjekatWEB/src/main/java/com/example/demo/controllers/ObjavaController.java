package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.services.KorisnikService;
import com.example.demo.services.ObjavaService;
import com.example.demo.services.PorukaService;
import com.example.demo.services.ProfilService;
import com.example.demo.services.TopikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Kategorija;
import model.Komentar;
import model.Korisnik;
import model.Objava;
import model.Topik;

@Controller
@RequestMapping("/objava/")
public class ObjavaController {
	
	@Autowired
	ObjavaService os;
	
	@Autowired
	TopikService ts;
	
	@Autowired
	ProfilService ps;
	
	@Autowired
	PorukaService porukaService;
	
	@Autowired
	KorisnikService ks;
	
	//proveriti kako proslediti idtopika, a da nas posalje na stranicu
	
	@ModelAttribute("kategorije")
	public List<Kategorija> getStranica(HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		if (k != null) {
			Integer idKorisnik = k.getIdkorisnik();
			return ps.getKategorije(idKorisnik);
		}
		return null;
	}
	
	@GetMapping("getStranica")
	public String getStranica(@RequestParam("idTopik") Integer idTopik, HttpServletRequest request) {
		request.setAttribute("objave", os.getObjaveZaTopik(idTopik));
		request.getSession().setAttribute("topik", ts.getTopik(idTopik));
		return "Objave";
	}
	
	@GetMapping("getDetaljiObjave")
	public String getDetaljiObjave(@RequestParam("idobjava") Integer idobjava, HttpServletRequest request, Model m) {
		Objava o = os.getObjava(idobjava);
		List<Komentar> listaKomentara = os.getKomentareZaObjavu(idobjava);
		request.getSession().setAttribute("objava", o);
		m.addAttribute("komentari", listaKomentara);
		return "PrikazObjave";
		
	}
	
	@GetMapping("getDodavanjeKomentara")
	public String getDodavanjeKomentara() {
		return "DodavanjeKomentara";
	}
	
	@PostMapping("dodajKomentar")
	public String dodajKomentar(@RequestParam("sadrzaj") String sadrzaj, HttpServletRequest request) {
		
		Objava o = (Objava) request.getSession().getAttribute("objava");
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		os.dodajKomentar(sadrzaj, o, ulogovan);
		
		List<Komentar> listaKomentara = os.getKomentareZaObjavu(o.getIdobjava());
		request.setAttribute("komentari", listaKomentara);
		return "PrikazObjave";
	}
	
	@GetMapping("getObjavaPoruka")
	public String getObjavaPoruka() {
		return "ObjavaPoruka";
	}
	
	@PostMapping("posaljiObjavaPoruku")
	public String posaljiPoruku(@RequestParam("sadrzaj") String sadrzaj, HttpServletRequest request) {
		
		Objava o = (Objava) request.getSession().getAttribute("objava");
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		porukaService.savePoruka(sadrzaj, ulogovan, o.getKorisnik());
		
		List<Komentar> listaKomentara = os.getKomentareZaObjavu(o.getIdobjava());
		request.setAttribute("komentari", listaKomentara);
		return "PrikazObjave";
	}
	
	@GetMapping("getKomentarPoruka")
	public String getKomentarPoruka(@RequestParam("idkorisnik") Integer idkorisnik, HttpServletRequest request) {
		request.getSession().setAttribute("idkorisnik", idkorisnik);
		return "KomentarPoruka";
	}
	
	@PostMapping("posaljiKomentarPoruku")
	public String posaljiPoruku(@RequestParam("sadrzaj") String sadrzaj, @RequestParam("idkorisnik") Integer idkorisnik, HttpServletRequest request) {
		
		Korisnik primalac = ks.getKorisnik(idkorisnik);
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		porukaService.savePoruka(sadrzaj, ulogovan, primalac);
		
		Objava o = (Objava) request.getSession().getAttribute("objava");
		List<Komentar> listaKomentara = os.getKomentareZaObjavu(o.getIdobjava());
		
		request.setAttribute("komentari", listaKomentara);
		request.getSession().removeAttribute("idkorisnik");
		return "PrikazObjave";
	}
	
	// implementirati pretragu po kljucnoj reci ili datumu
	// skoro pa gotovo samo modifikovati upite da radi za rec
	
	@PostMapping("pretragaObjava")
	public String pretragaObjava(HttpServletRequest request) {
		
		Topik t = (Topik) request.getSession().getAttribute("topik");
		String pretraga = request.getParameter("pretraga");
		String datum = request.getParameter("datum");
		List<Objava> listaObjava = os.getObjavePoKriterijumu(t.getIdtopik(), pretraga, datum);
		request.setAttribute("objave", listaObjava);		
		
		return "Objave";
		
	}
	
	@PostMapping("sortiranjeObjava")
	public String sortiranjeObjava(@RequestParam("kriterijum") String kriterijum, HttpServletRequest request) {
		
		Topik t = (Topik) request.getSession().getAttribute("topik");
		List<Objava> listaSortiranihObjava = os.getSortiraneObjave(t.getIdtopik(), kriterijum);
		request.setAttribute("objave", listaSortiranihObjava);		
		
		return "Objave";

	}
	
	@PostMapping("postaviObjavu")
	public String postaviObjavu(@RequestParam("naslov") String naslov, @RequestParam("sadrzaj") String sadrzaj, HttpServletRequest request) {
		
		Topik topik = (Topik) request.getSession().getAttribute("topik");
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		os.postaviObjavu(naslov, sadrzaj, topik, ulogovan);
		request.setAttribute("objave", os.getObjaveZaTopik(topik.getIdtopik()));
		
		return "Objave";
	}
	
	@GetMapping("getBrisanje")
	public String getBrisanje(@RequestParam("idobjava") Integer idobjava, @RequestParam("idkorisnik") Integer idkorisnik, HttpServletRequest request) {
		request.getSession().setAttribute("idobjava", idobjava);
		request.getSession().setAttribute("idkorisnik", idkorisnik);
		return "BrisanjeObjave";
		
	}
	
	@PostMapping("izbrisiObjavu")
	public String izbrisiObjavu(@RequestParam("idobjava") Integer idobjava, @RequestParam("idkorisnik") Integer idkorisnik, HttpServletRequest request, RedirectAttributes model) {
		
		os.izbrisiObjavu(idobjava, idkorisnik, request.getParameter("datumZabrane"));
		
		request.getSession().removeAttribute("idobjava");
		request.getSession().removeAttribute("idkorisnik");
		Topik t = (Topik) request.getSession().getAttribute("topik");
		model.addAttribute("idTopik", t.getIdtopik());
		return "redirect:/objava/getStranica";
	}
	
	// modifikacija ukoliko postoji zabrana objavljivanja
	
	@GetMapping("getObjavljivanje")
	public String getObjavljivanje(HttpServletRequest request, RedirectAttributes model) {
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		
		if (ulogovan.getZabrana() != null) {
			
			if (ulogovan.getZabrana().after(new Date())) {
				Topik topik = (Topik) request.getSession().getAttribute("topik");
				model.addAttribute("idTopik", topik.getIdtopik());
				return "redirect:/objava/getStranica";
			}
			
			else {
				ks.ukloniZabranu(ulogovan);
			}
			
		}
		
		return "UnosObjave";
	}
	
	//dodati dugme nazad gde se topik sklanja iz sesije
	

}
