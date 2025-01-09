package com.example.demo.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Tuple;
import model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	List<Korisnik> findByUsernameAndPassword(String username, String password);
	
	Optional<Korisnik> findByUsername(String username);

	@Query("select k from Korisnik k left join Objava o on o.korisnik.idkorisnik = k.idkorisnik left join Komentar kom on kom.korisnik.idkorisnik = k.idkorisnik where o.datum = :datum or kom.datum = :datum group by k.idkorisnik order by count(idobjava) desc, count(idkomentar) desc")
	List<Korisnik> pronadjiNajaktivnijeZaDatum(@Param("datum") Date datum);
	
	@Query("select k from Korisnik k left join Objava o on o.korisnik.idkorisnik = k.idkorisnik left join Komentar kom on kom.korisnik.idkorisnik = k.idkorisnik group by idkorisnik order by count(idobjava) desc, count(idkomentar) desc")
	List<Korisnik> pronadjiSveAktivneKorisnike();

	@Query("select k from Korisnik k where k.idkorisnik in (select p.korisnik1.idkorisnik from Poruka p where korisnik2.idkorisnik = :idkorisnik) or k.idkorisnik in (select p.korisnik2.idkorisnik from Poruka p where p.korisnik1.idkorisnik = :idkorisnik)")
	List<Korisnik> pronadjiSvePosiljaoce(@Param("idkorisnik") Integer idKorisnik);
	
	@Query("select k.username, count(idobjava) from Objava o right join Korisnik k on o.korisnik.idkorisnik = k.idkorisnik group by k.username order by count(idobjava) desc")
	List<Tuple> getStatistikaObjava();
	
	@Query("select k.username, count(idkomentar) from Komentar kom right join Korisnik k on kom.korisnik.idkorisnik = k.idkorisnik group by k.username order by count(idkomentar) desc")
	List<Tuple> getStatistikaKomentara();
	
	@Transactional
	@Modifying
	@Query("update Korisnik k set k.password = :password where k.idkorisnik = :idkorisnik")
	int updatePassword(@Param("password") String password, @Param("idkorisnik") Integer idKorisnik);

}
