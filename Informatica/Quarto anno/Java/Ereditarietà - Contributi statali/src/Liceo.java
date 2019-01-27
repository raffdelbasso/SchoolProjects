
public class Liceo extends Superiore {
	private float contributoStud;
	private float contributoLabs;
	public Liceo(String codice, String denominazione, String indirizzo, String citta, int nStudenti, int nClassi,
			int nSedi, int nLabs) {
		super(codice, denominazione, indirizzo, citta, nStudenti, nClassi, nSedi, nLabs);
		contributoStud = 150;
		contributoLabs = 1100;
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
	public float calcolaContributo() {
		float risultato;
		risultato = contributoStud*(super.getnStudenti());
		risultato += contributoLabs*(super.getnLabs());
		return risultato;
	}
	
	public String toString() {
		String msg = "Tipo scuola: Superiore - Liceo";
		msg += "\n"+super.toString();
		msg += "Contributo statale: €"+calcolaContributo();
		return msg;
	}
}
