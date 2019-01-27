import java.util.Scanner;
public class Allineamento {

	public static void main(String[] args) {
		Scanner t = new Scanner(System.in);
		int i, j, k, n, m, V[][], CifreMax[], sp;
		System.out.print("Numero righe: ");
		n = t.nextInt();
		System.out.print("Numero colonne: ");
		m = t.nextInt();
		V = new int[n][m];
		CifreMax = new int[m];
		// Doppio ciclo di riempimento della matrice
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				V[i][j] = (int)(Math.random()*10000);
			}
		}
		// Doppio ciclo per creare il numero di cifre massime su ogni colonna
		// È tutta una questione di spazi
		for (j=0; j<m; j++) {
			CifreMax[j]=0;
			for (i=0; i<n; i++) {
				if (String.valueOf(V[i][j]).length()>CifreMax[j]) {
					CifreMax[j]=String.valueOf(V[i][j]).length();
				}
			}
		}
		// Doppio ciclo per la visualizzazione della matrice allineata
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				System.out.print(V[i][j]);
				// Se ci troviamo sull'ultima colonna,
				// gli spazi non serve che vadano aggiunti
				if (j!=m-1) {
					// Gli spazi da inserire tra una cifra e l'altra sono uguali alla differenza
					// tra il numero di cifre del numero più grande e il numero di cifre del numero corrente.
					sp=(CifreMax[j]-(String.valueOf(V[i][j])).length())+2;
					for (k=0; k<sp; k++) {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
		}
		t.close();
	}
}