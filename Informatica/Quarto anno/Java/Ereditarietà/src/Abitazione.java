
public class Abitazione {
	private int numStanze;
	private String indirizzo;
	private String citta;
	public Abitazione(int numStanze, String indirizzo, String citta) {
		this.numStanze = numStanze;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}
	public int getNumStanze() {
		return numStanze;
	}
	public void setNumStanze(int numStanze) {
		this.numStanze = numStanze;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String toString() {
		String s;
		s="Numero stanze: "+numStanze;
		s=s+"\nIndirizzo: "+indirizzo;
		s=s+"\nCittà: "+citta;
		return s+"\n";
	}
}