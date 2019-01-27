
public class RegoleGioco {
	private Matrice genAttuale;
	private Matrice genPrecedente;
	private int nr;
	private int nc;
	
	// Il costruttore riceve il numero di righe e colonne,
	// e crea la generazione attuale e quella precedente.
	public RegoleGioco(int nr, int nc) {
		this.nr = nr;
		this.nc = nc;
		genAttuale = new Matrice(nr, nc);
		genPrecedente = new Matrice(nr,nc);
	}
	// Questo metodo riempie la matrice genAttuale
	// con valori random 0 o 1
	public void primaGenerazione() {
		genAttuale.genera(0, 1);
	}
	
	/*
	 *Questo metodo controlla e restituisce il numero di celle vive attorno a quella data.
	 * La cella data si identifica con i parametri di riga o colonna passati
	 * al metodo. Si utilizza il modulo per evitare la creazione di una seconda matrice sferica.
	 */
	public int contaIntorno(int r, int c) {
		int i, j, cont;
		cont = 0;
		
		for(i=r-1; i<=r+1; i++) {
			for(j=c-1; j<=c+1; j++) {
				cont = cont + genPrecedente.getElem((i+nr)%nr, (j+nc)%nc);
			}
		}
		cont = cont - genPrecedente.getElem(r, c);
		return cont;
	}
	
	// Questo metodo cambia lo stato di una cellula data passandogli il suo indice di riga e colonna
	// andando ad analizzarla con il metodo precendentemente visto.
	// Questo metodo restituisce 1 se la cellula analizzata sarà viva
	// o 0 se morirà
	public int nuovoStatoCellula(int r, int c) {
		int cont;
		cont = contaIntorno(r,c);
		// Se la cellula è viva
		if(genPrecedente.getElem(r, c)==0) {
			// Regole del gioco: se ci sono esattamente 3 cellule vive, nascerà
			if(cont==3) {
				return 1;
			}else {
				// Altrimenti resta morta.
				return 0;
			}
		} else {
			// Se la cellula è morta
			if(cont<2 || cont>3) {
				// Se non ci sono esattamente 3 celle vive, la cellula muore
				return 0;
			}else {
				// Altrimenti resta viva
				return 1;
			}
		}
	}
	
	// Questo metodo genera la nuova generazione in base al risultato
	// del metodo nuovoStatoCellula, che a sua volta richiama il metodo
	// contaIntorno.
	// Ogni cella della matrice cambierà il suo valore attraverso il setElem, andando ad
	// effettuare i vari controlli attraverso i metodi visti prima.
	public void nuovaGenerazione() {
		int r, c;
		for(r=0; r<nr; r++) {
			for(c=0; c<nc; c++) {
				genAttuale.setElem(r, c, nuovoStatoCellula(r,c));
			}
		}
	}
	
	// Questo metodo copia la generazione attuale mettendola nella
	// precedente. Il procedimento è molto simile al metodo situato in alto,
	// solo che il setElem è fatto su genPrecedente perchè ciascun elemento
	// si riempie prendendolo da genAttuale.
	public void copiaAttualeInPrecedente() {
		int r,c;
		for(r=0; r<nr; r++) {
			for(c=0; c<nc; c++) {
				genPrecedente.setElem(r, c, genAttuale.getElem(r, c));
			}
		}
	}
	
	// Getters del numero di righe e colonne, e delle due matrici
	// Non hanno parametri in input perchè restituiscono semplicemente dei valori.
	public int getNr() {
		return nr;
	}
	
	public int getNc() {
		return nc;
	}

	public Matrice getGenAttuale() {
		return genAttuale;
	}

	public Matrice getGenPrecedente() {
		return genPrecedente;
	}
}