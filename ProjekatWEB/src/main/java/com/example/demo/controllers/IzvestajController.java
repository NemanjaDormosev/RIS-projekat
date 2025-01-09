package com.example.demo.controllers;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.IzvestajService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/izvestaj/")
public class IzvestajController {

	@Autowired
	IzvestajService is;
	
	@GetMapping("izvestajZaTopik")
	public void generisiIzvestajZaTopik(@RequestParam("idtopik")Integer idtopik, HttpServletResponse response, Model m) {
		try {
			JasperPrint jasperReport = is.generisiIzvestajZaTopik(idtopik);
			response.setContentType("text/html");
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=izvestajZaTopik.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperReport, out);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
//		return "PreuzimanjeIzvestaja";
	}
	
	@GetMapping("izvestajZaStatistiku")
	public void generisiIzvestajZaStatistiku(HttpServletResponse response, Model m) {
		try {
			JasperPrint jasperReport = is.generisiIzvestajZaStatistiku();
			response.setContentType("text/html");
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename=izvestajZaStatistiku.pdf");
			OutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperReport, out);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
//		return "PreuzimanjeIzvestaja";
	}
	
}
