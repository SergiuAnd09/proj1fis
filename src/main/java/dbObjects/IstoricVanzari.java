package dbObjects;

public class IstoricVanzari {

	private int id;
	private String nume;
	private float pret;
	private String email;
	private String emailSeller;
	public String getEmailSeller() {
		return emailSeller;
	}
	public void setEmailSeller(String emailSeller) {
		this.emailSeller = emailSeller;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public IstoricVanzari() {
		super();
	}
	@Override
	public String toString() {
		return "IstoricVanzari [id=" + id + ", nume=" + nume + ", pret=" + pret + ", email=" + email + ", emailSeller="
				+ emailSeller + "]";
	}
	public IstoricVanzari(int id, String nume, float pret, String email, String emailSeller) {
		super();
		this.id = id;
		this.nume = nume;
		this.pret = pret;
		this.email = email;
		this.emailSeller = emailSeller;
	}
	
	
}
