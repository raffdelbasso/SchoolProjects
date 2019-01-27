
public class Dipendente extends Anagrafica{
	private float stipendio;
	private int livello;
	public Dipendente(String nome, String cognome, String email, float stipendio, int livello) {
		super(nome, cognome, email);
		this.stipendio=stipendio;
		this.livello=livello;
	}
	public float getStipendio() {
		return stipendio;
	}
	public void setStipendio(float stipendio) {
		this.stipendio = stipendio;
	}
	public int getLivello() {
		return livello;
	}
	public void setLivello(int livello) {
		this.livello = livello;
	}
	public boolean aumentaLivello() {
		boolean bool=true;
		if (livello==7) {
			bool=false;
		} else {
			livello++;
			stipendio=(float)(stipendio+(stipendio*0.1));
		}
		return bool;
	}
	public String stampaDati() {
		return super.stampaDati()+"Stipendio: "+stipendio+" euro\nLivello attuale: "+livello;
	}
}
