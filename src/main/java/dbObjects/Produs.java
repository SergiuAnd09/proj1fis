package dbObjects;

import java.util.Objects;

public class Produs {

	private int id;
	private String nume;
	private float pret;
	private String email;
	private String descriere;
	private float pret_minim;
	public float getPret_minim() {
		return pret_minim;
	}
	public void setPret_minim(float pret_minim) {
		this.pret_minim = pret_minim;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	
	public Produs(int id, String nume, float pret, String email, String descriere) {
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;
		this.email = email;
		this.descriere = descriere;
		this.pret_minim=-1;
	}
	public Produs(int id, String nume, float pret, String email, String descriere, float pret_minim) {
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;
		this.email = email;
		this.descriere = descriere;
		this.pret_minim = pret_minim;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public Produs() {
		super();
	}
	@Override
	public String toString() {
		return "Produs [id=" + id + ", nume=" + nume + ", pret=" + pret + ", email=" + email + ", descriere="
				+ descriere + ", pret_minim=" + pret_minim + "]";
	}
	
	
	
}
