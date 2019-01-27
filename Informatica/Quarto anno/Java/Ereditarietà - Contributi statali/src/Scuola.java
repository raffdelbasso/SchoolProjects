
public class Scuola {
	private String codice;
	private String denominazione;
	private String indirizzo;
	private String citta;
	private int nStudenti;
	private int nClassi;
	private int nSedi;
	private int nLabs;
	public Scuola(String codice, String denominazione, String indirizzo, String citta, int nStudenti, int nClassi,
			int nSedi, int nLabs) {
		this.codice = codice;
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.nStudenti = nStudenti;
		this.nClassi = nClassi;
		this.nSedi = nSedi;
		this.nLabs = nLabs;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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
	public int getnStudenti() {
		return nStudenti;
	}
	public void setnStudenti(int nStudenti) {
		this.nStudenti = nStudenti;
	}
	public int getnClassi() {
		return nClassi;
	}
	public void setnClassi(int nClassi) {
		this.nClassi = nClassi;
	}
	public int getnSedi() {
		return nSedi;
	}
	public void setnSedi(int nSedi) {
		this.nSedi = nSedi;
	}
	public int getnLabs() {
		return nLabs;
	}
	public void setnLabs(int nLabs) {
		this.nLabs = nLabs;
	}
	@Override
	public String toString() {
		String msg;
		msg = "Codice: " + codice;
		msg += "\nDenominazione: " + denominazione;
		msg += "\nIndirizzo: " + indirizzo;
		msg += "\nCittà: " + citta;
		msg += "\nNumero studenti: " + nStudenti;
		msg += "\nNumero classi: " + nClassi;
		msg += "\nNumero sedi: " + nSedi;
		msg += "\nNumero laboratori: " + nLabs+"\n";
		return msg;
	}
	
	
	
}
