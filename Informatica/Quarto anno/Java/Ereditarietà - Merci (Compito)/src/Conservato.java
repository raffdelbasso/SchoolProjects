
public class Conservato extends Alimentare {
	private String marca;

	public Conservato(String codice, float prezzoUnitario, String descrizione, float peso, float calorieUnitarie,
			String marca) {
		super(codice, prezzoUnitario, descrizione, peso, calorieUnitarie);
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/* Il metodo calcolaImporto non è presente in questa classe perchè
	 * non contiene calcoli differenti dal metodo calcolaImporto
	 * contenuto nella classe Alimentare.
	 * Quando l'utente nel main creerà l'oggetto di classe Conservato, quindi
	 * facendo c.calcolaImporto(quantita), verrà richiamato il metodo
	 * calcolaImporto presente nella classe Alimentare.
	 */
}
