
public class Studente extends Anagrafica{
	private String matricola;
	public Studente(String matricola, String nome, String cognome, String email) {
		super(nome, cognome, email);
		this.matricola=matricola;
	}
	
	public String stampaDati() {
		return "Matricola: "+matricola+"\n"+super.stampaDati();
	}
}
