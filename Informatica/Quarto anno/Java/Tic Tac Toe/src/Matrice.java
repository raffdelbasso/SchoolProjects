
public class Matrice {
	// Attributi
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
	
	public void genera(int sx, int dx){
		int r,c;
		for(r=0; r<nr; r++){
			for(c=0; c<nc; c++){
				mat[r][c] = (int)(Math.random()*(dx-sx+1)+sx);
			}
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
