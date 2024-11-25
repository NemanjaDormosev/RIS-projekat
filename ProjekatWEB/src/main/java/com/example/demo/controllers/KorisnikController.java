package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.KorisnikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Korisnik;
import model.Topik;

@Controller
@RequestMapping("/korisnik/")
public class KorisnikController {
	
	@Autowired
	KorisnikService ks;
	
	@RequestMapping(value = "registracija", method = RequestMethod.POST)
	public String registracija(Korisnik k, Model m) {
		
		k.setStatus("pocetnik");
		k.setUloga(ks.getUloga("korisnik").get(0));
		ks.saveKorisnik(k);
		m.addAttribute("message", "Korisnik dodat u bazu");
		return "UnosKorisnika";
	}
	
	@RequestMapping(value = "logovanje", method = RequestMethod.POST)
	public String logovanje(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
		List<Korisnik> lista = ks.getKorisnik(username, password);
		if (lista.size() == 0) {
			request.setAttribute("message", "Neuspesno logovanje");
			return "Logovanje";
		}
		
		else {
			Korisnik k = lista.get(0);
			request.getSession().setAttribute("ulogovan", k);
			
		}
		
		return "Pocetna";
	}
	
	@GetMapping("odjavljivanje")
	public String odjavljivanje(HttpServletRequest request) {
		request.getSession().removeAttribute("ulogovan");
		return "Pocetna";
	}
	
	@ModelAttribute("topiks") 
	public List<Topik> getTopiks() {
		return ks.getTopiks();
	}
	
	@ModelAttribute("najaktivnijiDanas")
	public Korisnik getNajaktivnijegDanas() {
		Korisnik k = ks.getNajaktivnijegDanas();
		return k;
	}
	
	@GetMapping("getPocetna")
	public String getPocetna() {
		return "Pocetna";
	}
	
//	@RequestMapping(value = "novaUloga", method = RequestMethod.POST)
//	public String dodajUlogu(Model m) {
//		
//		Uloga u = new Uloga();
//		u.setNaziv("test");
//		ks.saveUloga(u);
//		m.addAttribute("test", "Baza radi ispravno!");
//		return "UnosKorisnika";
//	}
//	
//	@RequestMapping(value = "uloga-name", method = RequestMethod.GET)
//	public String getUloga(@RequestParam("naziv") String naziv, Model m) {
//		
//		Uloga u = ks.getUloga(naziv).get(0);
//		m.addAttribute("uloga", u.getNaziv());
//		return "UnosKorisnika";
//		
//	}

}
