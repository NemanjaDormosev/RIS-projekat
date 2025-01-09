package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.KorisnikService;
import com.example.demo.services.ProfilService;

import model.Korisnik;

@Controller
@RequestMapping("/statistika/")
public class StatistikaController {
	
	
	@Autowired
	KorisnikService ks;
	
	@Autowired
	ProfilService ps;
	
	
	@ModelAttribute("sviAktivniKorisnici")
	public List<Korisnik> getSveAktivneKorisnike() {
		return ks.getSveAktivneKorisnike();
	}
	
	@GetMapping("prikaziAktivnost")
	public String getBrojObjavaIKomentara(@RequestParam("idkorisnik") Integer idkorisnik, Model m) {
		
		m.addAttribute("brojObjava", ps.getBrojObjava(idkorisnik));
		m.addAttribute("brojKomentara", ps.getBrojKomentara(idkorisnik));
		
		return "Statistika";
		
		
	}
	
	@GetMapping("getStranica")
	public String getStranica() {
//		List<Tuple> lista = ks.getStatistikaForuma();
//		for (Tuple t: lista) {
//			System.out.println(t.get(0) + " " + t.get(1) + " " + t.get(2));
//		}
		return "Statistika";
	}

}
