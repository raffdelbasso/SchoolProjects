import java.io.File;

public class Main {

	public static void main(String[] args) {
		File m;
		int n=0;
		String per="src/";
		do {
			per="src/";
			per+=String.valueOf(n+1)+".wav";
			m=new File(per);
			n++;
		} while (m.exists());
		System.out.print("Canzoni trovate: "+(n-1));
		GUI finestra = new GUI((n-1));
	}

}
