
public class Villa extends Abitazione {
	private int numPiani;
	private boolean piscina;
	public Villa(int numStanze, String indirizzo, String citta, int numPiani, boolean piscina) {
		super(numStanze, indirizzo, citta);
		this.numPiani=numPiani;
		this.piscina=piscina;
	}
	public int getNumPiani() {
		return numPiani;
	}
	public void setNumPiani(int numPiani) {
		this.numPiani = numPiani;
	}
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public String toString() {
		String s;
		s=super.toString();
		s+="Numero di piani: "+numPiani;
		s+="\nPiscina presente: ";
		if (piscina) {
			s+="Sì\n";
		} else {
			s+="No\n";
		}
		return s;
	}
}
