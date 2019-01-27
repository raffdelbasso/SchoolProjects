
public class Mensola {
	Libro volumi[];
	private static final int MAX_NUM_VOLUMI=12;
	public Mensola() {
		volumi = new Libro[getMaxNumVolumi()];
	}
	public Mensola(Mensola mensola) {
		int i;
		volumi = new Libro[getMaxNumVolumi()];
		for (i=0; i<getMaxNumVolumi(); i++) {
			if (volumi[i]!=null) {
				volumi[i] = mensola.getVolume(i);
			}
		}
	}
	
	public Libro getVolume(int pos) {
		return volumi[pos];
	}
	
	public int setVolume(Libro libro, int pos) {
		if (pos<0 || pos > getMaxNumVolumi()) {
			return -1;
		}
		if (getVolume(pos)!=null) {
			return -2;
		} else {
			volumi[pos] = libro;
			return -3;
		}
	}
	public void setVolume2(Libro libro, int pos) {
		volumi[pos] = libro;
	}
	public void rimuoviVolume(int pos) {
		volumi[pos]=null;
	}
	public int getMaxNumVolumi() {
		return MAX_NUM_VOLUMI;
	}
}