
public class Tecnico extends Superiore {
	private float contributoClassi;
	private float contributoLabs;
	public Tecnico(String codice, String denominazione, String indirizzo, String citta, int nStudenti, int nClassi,
			int nSedi, int nLabs) {
		super(codice, denominazione, indirizzo, citta, nStudenti, nClassi, nSedi, nLabs);
		contributoClassi = 3500;
		contributoLabs = 6000;
	}
	public float getContributoClassi() {
		return contributoClassi;
	}
	public void setContributoClassi(float contributoClassi) {
		this.contributoClassi = contributoClassi;
	}
	public float getContributoLabs() {
		return contributoLabs;
	}
	public void setContributoLabs(float contributoLabs) {
		this.contributoLabs = contributoLabs;
	}
	
	public float calcolaContributo() {
		float risultato;
		risultato = contributoClassi*(super.getnClassi());
		risultato += contributoLabs*(super.getnLabs());
		return risultato;
	}
	
	public String toString() {
		String msg = "Tipo scuola: Superiore - Tecnico";
		msg += "\n"+super.toString();
		msg += "Contributo statale: €"+calcolaContributo();
		return msg;
	}
}
