package model;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idkategorija;

	private String naziv;

	//bi-directional many-to-many association to Objava
	@ManyToMany(mappedBy="kategorijas", fetch = FetchType.EAGER)
	private List<Objava> objavas;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Kategorija() {
		this.objavas = new ArrayList<Objava>();
	}

	public int getIdkategorija() {
		return this.idkategorija;
	}

	public void setIdkategorija(int idkategorija) {
		this.idkategorija = idkategorija;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Objava> getObjavas() {
		return this.objavas;
	}

	public void setObjavas(List<Objava> objavas) {
		this.objavas = objavas;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}