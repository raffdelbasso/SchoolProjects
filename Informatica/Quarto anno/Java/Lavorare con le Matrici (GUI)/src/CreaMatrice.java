import java.util.Scanner;
public class CreaMatrice {
	
	/*Del Basso Raffaele, 4^IA
	 * Questo progetto è destinato ad essere aggiornato in seguito ai nuovi esercizi assegnati.
	 * La classe CreaMatrice, contenente il metodo main, serve esclusivamente per la creazione della matrice.
	 * Il resto delle operazioni sarà effettuato nella classe "Finestra" che visualizza la matrice creata dall'utente
	 * assieme ad una serie di pulsanti funzionali che verranno aggiunti con il tempo.
	 */

	public static void main(String[] args) {
		// Metodo main: creazione della matrice
		Scanner t = new Scanner(System.in);
		int n, m;
		// A causa della dimensione dell'interfaccia,
		// ho fatto in modo che la matrice massima che si può creare abbia dimensione 13x6
		do {
			System.out.print("Numero righe: ");
			n = t.nextInt();
			if (n > 13) {
				System.out.println(n+" righe non sono supportate.");
			}
			if (n==0) {
				System.out.println("Impossibile creare una matrice con 0 righe.");
			}
		} while (n>13 || n==0);
		do {
			System.out.print("Numero colonne: ");
			m = t.nextInt();
			if (m > 6) {
				System.out.println(m+" colonne non sono supportate.");
			}
			if (m==0) {
				System.out.println("Impossibile creare una matrice con 0 colonne.");
			}
		} while (m>6 || m==0);
		System.out.print("Creazione della matrice terminata.");
		t.close();
		// Oggetto ris: la finestra.
		// A questo oggetto passo il vettore appena creato, assieme alle due dimensioni e ad una stringa
		// che userò per dare alla finestra un titolo diverso a seconda dei casi.
		Finestra matrice = new Finestra(n, m);
	}
}