
public class Media extends Scuola {
	private float contributoStud;
	private float contributoLabs;
	private float contributoSede;
	public Media(String codice, String denominazione, String indirizzo, String citta, int nStudenti, int nClassi,
			int nSedi, int nLabs) {
		super(codice, denominazione, indirizzo, citta, nStudenti, nClassi, nSedi, nLabs);
		contributoStud = 150;
		contributoLabs = 1100;
		contributoSede = 9000;
	}
	public float getContributoStud() {
		return contributoStud;
	}
	public void setContributoStud(float contributoStud) {
		this.contributoStud = contributoStud;
	}
	public float getContributoLabs() {
		return contributoLabs;
	}
	public void setContributoLabs(float contributoLabs) {
		this.contributoLabs = contributoLabs;
	}
	public float getContributoSede() {
		return contributoSede;
	}
	public void setContributoSede(float contributoSede) {
		this.contributoSede = contributoSede;
	}
	
	public float calcolaContributo() {
		float risultato;
		risultato = contributoStud*(super.getnStudenti());
		risultato += contributoLabs*(super.getnLabs());
		risultato += contributoSede*(super.getnSedi());
		return risultato;
	}
	
	public String toString() {
		String msg = "Tipo scuola: Media";
		msg += "\n"+super.toString();
		msg += "Contributo statale: €"+calcolaContributo();
		return msg;
	}
}
