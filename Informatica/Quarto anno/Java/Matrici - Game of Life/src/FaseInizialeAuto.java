import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class FaseInizialeAuto extends JFrame{
	public int n;
	public int m;
	public int p;
	public int i;
	public int j;
	public int nDaRiempire;
	private Object s;
	private int pos;
	private RegoleGioco g;
	public FaseInizialeAuto (int n, int m, int p, Object s, RegoleGioco g) {
		this.n=n;
		this.m=m;
		this.p=p;
		this.s=s;
		this.g=g;
		generaMatrice();
	}
	
	private void generaMatrice() {
		nDaRiempire=((n*m)*p)/100;
		for (pos=0; pos<nDaRiempire; pos++) {
			i=(int)(Math.random()*(n));
			j=(int)(Math.random()*(m));
			if (g.getGenAttuale().getElem(i, j)==1) {
				pos--;
			}
			g.getGenAttuale().setElem(i, j, 1);
		}
		Gioco gui = new Gioco(g, n, m, s);
		// Alla schermata di informazioni, passo anche l'interfaccia
		// perchè lì verrà richiamato il metodo per cambiare generazione
	}

}
