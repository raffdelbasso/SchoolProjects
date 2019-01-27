
public class Portachiavi {
	private Chiave chiavi[];
	
	// Dimensione portachiavi: 10
	private final int capienza = 10;
	
	public Portachiavi() {
		chiavi = new Chiave[capienza];
	}
	
	// Metodo che deposita la chiave ricevuta in input nella posizione ricevuta in input
	public void depositaChiave(Chiave chiave, int pos) {
		chiavi[pos] = chiave;
	}
	
	// Metodo che, dato il numero della stanza, restituisce 0
	// se non c'è nessuna chiave con quel numero, o 1 se invece c'è.
	// Se l'output del metodo è 1, la chiave nella posizione trovata
	// sarà eguagliata a null, perchè la chiave sarà stata prelevata.
	public int prelevaChiave(int nStanza) {
		int i;
		boolean trovato;
		i=0;
		trovato=false;
		while (i<capienza && !trovato) {
			if (chiavi[i]!=null) {
				if (chiavi[i].getnStanza()==nStanza) {
					trovato=true;
					chiavi[i]=null;
				}
			}
			i++;
		}
		if (!trovato) {
			return 0;
		} else {
			return 1;
		}
	}
	
	// Questo metodo fa la stessa cosa, ma riceve il nominativo anzichè
	// il numero della stanza
	public int prelevaChiave(String nominativo) {
		int i;
		boolean trovato;
		i=0;
		trovato=false;
		while (i<capienza && !trovato) {
			if (chiavi[i]!=null) {
				// Questo metodo cerca le chiavi senza tener conto dell'uguaglianza
				// tra maiuscole e minuscole (case insensitive)
				if (chiavi[i].getNominativo().toLowerCase().equals(nominativo.toLowerCase())) {
					trovato=true;
					chiavi[i]=null;
				}
			}
			i++;
		}
		if (!trovato) {
			return 0;
		} else {
			return 1;
		}
	}
	
	// Metodo che calcola la posizione della prima posizione libera.
	// Se non c'è nessuna posizione libera, pos rimarrà -1
	public int calcolaPos() {
		int i, pos;
		i=0;
		pos=-1;
		while (i<capienza && pos==-1) {
			if (chiavi[i]==null) {
				pos=i;
			}
			i++;
		}
		return pos;
	}
	
	// Metodo che visualizza lo stato dell'intero portachiavi
	// richiamando la toString della classe Chiave
	public String visualizzaChiavi() {
		int i;
		String msg="\n";
		for (i=0; i<capienza; i++) {
			msg+="Posizione "+i+":\n";
			if (chiavi[i]==null) {
				msg+="Vuota";
			} else {
				msg+=chiavi[i].toString();
			}
			msg+="\n\n";
		}
		return msg;
	}
}
