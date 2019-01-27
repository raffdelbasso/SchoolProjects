public class Matrice {
	private int mat[][];
	private int n;
	private int m;
	

	public Matrice(int dim) {
		n=dim;
		m=n;
		mat=new int[n][m];
	}
	
	public void genera() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				mat[i][j]=(int) (Math.random()*101);
			}
		}
	}
	
	public String visua() {
		String msg="";
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				msg+=String.format("%5d", mat[i][j]);
			}
			msg+="\n";
		}
		return msg;
	}
	public boolean almenoUnoDispari() {
		boolean bool=false;
		int i=0, j;
		while (i<n && !bool) {
			j=0;
			while (j<n && !bool) {
				if (mat[i][j]%2!=0) {
					bool=true;
				}
				j++;
			}
			i++;
		}
		return bool;
	}
	
	public boolean almenoUnoPariRighe() {
		boolean bool=true;
		int i=0, j;
		while (i<n && bool) {
			j=0;
			bool=false;
			while (j<n && !bool) {
				if (mat[i][j]%2==0) {
					bool=true;
				}
				j++;
			}
			i++;
		}
		return bool;
	}
	
	public boolean iNumPari(int i) {
		int j=0, nPari=0;
		while (j<n && nPari!=i) {
			if (mat[i][j]%2==0) {
				nPari++;
			}
			j++;
		}
		if (nPari==i) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean iNumDisp(int i) {
		int j=0, nDisp=0;
		while (j<n) {
			if (mat[i][j]%2!=0) {
				nDisp++;
			}
			j++;
		}
		if (nDisp==i) {
			return true;
		} else {
			return false;
		}
	}
}