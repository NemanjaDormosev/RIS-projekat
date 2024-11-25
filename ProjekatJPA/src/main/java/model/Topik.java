package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the topik database table.
 * 
 */
@Entity
@NamedQuery(name="Topik.findAll", query="SELECT t FROM Topik t")
public class Topik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtopik;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private String naziv;

	private Time vreme;

	//bi-directional many-to-one association to Objava
	@OneToMany(mappedBy="topik")
	private List<Objava> objavas;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Topik() {
	}

	public int getIdtopik() {
		return this.idtopik;
	}

	public void setIdtopik(int idtopik) {
		this.idtopik = idtopik;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Time getVreme() {
		return this.vreme;
	}

	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}

	public List<Objava> getObjavas() {
		return this.objavas;
	}

	public void setObjavas(List<Objava> objavas) {
		this.objavas = objavas;
	}

	public Objava addObjava(Objava objava) {
		getObjavas().add(objava);
		objava.setTopik(this);

		return objava;
	}

	public Objava removeObjava(Objava objava) {
		getObjavas().remove(objava);
		objava.setTopik(null);

		return objava;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}