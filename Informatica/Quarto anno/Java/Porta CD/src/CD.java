
public class CD {
	private String titolo;
	private String autore;
	private int annoUscita;
	
	public CD(String titolo, String autore, int annoUscita) {
		this.titolo = titolo;
		this.autore = autore;
		this.annoUscita = annoUscita;
	}
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public int getAnnoUscita() {
		return annoUscita;
	}
	public void setAnnoUscita(int annoUscita) {
		this.annoUscita = annoUscita;
	}
	public String toString() {
		return "Titolo: "+titolo+"\nAutore: "+autore+"\nAnno di uscita: "+annoUscita;
	}
}
