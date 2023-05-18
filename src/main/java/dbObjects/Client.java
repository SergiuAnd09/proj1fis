package dbObjects;

public class Client {

	private String email;
	private String parola;
	private int id;
	private boolean seller;

	public Client(String email, String parola) {
		super();
		this.email = email;
		this.parola = parola;
		id = hashCode();
	}
	
	public boolean getSeller() {
		
		return this.seller;
	}
	
	public void setSeller(boolean seller_approve) {
		
		this.seller=seller_approve;
	}
	
	public Client() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

}
