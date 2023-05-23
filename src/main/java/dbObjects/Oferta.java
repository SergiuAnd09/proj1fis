package dbObjects;

public class Oferta {
	
	private int id_produs;
	private int id_cumparator;
	private float pret;
	public int getId_produs() {
		return id_produs;
	}
	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}
	public int getId_cumparator() {
		return id_cumparator;
	}
	public void setId_cumparator(int id_cumparator) {
		this.id_cumparator = id_cumparator;
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
	public Oferta(int id_produs, int id_cumparator, float pret) {
		super();
		this.id_produs = id_produs;
		this.id_cumparator = id_cumparator;
		this.pret = pret;
	}
	@Override
	public String toString() {
		return "Oferta [id_produs=" + id_produs + ", id_cumparator=" + id_cumparator + ", pret=" + pret + "]";
	}
	
	
	
}
