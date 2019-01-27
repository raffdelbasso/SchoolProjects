
public class Libro {
	private String titolo;
	private String autore;
	private int nPagine;
	private static double costoPagina=0.05;
	final double COSTO_FISSO=5.5;
	public Libro(String titolo, String autore, int nPagine) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.nPagine = nPagine;
	}
	public Libro(Libro libro) {
		titolo = libro.getTitolo();
		autore = libro.getAutore();
		nPagine = libro.getnPagine();
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public int getnPagine() {
		return nPagine;
	}
	public void setnPagine(int nPagine) {
		this.nPagine = nPagine;
	}
	public double prezzo() {
		return COSTO_FISSO+(nPagine*costoPagina);
	}
	public String toString() {
		String msg;
		msg = "Titolo: "+titolo+"\n";
		msg += "Autore: "+autore+"\n";
		msg += "Numero pagine: "+nPagine+"\n";
		msg += "Valore: "+prezzo()+" euro";
		return msg;
	}
}
