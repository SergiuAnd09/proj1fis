package dbObjects;

public class IstoricVanzari {

	private int id_produs;
	public int getId_produs() {
		return id_produs;
	}
	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}
	private float pret;
	private int id_cumparator;
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public int getId_cumparator() {
		return id_cumparator;
	}
	public void setId_cumparator(int id_cumparator) {
		this.id_cumparator = id_cumparator;
	}
	public IstoricVanzari() {
		super();
	}
	@Override
	public String toString() {
		return "IstoricVanzari [pret=" + pret + ", id_cumparator=" + id_cumparator + "]";
	}
	public IstoricVanzari(int id_produs, float pret, int id_cumparator) {
		super();
		this.id_produs = id_produs;
		this.pret = pret;
		this.id_cumparator = id_cumparator;
	}
}
