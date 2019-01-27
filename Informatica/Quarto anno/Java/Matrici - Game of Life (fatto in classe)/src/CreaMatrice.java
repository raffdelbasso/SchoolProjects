import javax.swing.*;

public class CreaMatrice extends JFrame{
	private String n;
	private String m;
	private int rig;
	private int col;
	private int yesNo;
	Object[] scelte = {"Automaticamente", "Manualmente"};
	Object[] scelte2 = {"Con pulsante", "Con tempo"};
	private Object scelta;
	private Object scelta2;
	private String riepilogo;
	private RegoleGioco g;
	public CreaMatrice(RegoleGioco g) {
		this.g=g;
		mostraInput();
	}
	private void mostraInput() {
		boolean rifai;
		do {
			riepilogo="";
			do {
				rifai=false;
				n=JOptionPane.showInputDialog(null, "Numero righe:", "Numero righe", JOptionPane.QUESTION_MESSAGE);
				if (n==null) {
					System.exit(0);
				}
				if (!isIntero(n) || n.isEmpty()) {
					rifai=true;
					JOptionPane.showMessageDialog(null, "Non hai inserito un numero intero. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
				} else {
					if (Integer.valueOf(n)>20 || Integer.valueOf(n) < 1) {
						JOptionPane.showMessageDialog(null, "Inserire un numero tra 1 e 20.", "Errore", JOptionPane.ERROR_MESSAGE);
						rifai=true;
					}
				}
			} while (rifai);
			rig = Integer.valueOf(n);
			riepilogo+="Numero righe: "+rig+"\n";
			do {
				rifai=false;
				m=JOptionPane.showInputDialog(null, "Numero colonne:");
				if (m==null) {
					System.exit(0);
				}
				if (!isIntero(m) || m.isEmpty()) {
					rifai=true;
					JOptionPane.showMessageDialog(null, "Non hai inserito un numero intero. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
				} else {
					if (Integer.valueOf(m)>20 || Integer.valueOf(m) < 1) {
						JOptionPane.showMessageDialog(null, "Inserire un numero tra 1 e 20.", "Errore", JOptionPane.ERROR_MESSAGE);
						rifai=true;
					}
				}
			} while (rifai);
			rifai=false;
			col = Integer.valueOf(m);
			riepilogo+="Numero colonne: "+col+"\n";
			scelta = JOptionPane.showInputDialog(null, "In che modo vuoi generare la fase iniziale?", "Seleziona opzione", JOptionPane.INFORMATION_MESSAGE, null, scelte, scelte[0]);
			if (scelta==null) {
				System.exit(0);
			}
			riepilogo+="Generazione fase iniziale: "+scelta+"\n";
			scelta2 = JOptionPane.showInputDialog(null, "In che modo vuoi far passare le generazioni?", "Seleziona opzione", JOptionPane.INFORMATION_MESSAGE, null, scelte2, scelte[0]);
			if (scelta2==null) {
				System.exit(0);
			}
			riepilogo+="Passaggio alla generazione successiva: "+scelta2+"\nProcedo?";
			yesNo = JOptionPane.showConfirmDialog(null, riepilogo, "Conferma", JOptionPane.YES_NO_OPTION);
			if (yesNo==1) {
				rifai=true;
			}
			if (yesNo==JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}
		} while (rifai);
			if (scelta==scelte[0]) {
			String perc;
			do { 
				rifai=false;
				perc=JOptionPane.showInputDialog(null, "In percentuale, quante celle vuoi riempire?");
				if (perc==null) {
					System.exit(0);
				}
				if (!isIntero(perc) || perc.isEmpty()) {
					rifai=true;
					JOptionPane.showMessageDialog(null, "Non hai inserito un numero intero. Riprova.", "Errore", JOptionPane.ERROR_MESSAGE);
				} else {
					if (Integer.valueOf(perc)>100 || Integer.valueOf(perc) < 0) {
						JOptionPane.showMessageDialog(null, "Inserire un numero tra 0 e 100.", "Errore", JOptionPane.ERROR_MESSAGE);
						rifai=true;
					}
				}
			} while (rifai);
			int p = Integer.valueOf(perc);
			g = new RegoleGioco(Integer.valueOf(n), Integer.valueOf(m));
			FaseInizialeAuto inizio = new FaseInizialeAuto(rig, col, p, scelta2, g);
		} else {
			FaseInizialeManual inizio = new FaseInizialeManual(rig, col, scelta2);
		}
	}
	private boolean isIntero(String n) {
		int i;
		for (i=0; i<n.length(); i++) {
			if ((n.charAt(i) < '0' || n.charAt(i) > '9') && n.charAt(i)!='-') {
				return false;
			}
		}
		return true;
	}
}