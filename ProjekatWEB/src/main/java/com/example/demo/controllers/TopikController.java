package com.example.demo.controllers;


import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.TopikService;

import jakarta.servlet.http.HttpServletRequest;
import model.Korisnik;
import model.Topik;

@Controller
@RequestMapping("/topik/")
public class TopikController {
	
	@Autowired
	TopikService ts;
	
	@RequestMapping(value = "dodajTopik", method = RequestMethod.POST)
	public String dodajTopik(@RequestParam("naziv") String naziv, HttpServletRequest request) {
		
		Topik topik = new Topik();
		topik.setNaziv(naziv);
		topik.setKorisnik((Korisnik)request.getSession().getAttribute("ulogovan"));
		topik.setDatum(new Date());
		topik.setVreme(Time.valueOf(LocalTime.now()));
		ts.dodajTopik(topik);
		
		return "redirect:/korisnik/getPocetna";
	}
	

}
