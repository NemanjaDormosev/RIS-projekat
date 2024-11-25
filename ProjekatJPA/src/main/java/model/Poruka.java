package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the poruka database table.
 * 
 */
@Entity
@NamedQuery(name="Poruka.findAll", query="SELECT p FROM Poruka p")
public class Poruka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idporuka;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private String sadrzaj;

	private Time vreme;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_idkorisnik")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="korisnik_idkorisnik2")
	private Korisnik korisnik2;

	public Poruka() {
	}

	public int getIdporuka() {
		return this.idporuka;
	}

	public void setIdporuka(int idporuka) {
		this.idporuka = idporuka;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getSadrzaj() {
		return this.sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Time getVreme() {
		return this.vreme;
	}

	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

}