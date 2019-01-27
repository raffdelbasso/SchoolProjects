import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		boolean errore;
		int scelta;
		int i;
		int pos;
		String cognome, nome, matricola, email;
		Classe c = new Classe("4I", 'A', 19);
		do {
			do {
				System.out.print("1. Aggiungi studente\n2. Rimuovi studente\n3. Visualizza tabella\n4. Esci\nInserisci: ");
				scelta = t.nextInt();
				errore=false;
				if (scelta<1 || scelta > 4) {
					System.out.println("Scelta non valida.\n");
					errore=true;
				}
			} while (errore);
			System.out.println();
			switch(scelta) {
			case 1:
				if (c.getnCorrenti()>=c.getNumStudenti()) {
					System.out.println("Capienza massima raggiunta.");
				} else {
					System.out.print("Cognome: ");
					t.nextLine();
					cognome = t.nextLine();
					cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1);
					System.out.print("Nome: ");
					nome = t.nextLine();
					nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);
					System.out.print("Matricola: ");
					matricola = t.next();
					System.out.print("E-mail: ");
					email = t.next();
					c.aggiungiStudente(new Studente(matricola, nome, cognome, email));
					System.out.println("Studente aggiunto all'elenco.");
				}
				break;
			case 2:
				if (c.getnCorrenti()!=0) {
					do {
						System.out.println("1. Rimuovi studente per posizione");
						System.out.println("2. Rimuovi studente per cognome");
						System.out.print("3. Torna indietro\nInserisci: ");
						scelta = t.nextInt();
						errore=false;
						if (scelta<1 || scelta > 3) {
							System.out.println("Scelta non valida.\n");
							errore=true;
						}
					} while (errore);
					System.out.println();
					switch(scelta) {
					case 1:
						do {
							for (i=0; i<c.getnCorrenti(); i++) {
								System.out.println((i+1)+": "+c.getElenco()[i].stampaDati());
							}
							errore=false;
							System.out.print("\nInserisci posizione dell'alunno da rimuovere: ");
							pos=t.nextInt();
							if (pos<0 || pos>c.getnCorrenti()) {
								System.out.println("Posizione non valida.\n");
								errore=true;
							}
						} while (errore);
						if (pos!=0) {
							c.rimuoviStudente(pos-1);
							System.out.println("Alunno rimosso.");
						}
						break;
					case 2:
						for (i=0; i<c.getnCorrenti(); i++) {
							System.out.println((i+1)+": "+c.getElenco()[i].stampaDati());
						}
						System.out.print("\nInserisci cognome dell'alunno da rimuovere: ");
						t.nextLine();
						cognome = t.nextLine();
						if (c.rimuoviStudente(cognome)==1) {
							System.out.println("Il cognome non corrisponde a nessuno studente.");
						} else {
							System.out.println("Studente "+cognome+" eliminato.");
						}
					}
				} else {
					System.out.println("Non c'è alcuno studente in questa classe.");
				}
				break;
			case 3:
				if (c.getnCorrenti()!=0) {
					System.out.println("Finestra aperta.");
					TabellaGUI g = new TabellaGUI(c, c.getnCorrenti());
				} else {
					System.out.println("Non c'è alcuno studente in questa classe.");
				}
				break;
			}
			System.out.println();
		} while (scelta!=4);
		t.close();
	}
}