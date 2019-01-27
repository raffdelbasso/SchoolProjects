
public class Main {

	public static void main(String[] args) {
		int n=10;
		int m=10;
		RegoleGioco g = new RegoleGioco(n, m);
		FaseInizialeManual inizio = new FaseInizialeManual(n, m, "Con pulsante", g);
	}
}