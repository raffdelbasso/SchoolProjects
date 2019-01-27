import java.util.Vector;
/**
 * Rappresenta gli attributi e le funzionalità del tubo nel quale scorre l'acqua
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class Tubo {
	/**
	 * Vettore che contiene le temperature dell'acqua
	 */
	private Vector temp = new Vector();
	/**
	 * Temperatura all’istante iniziale in tutto il tubo
	 */
	private final float TZERO;
	/**
	 * Costruttore del tubo
	 * 
	 * @param RIT Tempo (in secondi) che l’acqua impiega a percorrere il tubo
	 * @param TZERO Temperatura all’istante iniziale in tutto il tubo
	 */
	public Tubo(int RIT, float TZERO) {
		int i;
		for(i=0; i<RIT; i++) {
			temp.addElement(TZERO);
		}
		this.TZERO = TZERO;
	}
	
	public Vector getTemp() {
		return temp;
	}

	public void setTemp(Vector temp) {
		this.temp = temp;
	}

	public float getTZERO() {
		return TZERO;
	}

	/**
	 * Metodo che misura la temperatura dell’acqua all’uscita del tubo
	 * @return la temperatura dell'acqua all'uscita del tubo
	 */
	public float misuraT() {
		float f = (float) temp.elementAt(0);
		return f;
	}
	
	/**
	 * aggiungere una nuova temperatura dell'acqua miscelata all’inizio del vettore
	 * 
	 * @param TM Temperatura dell'acqua miscelata
	 */
	public void add(float TM) {
		temp.removeElementAt(0);
		temp.addElement(TM);
	}
}