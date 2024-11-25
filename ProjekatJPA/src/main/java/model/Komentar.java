package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the komentar database table.
 * 
 */
@Entity
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idkomentar;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private String sadrzaj;

	private Time vreme;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Objava
	@ManyToOne
	private Objava objava;

	public Komentar() {
	}

	public int getIdkomentar() {
		return this.idkomentar;
	}

	public void setIdkomentar(int idkomentar) {
		this.idkomentar = idkomentar;
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

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Objava getObjava() {
		return this.objava;
	}

	public void setObjava(Objava objava) {
		this.objava = objava;
	}

}