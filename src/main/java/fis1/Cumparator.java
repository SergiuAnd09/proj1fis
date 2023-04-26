package fis1;

public class Cumparator {

	private String nume;
	private Status status;
	
	public Cumparator() {}
	
	public Cumparator(String nume, Status status) {
		
		this.nume = nume;
		this.status = status;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cumparator [nume=" + nume + ", status=" + status + "]";
	}

	
	
	
}
