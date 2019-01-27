
public class Fresco extends Alimentare {
	private String tipo;

	public Fresco(String codice, float prezzoUnitario, String descrizione, float peso, float calorieUnitarie, String tipo) {
		super(codice, prezzoUnitario, descrizione, peso, calorieUnitarie);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/* Il metodo che segue verrà richiamato nel main solo se
	 * il tipo dell'Alimentare Fresco creato è uguale a "confezionato"
	 * (come chiede la traccia). In tal caso, verrà chiesto anche il
	 * costo del sacchetto.
	 * Altrimenti, verrà richiamato il metodo
	 * calcolaImporto passandogli solo la quantità. Esso equivale al richiamo
	 * del metodo calcolaImporto presente nella classe Alimentare.
	 */
	public float calcolaImporto(int quantita, float costoSacchetto) {
		return super.calcolaImporto(quantita)+costoSacchetto;
	}
	
}
