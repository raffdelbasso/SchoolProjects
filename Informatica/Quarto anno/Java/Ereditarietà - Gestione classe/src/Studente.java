
public class Studente extends Anagrafica{
	private String matricola;
	public Studente(String matricola, String nome, String cognome, String email) {
		super(nome, cognome, email);
		this.matricola=matricola;
	}
	
	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	// Sovrascrittura del metodo Anagrafica.stampaDati
	@Override
	public String stampaDati() {
		return super.stampaDati()+" || "+matricola;
	}
}
