package dbObjects;

public class IstoricVanzari {

	private int id;
	private String nume;
	private float pret;
	private String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "IstoricVanzari [id=" + id + ", nume=" + nume + ", pret=" + pret + ", email=" + email + "]";
	}
	public IstoricVanzari() {
		super();
	}
	public IstoricVanzari(int id, String nume, float pret, String email) {
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;
		this.email = email;
	}
	
	
}
