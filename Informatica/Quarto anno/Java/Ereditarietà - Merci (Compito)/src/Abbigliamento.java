
public class Abbigliamento extends Merce {
	private char sesso;
	private String taglia;
	// Ho deciso di non rendere queste variabili final
	// in modo che l'utente possa cambiare i valori in qualsiasi momento.
	private int scontoUomo;
	private int scontoDonna;
	public Abbigliamento(String codice, float prezzoUnitario, char sesso, String taglia, int scontoUomo, int scontoDonna) {
		super(codice, prezzoUnitario);
		this.sesso = sesso;
		this.taglia = taglia;
		this.scontoUomo = scontoUomo;
		this.scontoDonna = scontoDonna;
	}
	
	public char getSesso() {
		return sesso;
	}
	
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	public String getTaglia() {
		return taglia;
	}
	
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	
	public int getScontoUomo() {
		return scontoUomo;
	}
	
	public void setScontoUomo(int scontoUomo) {
		this.scontoUomo = scontoUomo;
	}
	
	public int getScontoDonna() {
		return scontoDonna;
	}
	
	public void setScontoDonna(int scontoDonna) {
		this.scontoDonna = scontoDonna;
	}
	
	public float calcolaImporto(int quantita) {
		float prezzo = super.calcolaImporto(quantita);
		if (sesso == 'M' || sesso == 'm') {
			prezzo = prezzo - ((prezzo*(scontoUomo/100))*quantita);
		} else {
			prezzo = prezzo - ((prezzo*(scontoDonna/100))*quantita);
		}
		return prezzo;
	}
	
}
