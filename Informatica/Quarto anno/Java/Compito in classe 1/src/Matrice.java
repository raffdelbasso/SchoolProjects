
public class Matrice {

	private int nr, nc;
	private int mat[][];
	
	// Costruttore per matrici rettangolari
	public Matrice(int nr, int nc){
		this.nr=nr;
		this.nc=nc;
		mat = new int[nr][nc];
	}
	
	// Costruttore per matrici quadrate
	public Matrice(int dim){
		nr=dim;
		nc=nr;
		mat = new int[nr][nc];
	}
	
	// Metodo che riempie la matrice con valori compresi tra sx e dx, passati in input
	public void genera(int sx, int dx){
		int r,c;
		for(r=0; r<nr; r++){
			for(c=0; c<nc; c++){
				mat[r][c] = (int)(Math.random()*(dx-sx+1)+sx);
			}
		}
	}

	// Metodo che calcola la somma di una riga r passata in input
	public int sommaRiga (int r) {
		int i, cont=0;
		for(i=0;i<nc;i++) {
			cont = cont + mat[r][i];
		}
		return cont;
	}
	
	// Metodo che scambia le righe appartenenti ai due indici passati in input
	public void scambiaRighe(int r1, int r2) {
		int i;
		int appoggio;
		for (i=0; i<nc; i++) {
			appoggio=mat[r1][i];
			mat[r1][i]=mat[r2][i];
			mat[r2][i]=appoggio;
		}
	}
	
	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public int getNc() {
		return nc;
	}

	public void setNc(int nc) {
		this.nc = nc;
	}

	public int getElem(int r, int c) {
		return mat[r][c];
	}

	public void setElem(int r, int c, int value) {
		mat[r][c] = value;
	}
	
}
