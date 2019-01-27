
public class Chiave {
	private int nStanza;
	private String nominativo;
	
	public Chiave(int nStanza, String nominativo) {
		this.nStanza = nStanza;
		this.nominativo = nominativo;
	}

	public int getnStanza() {
		return nStanza;
	}

	public void setnStanza(int nStanza) {
		this.nStanza = nStanza;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	
	// toString che visualizza il numero della stanza
	// e il proprietario associati alla chiave
	public String toString() {
		return "Numero stanza: "+nStanza+"\nProprietario: "+nominativo;
	}
}
