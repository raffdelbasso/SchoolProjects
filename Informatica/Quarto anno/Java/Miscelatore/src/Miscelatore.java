/**
 * Rappresenta gli attributi e le funzionalità del miscelatore che riceve sia acqua calda che acqua fredda e le miscela fornendole
al tubo.
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class Miscelatore {
	/**
	 * Temperatura acqua calda (in gradi centigradi)
	 */
	private float TC;
	/**
	 * Temperatura acqua fredda (in gradi centigradi)
	 */
	private float TF;
	/**
	 * Flusso acqua calda (in litri al secondo)
	 */
	private float FC;
	/**
	 * Flusso acqua fredda (in litri al secondo)
	 */
	private float FF;
	/**
	 * Oggetto di classe tubo nel quale inserire l'acqua miscelata
	 */
	private Tubo tub;
	
	/**
	 * Costruttore del Miscelatore
	 *  
	 * @param FZERO Flusso dell'acqua calda iniziale
	 * @param tub Oggetto di classe tubo
	 */
	public Miscelatore(float FZERO, Tubo tub) {
		FC = FZERO;
		this.tub=tub;
	}

	/**
	 * Getter dell'attributo TC
	 * 
	 * @return il valore di TC
	 */
	public float getTC() {
		return TC;
	}

	/**
	 * Setter dell'attributo TC
	 * 
	 * @param tC Nuovo valore di TC
	 */
	public void setTC(float tC) {
		TC = tC;
	}

	public float getTF() {
		return TF;
	}

	public void setTF(float tF) {
		TF = tF;
	}

	public float getFC() {
		return FC;
	}

	public void setFC(float fC) {
		FC = fC;
	}

	public float getFF() {
		return FF;
	}

	public void setFF(float fF) {
		FF = fF;
	}

	public Tubo getTub() {
		return tub;
	}

	public void setTub(Tubo tub) {
		this.tub = tub;
	}
	
	/**
	 * Calcola la temperatura dell'acqua miscelata secondo la formula
	 * TM = ((TC*FC) + (TF*FF)) / (FC+FF)
	 * 
	 * @return il valore della temperatura dell'acqua miscelata
	 */
	public float TM() {
		return (float) ((TC*FC) + (TF*FF)) / (FC+FF);
	}
	
	/**
	 * Aggiunge l'acqua miscelata al tubo
	 */
	public void miscela() {
		tub.add(TM());
	}
	
}