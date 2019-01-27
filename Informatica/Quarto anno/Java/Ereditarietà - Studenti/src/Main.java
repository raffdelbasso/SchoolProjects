
public class Main {

	public static void main(String[] args) {
		Studente s = new Studente("121562", "Raffaele", "Del Basso", "raffydelbasso@gmail.com");
		System.out.println(s.stampaDati());
		Studente s2 = new Studente("0415721", "Mario", "Rossi", null);
		System.out.print(s2.stampaDati());
	}

}
