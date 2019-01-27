import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		Porto p = new Porto();
		int scelta;
		String nome;
		char tipologia;
		float stazza;
		int lunghezza;
		String nazione;
		boolean errore;
		int pos;
		int nGiorni;
		float risultato;
		Barca b;
		do {
			do {
				errore=false;
				System.out.print("1. Assegna barca\n2. Noleggia barca\n3. Visualizza informazioni\nInserisci: ");
				scelta = t.nextInt();
				if (scelta<1 || scelta > 3) {
					System.out.println("Scelta non valida.");
					errore=true;
				}
			} while (errore);
			switch(scelta) {
			case 1:
				do {
					errore=false;
					System.out.print("Nome: ");
					nome = t.next();
					System.out.print("Tipologia (V: Barca a vela, M: Barca a motore): ");
					tipologia = t.next().charAt(0);
					System.out.print("Stazza: ");
					stazza = t.nextFloat();
					System.out.print("Lunghezza (in metri): ");
					lunghezza = t.nextInt();
					System.out.print("Nazione: ");
					nazione = t.next();
					System.out.print("Posizione: ");
					pos = t.nextInt();
					if (!p.isValida(new Barca(nome, tipologia, stazza, lunghezza, nazione), pos)) {
						System.out.println("Impossibile inserire la barca in questa posizione.\n");
						errore=true;
					} else {
						System.out.println("Barca inserita correttamente.");
					}
				} while (errore);
				break;
			case 2:
				System.out.print("Numero giorni: ");
				nGiorni = t.nextInt();
				System.out.print("Inserisci posizione: ");
				pos = t.nextInt();
				risultato = p.costoSosta(nGiorni, pos);
				if (risultato==-1) {
					System.out.println("Nessuna barca presente in quella posizione.");
				} else {
					System.out.println("Barca noleggiata. Costo: €"+risultato);
				}
				break;
			case 3:
				System.out.print("Inserisci posizione: ");
				pos = t.nextInt();
				b = p.info(pos);
				if (b==null) {
					System.out.println("Nessuna barca presente in quella posizione.");
				} else {
					System.out.println("Informazioni barca:\n"+b.toString());
				}
			}
		} while (true);
	}
}
