package dbObjects;

import java.util.Objects;

public class Client {

	private int id;
	private String email;
	private String parola;
	private int seller;

	public Client(int id, String email, String parola, int seller) {
		super();
		this.id = id;
		this.email = email;
		this.parola = parola;
		this.seller = seller;
	}
	
	public int getSeller() {
		
		return this.seller;
	}
	
	public void setSeller(int seller_approve) {
		
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
