import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		String nome, cognome, email=null;
		char scelta;
		int scelta2, livello;
		float stipendio;
		boolean errore;
		System.out.print("Nome: ");
		nome = t.next();
		System.out.print("Cognome: ");
		cognome = t.next();
		do {
			errore=false;
			System.out.print("Registrare email? (y/n) ");
			scelta = t.next().charAt(0);
			switch(scelta) {
			case 'y':
				do {
					errore=false;
					System.out.print("Email: ");
					email = t.next();
					if (!email.contains("@")) {
						System.out.println("Email non valida. Riprova.");
						errore=true;
					}
				} while (errore);
				break;
			case 'n':
				email=null;
				break;
			default:
				errore=true;
			}
			if (errore) {
				System.out.println("Scelta non valida.");
			}
		} while (errore);
		System.out.print("Stipendio: ");
		stipendio = t.nextFloat();
		do {
			errore=false;
			System.out.print("Livello di partenza: ");
			livello = t.nextInt();
			if (livello<1 || livello >7) {
				errore=true;
				System.out.println("Il livello deve essere compreso tra 1 e 7.");
			}
		} while (errore);
		Dipendente d = new Dipendente(nome, cognome, email, stipendio, livello);
		boolean uscita=false;
		do {
			do {
				errore=false;
				System.out.print("\n1. Aumenta livello\n2. Stampa dati\n3. Esci\nInserisci: ");
				scelta2 = t.nextInt();
				if (scelta2<1 || scelta2>3) {
					errore=true;
					System.out.println("Scelta non valida.");
				}
			} while (errore);
			switch (scelta2) {
			case 1:
				if (!d.aumentaLivello()) {
					System.out.println("\nIl dipendente ha già raggiunto il livello massimo.");
				} else {
					System.out.println("\nLivello aumentato.");
				}
				break;
			case 2:
				System.out.println("\n"+d.stampaDati());
				break;
			case 3:
				uscita=true;
			}
		} while (!uscita);
		t.close();
	}
}
