package com.example.demo.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.query.KorisnikStatistika;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.ObjavaRepository;
import com.example.demo.repositories.TopikRepository;

import jakarta.persistence.Tuple;
import model.Objava;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class IzvestajService {
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	ObjavaRepository objavaRepo;
	
	@Autowired
	TopikRepository topikRepo;
	
	
	public List<KorisnikStatistika> convertStatistika(List<Tuple> statsObjava, List<Tuple> statsKomentara) {
		
		List<KorisnikStatistika> resultList = new ArrayList<KorisnikStatistika>();
		
		int i = 0;
		for (Tuple t: statsObjava) {
			String username = (String) t.get(0);
			String status = korisnikRepo.findByUsername(username).get().getStatus();
			int brojObjava = ((Long) t.get(1)).intValue();
			int brojKomentara = 0;
			
			for (Tuple t2: statsKomentara) {
				if (t2.get(0).equals(username)) {
					 brojKomentara = ((Long) t2.get(1)).intValue();
				}
			}
			
			KorisnikStatistika ks = new KorisnikStatistika();
			ks.setUsername(username);
			ks.setStatus(status);
			ks.setBrojObjava(brojObjava);
			ks.setBrojKomentara(brojKomentara);
			
			System.out.println(username + " " + status + " " + brojObjava + " " + brojKomentara);
			
			resultList.add(ks);
		}
		
		return resultList;
		
		
	}
		
	
	
//	public List<KorisnikStatistika> convertStatistika(List<Tuple> listaQuery) {
//		
//		List<KorisnikStatistika> resultList = new ArrayList<KorisnikStatistika>();
//		
//		for (Tuple t: listaQuery) {
//			String username = (String) t.get(0);
//			String status = korisnikRepo.findByUsername(username).get().getStatus();
//			int brojObjava = ((Long) t.get(1)).intValue();
//			int brojKomentara = ((Long) t.get(2)).intValue();
//			
//			KorisnikStatistika ks = new KorisnikStatistika();
//			ks.setUsername(username);
//			ks.setStatus(status);
//			ks.setBrojObjava(brojObjava);
//			ks.setBrojKomentara(brojKomentara);
//			
//			resultList.add(ks);
//		}
//		
//		return resultList;
//		
//	}

	public JasperPrint generisiIzvestajZaTopik(Integer idtopik) throws JRException, IOException {
		
			System.out.println("Broj objava: "+ objavaRepo.findByTopik_idtopik(idtopik).size()); 
			List<Objava> listaObjava = objavaRepo.findByTopik_idtopik(idtopik);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaObjava);
			InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/objave.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("nazivTopika", topikRepo.findById(idtopik).get().getNaziv());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			inputStream.close();
			return jasperPrint;
	}

	public JasperPrint generisiIzvestajZaStatistiku() throws JRException, IOException {
		
		System.out.println("Broj korisnika (statistika): "+ korisnikRepo.getStatistikaObjava().size()); 
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(convertStatistika(korisnikRepo.getStatistikaObjava(), korisnikRepo.getStatistikaKomentara()));
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/statistika.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		return jasperPrint;
	}

}
