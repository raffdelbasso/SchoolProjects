/**
 * Rappresenta gli attributi e le funzionalità del dispositivo che controlla la temperatura all’uscita del tubo e regola il flusso di acqua
calda al miscelatore.
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class Dispositivo {
	/**
	 * Costante usata nel calcolo della variazione del flusso di acqua calda
	 */
	private float K;
	/**
	 * Oggetto di classe Tubo usato per misurare la temperatura
	 */
	private Tubo tub;
	/**
	 * Oggetto di classe Miscelatore usato per impostare in esso il nuovo flusso elaborato dal dispositivo
	 */
	private Miscelatore misc;
	
	/**
	 * Costruttore del dispositivo
	 * 
	 * @param K
	 * @param tub
	 * @param misc
	 */
	public Dispositivo(float K, Tubo tub, Miscelatore misc) {
		this.K = K;
		this.tub = tub;
		this.misc = misc;
	}

	/**
	 * Getter dell'attributo K
	 * 
	 * @return il valore di K
	 */
	public float getK() {
		return K;
	}

	/**
	 * Setter dell'attributo k
	 * 
	 * @param k Nuovo valore di k
	 */
	public void setK(float k) {
		K = k;
	}

	public Tubo getTub() {
		return tub;
	}

	public void setTub(Tubo tub) {
		this.tub = tub;
	}

	public Miscelatore getMisc() {
		return misc;
	}

	public void setMisc(Miscelatore misc) {
		this.misc = misc;
	}
	
	/**
	 * Calcola il valore di variazione del flusso di acqua calda e imposta il flusso del miscelatore
	 */
	public void regolaFC() {
		float DFC = -K*(tub.misuraT() - tub.getTZERO());
		misc.setFC(misc.getFC()+DFC);
	}
}