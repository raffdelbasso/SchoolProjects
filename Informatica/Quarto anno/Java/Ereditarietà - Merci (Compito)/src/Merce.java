
public class Merce {
	private String codice;
	private float prezzoUnitario;
	public Merce(String codice, float prezzoUnitario) {
		this.codice = codice;
		this.prezzoUnitario = prezzoUnitario;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public float getPrezzoUnitario() {
		return prezzoUnitario;
	}
	
	public void setPrezzoUnitario(float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	
	public float calcolaImporto(int quantita) {
		return quantita*prezzoUnitario;
	}
}
