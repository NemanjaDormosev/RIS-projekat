package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idkorisnik;

	private String email;

	private int godine;

	private String ime;

	private String password;

	private String prezime;

	private String status;

	private String username;

	@Temporal(TemporalType.DATE)
	private Date zabrana;

	// bi-directional many-to-one association to Kategorija
	@OneToMany(mappedBy = "korisnik")
	private List<Kategorija> kategorijas;

	// bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy = "korisnik")
	private List<Komentar> komentars;

	// bi-directional many-to-one association to Uloga
	@ManyToOne
	private Uloga uloga;

	// bi-directional many-to-one association to Objava
	@OneToMany(mappedBy = "korisnik")
	private List<Objava> objavas;

	// bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy = "korisnik1")
	private List<Poruka> porukas1;

	// bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy = "korisnik2")
	private List<Poruka> porukas2;

	// bi-directional many-to-one association to Topik
	@OneToMany(mappedBy = "korisnik")
	private List<Topik> topiks;

	public Korisnik() {
	}

	public Korisnik(String ime, String prezime, int godine, String email, String username, String password, Uloga uloga) {
		this.ime = ime;
		this.prezime = prezime;
		this.godine = godine;
		this.email = email;
		this.username = username;
		this.password = password;
		this.uloga = uloga;
	}

	public int getIdkorisnik() {
		return this.idkorisnik;
	}

	public void setIdkorisnik(int idkorisnik) {
		this.idkorisnik = idkorisnik;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGodine() {
		return this.godine;
	}

	public void setGodine(int godine) {
		this.godine = godine;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getZabrana() {
		return this.zabrana;
	}

	public void setZabrana(Date zabrana) {
		this.zabrana = zabrana;
	}

	public List<Kategorija> getKategorijas() {
		return this.kategorijas;
	}

	public void setKategorijas(List<Kategorija> kategorijas) {
		this.kategorijas = kategorijas;
	}

	public Kategorija addKategorija(Kategorija kategorija) {
		getKategorijas().add(kategorija);
		kategorija.setKorisnik(this);

		return kategorija;
	}

	public Kategorija removeKategorija(Kategorija kategorija) {
		getKategorijas().remove(kategorija);
		kategorija.setKorisnik(null);

		return kategorija;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Objava> getObjavas() {
		return this.objavas;
	}

	public void setObjavas(List<Objava> objavas) {
		this.objavas = objavas;
	}

	public Objava addObjava(Objava objava) {
		getObjavas().add(objava);
		objava.setKorisnik(this);

		return objava;
	}

	public Objava removeObjava(Objava objava) {
		getObjavas().remove(objava);
		objava.setKorisnik(null);

		return objava;
	}

	public List<Poruka> getPorukas1() {
		return this.porukas1;
	}

	public void setPorukas1(List<Poruka> porukas1) {
		this.porukas1 = porukas1;
	}

	public Poruka addPorukas1(Poruka porukas1) {
		getPorukas1().add(porukas1);
		porukas1.setKorisnik1(this);

		return porukas1;
	}

	public Poruka removePorukas1(Poruka porukas1) {
		getPorukas1().remove(porukas1);
		porukas1.setKorisnik1(null);

		return porukas1;
	}

	public List<Poruka> getPorukas2() {
		return this.porukas2;
	}

	public void setPorukas2(List<Poruka> porukas2) {
		this.porukas2 = porukas2;
	}

	public Poruka addPorukas2(Poruka porukas2) {
		getPorukas2().add(porukas2);
		porukas2.setKorisnik2(this);

		return porukas2;
	}

	public Poruka removePorukas2(Poruka porukas2) {
		getPorukas2().remove(porukas2);
		porukas2.setKorisnik2(null);

		return porukas2;
	}

	public List<Topik> getTopiks() {
		return this.topiks;
	}

	public void setTopiks(List<Topik> topiks) {
		this.topiks = topiks;
	}

	public Topik addTopik(Topik topik) {
		getTopiks().add(topik);
		topik.setKorisnik(this);

		return topik;
	}

	public Topik removeTopik(Topik topik) {
		getTopiks().remove(topik);
		topik.setKorisnik(null);

		return topik;
	}

}