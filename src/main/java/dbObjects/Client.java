package dbObjects;

import java.util.Objects;

public class Client {

	private String email;
	private String parola;
	private int seller;
	
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
	public int getSeller() {
		return seller;
	}
	public void setSeller(int seller) {
		this.seller = seller;
	}
	public Client() {
		super();
	}
	public Client(String email, String parola, int seller) {
		super();
		this.email = email;
		this.parola = parola;
		this.seller = seller;
	}
	@Override
	public String toString() {
		return "Client [email=" + email + ", parola=" + parola + ", seller=" + seller + "]";
	}

	

}
