
public class PortaCD {
	private CD cd[];
	private int N;
	
	public PortaCD(int n) {
		N=n;
		cd = new CD[n];
	}
	
	public CD getCD(int pos) {
		return cd[pos];
	}
	
	public int getN() {
		int i;
		int n=0;
		for (i=0; i<N; i++) {
			if (cd[i]!=null) {
				n++;
			}
		}
		return n;
	}
	
	public void setCD(CD cd, int pos) {
		this.cd[pos] = cd;
	}
	
	public void killCD(int pos) {
		cd[pos]=null;
	}
	
	public int cercaCDperTitolo(String titolo, int partenza) {
		int i=partenza;
		int pos=-1;
		boolean trovato=false;
		while (i<N && !trovato) {
			if (cd[i]!=null) {
				if ((cd[i].getTitolo().toLowerCase()).equals(titolo.toLowerCase())) {
					trovato=true;
					pos=i;
				}
			}
			i++;
		}
		return pos;
	}
	
	public int getDim() {
		return N;
	}
	
	public String toString() {
		int i;
		String msg="";
		for (i=0; i<N; i++) {
			if (cd[i]!=null) {
				msg+="Titolo CD in posizione "+i+": "+cd[i].getTitolo()+"\n";
			}
		}
		return msg;
	}
	public int confrontaCollezione(PortaCD p2) {
		int uguali=0;
		int i, j;
		for (i=0; i<N; i++) {
			if (getCD(i)!=null) {
				for (j=0; j<p2.getDim(); j++) {
					if (p2.getCD(j)!=null) {
						if (getCD(i).getTitolo().equals(p2.getCD(j).getTitolo())) {
							uguali++;
						}
					}
				}
			}
		}
		return uguali;
	}
	
}
