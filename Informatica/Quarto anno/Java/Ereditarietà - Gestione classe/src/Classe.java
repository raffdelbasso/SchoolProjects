
public class Classe {
	private String nome;
	private char sezione;
	private int numStudenti;
	private int nCorrenti;
	private Studente[] elenco;
	
	public Classe(String nome, char sezione, int numStudenti) {
		this.nome=nome;
		this.sezione=sezione;
		this.numStudenti=numStudenti;
		elenco = new Studente[this.numStudenti];
		nCorrenti=0;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSezione() {
		return sezione;
	}

	public void setSezione(char sezione) {
		this.sezione = sezione;
	}

	public int getNumStudenti() {
		return numStudenti;
	}

	public Studente[] getElenco() {
		return elenco;
	}

	public void setElenco(Studente[] elenco) {
		this.elenco = elenco;
	}

	public void aggiungiStudente(Studente s) {
		int pos;
		pos = trovaPosizione(s);
		scalaStudenti(pos);
		elenco[pos]=s;
		nCorrenti++;
	}
	
	public int getnCorrenti() {
		return nCorrenti;
	}

	public void setnCorrenti(int nCorrenti) {
		this.nCorrenti = nCorrenti;
	}

	private int trovaPosizione(Studente s) {
		int pos=nCorrenti;
		int i=0;
		boolean trovato=false;
		while (elenco[i]!=null && !trovato) {
			if (s.getCognome().compareTo(elenco[i].getCognome())<0) {
				pos=i;
				trovato=true;
			} else {
				if (s.getCognome().compareTo(elenco[i].getCognome())==0) {
					if (s.getNome().compareTo(elenco[i].getNome())<0) {
						pos=i;
						trovato=true;
					}
				}
			}
			i++;
		}
		return pos;
	}
	
	private void scalaStudenti(int pos) {
		int i;
		for (i=nCorrenti; i>pos; i--) {
			elenco[i]=elenco[i-1];
		}
	}
	
	public void rimuoviStudente(int pos) {
		elenco[pos]=null;
		int i;
		nCorrenti--;
		for (i=pos; i<nCorrenti; i++) {
			elenco[i]=elenco[i+1];
		}
	}
	
	public int rimuoviStudente(String cognome) {
		int msg;
		msg=0;
		int i;
		i=0;
		boolean trovato=false;
		while(i<nCorrenti && !trovato) {
			if (elenco[i].getCognome().toLowerCase().equals(cognome.toLowerCase())) {
				trovato=true;
				rimuoviStudente(i);
			}
			i++;
		}
		if (!trovato) {
			msg=1;
		}
		return msg;
	}
}