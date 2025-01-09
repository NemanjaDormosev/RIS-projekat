package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the objava database table.
 * 
 */
@Entity
@NamedQuery(name="Objava.findAll", query="SELECT o FROM Objava o")
public class Objava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idobjava;

	@Temporal(TemporalType.DATE)
	private Date datum;
	
	private String naslov;

	private String sadrzaj;

	private Time vreme;

	//bi-directional many-to-many association to Kategorija
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.ALL })
	@JoinTable(
		name="imaobjavu"
		, joinColumns={
			@JoinColumn(name="objava_idobjava")
			}
		, inverseJoinColumns={
			@JoinColumn(name="kategorija_idkategorija")
			}
		)
	private List<Kategorija> kategorijas;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="objava", cascade = CascadeType.ALL)
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Topik
	@ManyToOne
	private Topik topik;

	public Objava() {
		this.kategorijas = new ArrayList<Kategorija>();
	}

	public int getIdobjava() {
		return this.idobjava;
	}

	public void setIdobjava(int idobjava) {
		this.idobjava = idobjava;
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

	public List<Kategorija> getKategorijas() {
		return this.kategorijas;
	}

	public void setKategorijas(List<Kategorija> kategorijas) {
		this.kategorijas = kategorijas;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setObjava(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setObjava(null);

		return komentar;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Topik getTopik() {
		return this.topik;
	}

	public void setTopik(Topik topik) {
		this.topik = topik;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

}