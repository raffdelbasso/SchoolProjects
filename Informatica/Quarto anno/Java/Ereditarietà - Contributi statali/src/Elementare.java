
public class Elementare extends Scuola{
	private float contributoStud;
	private float contributoSede;
	public Elementare(String codice, String denominazione, String indirizzo, String citta, int nStudenti, int nClassi, int nSedi, int nLabs) {
		super(codice, denominazione, indirizzo, citta, nStudenti, nClassi, nSedi, nLabs);
		contributoStud = 125;
		contributoSede = 9000;
	}
	public float getContributoStud() {
		return contributoStud;
	}
	public void setContributoStud(float contributoStud) {
		this.contributoStud = contributoStud;
	}
	public float getContributoSede() {
		return contributoSede;
	}
	public void setContributoSede(float contributoSede) {
		this.contributoSede = contributoSede;
	}
	
	public float calcolaContributo() {
		float risultato;
		risultato = (contributoStud*super.getnStudenti())+(contributoSede*super.getnSedi());
		return risultato;
	}
	
	public String toString() {
		String msg = "Tipo scuola: Elementare";
		msg += "\n"+super.toString();
		msg += "Contributo statale: €"+calcolaContributo();
		return msg;
	}
}
