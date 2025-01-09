package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Objava;

@Repository
public interface ObjavaRepository extends JpaRepository<Objava, Integer> {
	
	int countByKorisnik_idkorisnik(Integer idKorisnik);
	
	List<Objava> findByTopik_idtopik(Integer idTopik);
	
	@Query("select o from Objava o inner join o.kategorijas ok where ok.idkategorija = :idkategorija")
	List<Objava> pronadjiSveObjaveZaKategoriju(@Param("idkategorija") Integer idkategorija);
	
	@Query("select o from Objava o where o.topik.idtopik = :idtopik and o.naslov like %:rec% and o.datum = :datum")
	List<Objava> pronadjiObjavePoKriterijumu(@Param("idtopik") Integer idtopik, @Param("rec") String rec, @Param("datum") Date datum);
	
	@Query("select o from Objava o where o.topik.idtopik = :idtopik and o.naslov like %:rec%")
	List<Objava> pronadjiObjavePoReci(@Param("idtopik") Integer idtopik, @Param("rec") String rec);
	
	@Query("select o from Objava o where o.topik.idtopik = :idtopik and o.datum = :datum")
	List<Objava> pronadjiObjavePoDatumu(@Param("idtopik") Integer idtopik, @Param("datum") Date datum);
	
	@Query("select o from Objava o where o.topik.idtopik = :idtopik order by o.naslov asc")
	List<Objava> getSortiraneObjavePoNaslovu(@Param("idtopik") Integer idtopik);
	
	@Query("select o from Objava o where o.topik.idtopik = :idtopik order by o.datum desc, o.vreme desc")
	List<Objava> getSortiraneObjavePoDatumu(@Param("idtopik") Integer idtopik);
	
	@Query("select o from Objava o inner join o.kategorijas ok where ok.idkategorija = :idkategorija and o.idobjava = :idobjava")
	List<Objava> pronadjiKategoriju(@Param("idkategorija") Integer idkategorija, @Param("idobjava") Integer idobjava);

}
