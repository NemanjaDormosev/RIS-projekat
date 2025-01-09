package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.services.ObjavaService;
import com.example.demo.services.ProfilService;

import jakarta.servlet.http.HttpServletRequest;
import model.Kategorija;
import model.Korisnik;
import model.Objava;
import model.Topik;

@Controller
@RequestMapping("/kategorija/")
public class KategorijaController {
	
	@Autowired
	ProfilService ps;
	
	@Autowired
	ObjavaService os;
	
	@PostMapping("dodajObjavu")
	public String dodajObjavu(@RequestParam("kategorija") Integer idkategorija, @RequestParam("idobjava") Integer idobjava, HttpServletRequest request, RedirectAttributes model) {
		
		boolean postoji = ps.proveriKategoriju(idkategorija, idobjava);
		
		if (!postoji) {
			Kategorija k = ps.getKategorija(idkategorija);
			Objava o = os.getObjava(idobjava);
			ps.dodajImaObjavu(k, o);
		}
		
		Topik t = (Topik) request.getSession().getAttribute("topik");
		model.addAttribute("idTopik", t.getIdtopik());
		return "redirect:/objava/getStranica";
	}
	
	
	@PostMapping("napraviKategoriju")
	public String napraviKategoriju(@RequestParam("naziv") String naziv, HttpServletRequest request, RedirectAttributes model) {
		
		Korisnik ulogovan = (Korisnik) request.getSession().getAttribute("ulogovan");
		Objava o = (Objava) request.getSession().getAttribute("objava");
		ps.saveKategorija(naziv, ulogovan, o);
		
		request.getSession().removeAttribute("objava");
		
		Topik t = (Topik) request.getSession().getAttribute("topik");
		model.addAttribute("idTopik", t.getIdtopik());
		return "redirect:/objava/getStranica";
	}
	
	@GetMapping("getStranica")
	public String getStranica(@RequestParam("idobjava") Integer idobjava, HttpServletRequest request) {
		request.getSession().setAttribute("objava", os.getObjava(idobjava));
		return "DodavanjeUKategoriju";
		
	}
	
	
	//implementirati da se prosledi id objave i onda proveriti da li je vec sacuvana objava u toj kategoriji
	//inace dodati objavu u odabranu kategoriju i vratiti na stranicu Objave.jsp 
	
}
