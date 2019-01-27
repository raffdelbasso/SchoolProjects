
public class Test {

	public static void main(String[] args) {
		Elementare e = new Elementare("6223", "Gianni Rodari", "Via dei Caduti Partigiani, 25", "Bari (Japigia)", 100, 25, 2, 3);
		System.out.println("Informazioni scuola elementare:\n"+e.toString()+"\n");
		Media m = new Media("14572", "Luigi Lombardi", "Via Lombardia, 31", "Bari", 320, 30, 1, 5);
		System.out.println("Informazioni scuola media:\n"+m.toString()+"\n");
		Liceo l = new Liceo("57432", "Margherita Hack", "Piazza Poerio, 2", "Bari", 700, 50, 2, 20);
		System.out.println("Informazioni liceo:\n"+l.toString()+"\n");
		Professionale p = new Professionale("6313", "Giulio Cesare", "Via Michele Viterbo, 3", "Bari", 300, 40, 1, 20);
		System.out.println("Informazioni professionale:\n"+p.toString()+"\n");
		Tecnico t = new Tecnico("57432", "Guglielmo Marconi", "Piazza Poerio, 2", "Bari", 700, 50, 2, 20);
		System.out.println("Informazioni tecnico:\n"+t.toString()+"\n");
	}

}
