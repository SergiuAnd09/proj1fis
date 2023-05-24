package dbObjects;

public class Oferta {
	
	private int id_produs;
	private String email;
	private float pret;
	private String email_vanzator;
	private String name;
	public int getId_produs() {
		return id_produs;
	}
	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}
		public Oferta(int id_produs, String email, float pret, String email_vanzator, String name) {
			super();
			this.id_produs = id_produs;
			this.email = email;
			this.pret = pret;
			this.email_vanzator = email_vanzator;
			this.name = name;
		}
		public String getEmail_vanzator() {
			return email_vanzator;
		}
		public void setEmail_vanzator(String email_vanzator) {
			this.email_vanzator = email_vanzator;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public Oferta() {
		super();
	}
	
	@Override
	public String toString() {
		return "Oferta [id_produs=" + id_produs + ", email=" + email + ", pret=" + pret + "]";
	}
	
	
	
}
