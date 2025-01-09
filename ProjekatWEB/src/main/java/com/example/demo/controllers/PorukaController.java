package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.services.KorisnikService;
import com.example.demo.services.PorukaService;
import com.example.demo.services.ProfilService;

import jakarta.servlet.http.HttpServletRequest;
import model.Korisnik;
import model.Poruka;

@Controller
@RequestMapping("/poruke/")
public class PorukaController {
	
	@Autowired
	ProfilService ps;
	
	@Autowired
	KorisnikService ks;
	
	@Autowired
	PorukaService porukaService;
	
	@ModelAttribute("posiljaoci")
	public List<Korisnik> getPosiljaoce(HttpServletRequest request) {
		
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		return ps.getPosiljaoce(k.getIdkorisnik());
		
	}
	
	@PostMapping("getRazmenjenePoruke")
	public String getRazmenjenePoruke(@RequestParam("korisnik") Integer idkorisnik, HttpServletRequest request) {
		
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		List<Poruka> listaPoruka = ps.getRazmenjenePoruke((Integer)ulogovan.getIdkorisnik(), idkorisnik);
		request.setAttribute("razmenjenePoruke", listaPoruka);
		request.getSession().setAttribute("korisnik", ks.getKorisnik(idkorisnik));
		return "Poruke";
		
	}
	
	// MODIFICATION REQUIRED, korisniku treba da se azuriraju polja perhaps?
	
	@PostMapping("posaljiPoruku")
	public String posaljiPoruku(@RequestParam("sadrzaj") String sadrzaj, HttpServletRequest request) {
		
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		Korisnik primalac = (Korisnik) request.getSession().getAttribute("korisnik");
		
		porukaService.savePoruka(sadrzaj, ulogovan, primalac);
		request.setAttribute("poruka", "Uspesno poslata poruka!");
		
		//dodaj jos i povecanje broja poruka kod korisnika i promena statusa
		
		List<Poruka> listaPoruka = ps.getRazmenjenePoruke((Integer)ulogovan.getIdkorisnik(), primalac.getIdkorisnik());
		request.removeAttribute("razmenjenePoruke");
		request.setAttribute("razmenjenePoruke", listaPoruka);
		
		//return "redirect:/poruke/getStranica";
		return "Poruke";
	}
	
	@GetMapping("getStranica")
	public String getStranica() {
		return "Poruke";
	}

}
