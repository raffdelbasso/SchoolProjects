
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
	
	/* Il metodo che segue verr� richiamato nel main solo se
	 * il tipo dell'Alimentare Fresco creato � uguale a "confezionato"
	 * (come chiede la traccia). In tal caso, verr� chiesto anche il
	 * costo del sacchetto.
	 * Altrimenti, verr� richiamato il metodo
	 * calcolaImporto passandogli solo la quantit�. Esso equivale al richiamo
	 * del metodo calcolaImporto presente nella classe Alimentare.
	 */
	public float calcolaImporto(int quantita, float costoSacchetto) {
		return super.calcolaImporto(quantita)+costoSacchetto;
	}
	
}
