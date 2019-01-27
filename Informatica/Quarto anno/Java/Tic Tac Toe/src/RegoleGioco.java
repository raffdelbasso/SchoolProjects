
public class RegoleGioco {
	private Matrice mat;
	private int gioc=1;
	private int turno=1;
	private boolean vitt[] = new boolean[2];
	public RegoleGioco() {
		gioc=1;
		turno=1;
		mat=new Matrice(3);
		vitt[0]=false;
		vitt[1]=false;
	}
	
	public Matrice getMat() {
		return mat;
	}

	public void setMat(Matrice mat) {
		this.mat = mat;
	}

	public int getGioc() {
		return gioc;
	}

	public void setGioc(int gioc) {
		this.gioc = gioc;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public boolean[] getVitt() {
		return vitt;
	}

	public void setVitt(boolean[] vitt) {
		this.vitt = vitt;
	}
	
	public void nuovoTurno(int i, int j) {
		mat.setElem(i, j, gioc);
		gioc=turno%2+1;
		turno++;
	}

	public int vincita() {
		int i, vincitore=-1;
		if (turno>5) {
			vitt[0]=controllaVincita(1);
			vitt[1]=controllaVincita(2);
			for (i=0; i<2; i++) {
				if (vitt[i]) {
					vincitore=i;
				}
			}
			if (turno==10&&(vitt[0]==false && vitt[1]==false) ) {
				vincitore=10;
			}
		}
		return vincitore;
	}
	
	public boolean controllaVincita(int gioc) {
		if (controlloRig(gioc)||controlloCol(gioc)||controlloDiag1(gioc)||controlloDiag2(gioc)) {
			return true;
		} else {
			return false;
		}
	}
	private boolean controlloRig(int gioc) {
		int i, j, somma;
		boolean vittoria=false;
		for (i=0; i<3; i++) {
			somma=0;
			for (j=0; j<3; j++) {
				somma+=mat.getElem(i, j);
			}
			if (somma==(gioc*3)) {
				vittoria=true;
			}
		}
		return vittoria;
	}
	private boolean controlloCol(int gioc) {
		int i, j, somma;
		boolean vittoria=false;
		for (i=0; i<3; i++) {
			somma=0;
			for (j=0; j<3; j++) {
				somma+=mat.getElem(j, i);
			}
			if (somma==(gioc*3)) {
				vittoria=true;
			}
		}
		return vittoria;
	}
	
	private boolean controlloDiag1(int gioc) {
		int i, somma=0;
		boolean vittoria=false;
		for (i=0; i<3; i++) {
			somma+=mat.getElem(i, i);
		}
		if (somma==gioc*3) {
			vittoria=true;
		}
		return vittoria;
	}
	
	private boolean controlloDiag2(int gioc) {
		int i, somma=0;
		boolean vittoria=false;
		for (i=0; i<3; i++) {
			somma+=mat.getElem(i, 3-i-1);
		}
		if (somma==gioc*3) {
			vittoria=true;
		}
		return vittoria;
	}
}