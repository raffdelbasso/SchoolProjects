import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		int scelta, pos, nStanza;
		boolean errore;
		String nominativo;
		Portachiavi p = new Portachiavi();
		do {
			// Visualizzazione del menu con le varie opzioni.
			System.out.println("1. Deposita chiave");
			System.out.println("2. Preleva chiave");
			System.out.println("3. Visualizza stato del portachiavi");
			System.out.println("4. Esci");
			// Verifica del corretto inserimento dell'input
			do {
				System.out.print("Inserisci: ");
				scelta = t.nextInt();
				if (scelta<1 || scelta>4) {
					System.out.println("Scelta non valida.");
				}
			} while (scelta<1 || scelta>4);
			switch (scelta) {
			case 1:
				// 1: Deposita chiave
				// Calcolo della prima posizione disponibile
				pos=p.calcolaPos();
				// Se non c'è nessuna posizione disponibile:
				if (pos==-1) {
					System.out.println("Nessuna posizione libera.");
				} else {
					// Altrimenti, creazione chiave
					System.out.print("Numero stanza: ");
					nStanza = t.nextInt();
					System.out.print("Nominativo: ");
					nominativo = t.next();
					// Richiamo del metodo depositaChiave creando una nuova chiave
					// e passando la posizione calcolata in precedenza (non potrà valere -1)
					p.depositaChiave(new Chiave(nStanza, nominativo), pos);
					System.out.println("Chiave depositata in posizione "+pos+". A presto!");
				}
				break;
			case 2:
				// 2: Preleva chiave
				do {
					// Altro menu
					System.out.println("\n1. Cerca chiave per numero stanza");
					System.out.println("2. Cerca chiave per nominativo");
					System.out.println("3. Indietro");
					// Verifica del corretto inserimento dell'input
					do {
						System.out.print("Inserisci: ");
						scelta = t.nextInt();
						if (scelta<1 || scelta>3) {
							System.out.println("Scelta non valida.");
						}
					} while (scelta<1 || scelta>3);
					errore = false;
					switch(scelta) {
					case 1:
						// 1: Cerca chiave per numero stanza
						System.out.print("Numero stanza: ");
						nStanza = t.nextInt();
						// Se il metodo restituisce 0 = Chiave non trovata
						if (p.prelevaChiave(nStanza)==0) {
							// Chiave non trovata: verrà rivisualizzato
							// il menu di "Preleva chiave"
							errore=true;
							System.out.println("Chiave non trovata.");
						} else {
							// Altrimenti, chiave trovata.
							// Il metodo depositaChiave rimuove l'oggetto
							System.out.println("Chiave prelevata. Buona permanenza!");
						}
						break;
					case 2:
						// 2: Cerca chiave per nominativo
						// Il ragionamento è il medesimo effettuato sù
						System.out.print("Nominativo: ");
						nominativo = t.next();
						if (p.prelevaChiave(nominativo)==0) {
							errore=true;
							System.out.println("Chiave non trovata.");
						} else {
							System.out.println("Chiave prelevata. Buona permanenza!");
						}
						break;
					}
					// L'utente resterà nella funzionalità "Preleva chiave"
					// finchè non trova la chiave oppure digita 3 (Indietro)
				} while (errore && scelta!=3);
				break;
			case 3:
				// 3: Visualizza stato portachiavi
				System.out.print(p.visualizzaChiavi());
			}
			System.out.println();
			// Il programma rivisualizza il menu finchè l'utente non preme 4
		} while(scelta!=4);
		t.close();
	}

}
