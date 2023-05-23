package dbObjects;

import java.util.Objects;

public class Produs {

	private int id;
	private String nume;
	private float pret;
	private int id_vanzator;
	private String descriere;
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
	public int getId_vanzator() {
		return id_vanzator;
	}
	public void setId_vanzator(int id_vanzator) {
		this.id_vanzator = id_vanzator;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public Produs(int id, String nume, float pret, int id_vanzator, String descriere) {
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;
		this.id_vanzator = id_vanzator;
		this.descriere = descriere;
	}
	public Produs() {
		super();
	}
	@Override
	public String toString() {
		return "Produs [id=" + id + ", nume=" + nume + ", pret=" + pret + ", id_vanzator=" + id_vanzator
				+ ", descriere=" + descriere + "]";
	}
	
	
	
}
