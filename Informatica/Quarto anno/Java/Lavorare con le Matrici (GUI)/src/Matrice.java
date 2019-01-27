
public class Matrice {
	// Attributi
	private int mat[][];
	private int n;
	private int m;
	private int iMax;
	private int jMax;
	private int iMin;
	private int jMin;
	// Costruttore per Matrice rettangolare
	public Matrice(int n, int m) {
		this.n=n;
		this.m=m;
		mat=new int[n][m];
	}       
	
	// Costruttore per Matrice quadrata
	public Matrice(int dim) {
		n=dim;
		m=n;
		mat=new int[n][m];
	}
	
	public int getN() {
		return n;
	}

	public int getiMax() {
		return iMax;
	}

	public int getjMax() {
		return jMax;
	}
	
	public int getiMin() {
		return iMin;
	}

	public int getjMin() {
		return jMin;
	}
	
	public int getM() {
		return m;
	}
	
	public void genera(int min, int max) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				mat[i][j]=(int)(Math.random()*(max-min+1)+min);
			}
		} 
	}
	public int getElem(int i, int j) {
		return mat[i][j];
	}
	
	public String toString() {
		String msg="";
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				msg=msg+String.format("%5d", mat[i][j]);
			}
			msg+="\n";
		}
		return msg;
	}
	public String sommaRighe() {
		int sommaRiga, i, j;
		// La stringa msg si riempirà all'interno del doppio for.
		String msg="";
		for (i=0; i<n; i++) {
			sommaRiga=0;
			for (j=0; j<m; j++) {
				sommaRiga+=mat[i][j];
			}
				// Dopo aver calcolato la somma dei numeri presenti su una riga,
				// la stringa memorizzerà e concatenerà il seguente messaggio:
				msg+="La somma dei numeri presenti nella riga "+i+" è: "+sommaRiga+"\n";
		}
		// Al termine del doppio for, la stringa avrà le somme di tutte le righe
		// presenti nella matrice.
		return msg;
	}
	public String matriceTrasposta() {
		String msg="";
		int i, j;
		int mat2[][] = new int[m][n];
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				mat2[j][i]=mat[i][j];
			}
		}
		for (i=0; i<m; i++) {
			for (j=0; j<n; j++) {
				msg+=String.format("%5d", mat2[i][j]);
			}
			msg+="\n";
		}
		return msg;
	}
	public String somme() {
		int sd=0;
		int ssts=0;
		int ssti=0;
		int i, j;
		String msg;
		for (i=0; i<n; i++) {
			for (j=0; j<n; j++) {
				if (i==j) {
					sd+=mat[i][j];
				} else {
					if (i<j) {
						ssts+=mat[i][j];
					} else {
						ssti+=mat[i][j];
					}
				}
			}
		}
		msg="La somma dei numeri presenti sulla diagonale principale è: "+sd+"\n";
		msg+="La somma dei numeri presenti sulla sottomatrice triangolare superiore è: "+ssts+"\n";
		msg+="La somma dei numeri presenti sulla sottomatrice triangolare inferiore è: "+ssti;
		return msg;
	}
	public int somma() {
		int s=0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				s+=mat[i][j];
			}
		}
		return s;
	}
	public float medColonna(int c) {
		float media=0;
		for (int i=0; i<n; i++) {
			media+=mat[i][c];
		}
		media/=n;
		return media;
	}
	public float medRiga(int r) {
		float media=0;
		for (int i=0; i<m; i++) {
			media+=mat[r][i];
		}
		media/=m;
		return media;
	}
	public boolean isSimmetrica() {
	   	int i=0;
	   	int j;
	   	boolean simm=true;
	   	while (i<n && simm) {
	   		j=0;
	   		while (j<m && simm) {
	   			if (mat[i][j]!=mat[j][i]) {
	    			simm=false;
	    		}
	   			j++;
	   		}
	   		i++;
	   	}
	    return simm;
	}
	public int max() {
		int max=mat[0][0];
		iMax=0;
		jMax=0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (mat[i][j]>max) {
					iMax=i;
					jMax=j;
					max = mat[i][j];
				}
			}
		}
		return max;
	}
	
	public int min() {
		int min=mat[0][0];
		iMin=0;
		jMin=0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (mat[i][j]<min) {
					iMin=i;
					jMin=j;
					min = mat[i][j];
				}
			}
		}
		return min;
	}
	
	public float media() {
		return somma()/(n*m);
	}
}