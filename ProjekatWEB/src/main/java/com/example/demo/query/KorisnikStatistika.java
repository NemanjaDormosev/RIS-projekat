package com.example.demo.query;

public class KorisnikStatistika {
	
	private String username;
	private String status;
	private int brojObjava;
	private int brojKomentara;
	
	public KorisnikStatistika() {
		
	}
	
	public KorisnikStatistika(String username, String status, int brojObjava, int brojKomentara) {
		this.username = username;
		this.status = status;
		this.brojObjava = brojObjava;
		this.brojKomentara = brojKomentara;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public int getBrojObjava() {
		return brojObjava;
	}

	public void setBrojObjava(int brojObjava) {
		this.brojObjava = brojObjava;
	}

	public int getBrojKomentara() {
		return brojKomentara;
	}

	public void setBrojKomentara(int brojKomentara) {
		this.brojKomentara = brojKomentara;
	}

	@Override
	public String toString() {
		return "KorisnikStatistika [username=" + username + ", brojObjava=" + brojObjava + ", brojKomentara="
				+ brojKomentara + "]";
	}
	
}
