package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.ProfilService;

import jakarta.servlet.http.HttpServletRequest;
import model.Kategorija;
import model.Korisnik;
import model.Objava;

@Controller
@RequestMapping("/profil/")
public class ProfilController {
	
	
	@Autowired
	ProfilService ps;
	
	
	@ModelAttribute("brojObjava")
	public int getBrojObjava(HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Integer idKorisnik = k.getIdkorisnik();
		int brojObjava = ps.getBrojObjava(idKorisnik);
		System.out.println("Objava: " + brojObjava);
		return brojObjava;
	}
	
	@ModelAttribute("brojKomentara")
	public int getBrojKomentara(HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Integer idKorisnik = k.getIdkorisnik();
		int brojKomentara = ps.getBrojKomentara(idKorisnik);
		System.out.println("Komentara: " + brojKomentara);
		return brojKomentara;
	}
	
	//utvrditi da li je model ili session (posto ce trebati poruke kasnije)
	@ModelAttribute("brojPoruka") 
	public int getBrojPoruka(HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Integer idKorisnik = k.getIdkorisnik();
		int brojPoruka = ps.getBrojPoruka(idKorisnik);
		System.out.println(k.getUsername());
		System.out.println("Poruka: " + brojPoruka);
		return brojPoruka;
		
	}
	
	@ModelAttribute("kategorije")
	public List<Kategorija> getKategorije(HttpServletRequest request) {
		Korisnik k = (Korisnik) request.getSession().getAttribute("ulogovan");
		Integer idKorisnik = k.getIdkorisnik();
		return ps.getKategorije(idKorisnik);
	}
	
	@GetMapping("getObjaveZaKategoriju")
	public String getObjaveZaKategoriju(@RequestParam("kategorija") Integer idkategorija, HttpServletRequest request) {
		
		List<Objava> listaObjava = ps.getObjaveZaKategoriju(idkategorija);
		
		request.getSession().setAttribute("kategorija", ps.getKategorija(idkategorija));
		request.setAttribute("objave", listaObjava);
		
		return "PrikazKategorije";
		
	}
		
	@GetMapping("getProfil")
	public String getProfil() {
		return "Profil";
	}
}
