
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
	 *Questo metodo controlla quante celle sono vive attorno a quella data.
	 * La cella data si identifica con i parametri di riga o colonna passati
	 * al metodo
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
	
	// Questo metodo cambia lo stato di una cellula data
	// andando ad analizzarla con il metodo precendentemente visto.
	// Questo metodo restituisce 1 se la cellula analizzata rester� viva
	// o 0 se morir�
	public int nuovoStatoCellula(int r, int c) {
		int cont;
		cont = contaIntorno(r,c);
		if(genPrecedente.getElem(r, c)==0) {
			if(cont==3) {
				return 1;
			}else {
				return 0;
			}
		} else {
			if(cont<2 || cont>3) {
				return 0;
			}else {
				return 1;
			}
		}
	}
	
	// Questo metodo genera la nuova generazione in base al risultato
	// del metodo nuovoStatoCellula, che a sua volta richiama il metodo
	// contaIntorno.
	public void nuovaGenerazione() {
		int r, c;
		for(r=0; r<nr; r++) {
			for(c=0; c<nc; c++) {
				genAttuale.setElem(r, c, nuovoStatoCellula(r,c));
			}
		}
	}
	
	// Questo metodo copia la generazione attuale mettendola nella
	// precedente. Il procedimento � molto simile al metodo situato in alto,
	// solo che il setElem � fatto su genPrecedente e ciascun elemento
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