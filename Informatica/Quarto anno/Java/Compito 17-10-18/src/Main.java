
public class Main {

	public static void main(String[] args) {
		Matrice mat = new Matrice(10);
		mat.genera();
		System.out.println(mat.visua());
		if (mat.almenoUnoDispari()) {
			System.out.println("C'è almeno un elemento dispari nella matrice.");
		} else {
			System.out.println("Non c'è nessun elemento dispari nella matrice.");
		}
		if (mat.almenoUnoPariRighe()) {
			System.out.println("C'è almeno un elemento pari in ogni riga della matrice.");
		} else {
			System.out.println("C'è una riga in cui non c'è nessun elemento pari.");
		}
		if (mat.iNumPari(3)) {
			System.out.println("Ci sono almeno 3 elementi pari nella riga n.3.");
		} else {
			System.out.println("Ci sono meno di 3 elementi pari nella riga n.3.");
		}
		if (mat.iNumDisp(2)) {
			System.out.println("Ci sono esattamente 2 elementi dispari sulla riga 2.");
		} else {
			System.out.println("Non ci sono esattamente 2 elementi dispari sulla riga 2.");
		}
	}

}
