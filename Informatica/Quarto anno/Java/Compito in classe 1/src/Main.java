import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		
		// Dichiarazione variabili e vettori
		int nr;
		int nc;
		int i;
		int j;
		int appoggio;
		int[] votiTotali;
		int[] id;
		
		// Acquisizione numero righe
		do {
			System.out.print("Numero righe (tra 2 e 10): ");
			nr = t.nextInt();
		} while (nr<2 || nr>10);
		
		// Inizializzazione vettori:
		// votiTotali: contiene la somma dei voti totali di ciascun candidato (ciascuna riga)
		// id: è l'identificatore di ciascun candidato che va da 1 al numero delle righe
		votiTotali = new int[nr];
		id = new int[nr];
		
		// Acquisizione numero colonne
		do {
			System.out.print("Numero colonne (tra 2 e 15): ");
			nc = t.nextInt();
		} while (nc<2 || nc>15);
		
		// Creazione oggetto Matrice con le dimensioni inserite dall'utente
		Matrice m = new Matrice(nr, nc);
		
		// Generazione degli elementi della matrice da 0 a 500
		m.genera(0, 500);
		
		// Ciclo for che riempie i due vettori votiTotali e id
		for (i=0; i<m.getNr(); i++) {
			votiTotali[i] = m.sommaRiga(i);
			id[i]=i+1;
		}
		
		// Visualizzazione matrice non ordinata con i voti di ciascun candidato
		System.out.println();
		for (i=0; i<m.getNr(); i++) {
			System.out.format("%-2d", (i+1));
			System.out.print(".");
			for (j=0; j<m.getNc(); j++) {
				System.out.format("%5d", m.getElem(i, j));
			}
			System.out.println("     "+votiTotali[i]);
		}
		/*
		 * Bubble sort che ordina in modo decrescente il vettore votiTotali,
		 * e di conseguenza l'id e le righe della matrice.
		 * Pur avendo già fatto questo metodo nella classe VettoreInt dell'anno scorso,
		 * ho deciso di inserirlo nel main perchè in questo esercizio, contemporaneamente
		 * all'ordinamento del vettore votiTotali, si deve aggiornare anche la matrice
		 * e l'id. In alternativa, avrei potuto richiamare quel metodo ma gli avrei
		 * dovuto passare anche la matrice, creando un metodo non generico e utile
		 * solo per questo esercizio.
		 */
		boolean scambio=true;
		int n=m.getNr()-1;
		int c=0;
		while (scambio && n>0) {
			scambio=false;
			for (i=0;i<n;i++){
				if (votiTotali[i]<votiTotali[i+1]) {
					appoggio=votiTotali[i];
					votiTotali[i]=votiTotali[i+1];
					votiTotali[i+1]=appoggio;
					appoggio=id[i];
					id[i]=id[i+1];
					id[i+1]=appoggio;
					m.scambiaRighe(i, i+1);
					scambio=true;
					c=i;
				}
			}
			n=c;
		}
		
		// Visualizzazione matrice ordinata con il numero identificatore di ciascun candidato
		System.out.println("\n");
		for (i=0; i<m.getNr(); i++) {
			System.out.print((i+1)+".");
			for (j=0; j<m.getNc(); j++) {
				System.out.format("%5d", m.getElem(i, j));
			}
			System.out.println("     "+votiTotali[i]+"\tCandidato: "+id[i]);
		}
		t.close();
	}
}