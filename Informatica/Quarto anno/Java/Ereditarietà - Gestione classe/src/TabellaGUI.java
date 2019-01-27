import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
public class TabellaGUI extends JFrame{
	private JTable tabella;
	private JScrollPane pannello;
	private int nAlunni;
	private Classe c;
	private String[][] dati;
	private String[] nomiColonne = {"N.", "Cognome", "Nome", "Email"};
	private Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	public TabellaGUI(Classe c, int nAlunni) {
		this.c=c;
		this.nAlunni=nAlunni;
		disegnaTabella();
	}
	
	private void disegnaTabella() {
		int i;
		dati = new String[nAlunni][4];
		for (i=0; i<nAlunni; i++) {
			dati[i][0]=(i+1)+".";
			dati[i][1]=c.getElenco()[i].getCognome();
			dati[i][2]=c.getElenco()[i].getNome();
			dati[i][3]=c.getElenco()[i].getEmail();;
		}
		tabella = new JTable(dati, nomiColonne);
		tabella.setRowHeight(20);
		tabella.getColumnModel().getColumn(0).setMinWidth(25);
		tabella.getColumnModel().getColumn(0).setMaxWidth(25);
		tabella.getColumnModel().getColumn(3).setMinWidth(200);
		tabella.getColumnModel().getColumn(3).setMaxWidth(300);
		setIconImage(icon);
		setTitle("Elenco classe");
		if (nAlunni<=25) {
			tabella.setPreferredScrollableViewportSize(new Dimension(700, nAlunni*(tabella.getRowHeight())));
		} else {
			tabella.setPreferredScrollableViewportSize(new Dimension(700, 25*(tabella.getRowHeight())));
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pannello = new JScrollPane(tabella);
		add(pannello);
		setLocationByPlatform(true);
		setVisible(true);
		setResizable(false);
		pack();
	}
}
