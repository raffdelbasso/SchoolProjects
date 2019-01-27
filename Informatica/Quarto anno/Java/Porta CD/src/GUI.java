import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
	private ImageIcon sfondo = new ImageIcon("sfondo.png");
	private JButton btn = new JButton(new ImageIcon("btn1.png"));
	private JButton btn2 = new JButton(new ImageIcon("btn2.png"));
	private JButton btn3 = new JButton(new ImageIcon("btn3.png"));
	private JLabel indici = new JLabel("1/1");
	private JButton frecc = new JButton();
	private JTextField j1 = new JTextField();
	private JTextField j2 = new JTextField();
	private JTextField j3 = new JTextField();
	private Cursor c = new Cursor(HAND_CURSOR);
	private Object[] op = {"Aggiungi CD", "Rimuovi CD", "Cerca CD per titolo"};
	private Object[] campi = {"Titolo: ", j1, "Autore: ", j2, "Anno di uscita: ", j3};
	private Object scelta, sceltaPos, sceltaPos2;
	private ImageIcon cdVuoto = new ImageIcon("vuoto.png");
	private ImageIcon cdPieno = new ImageIcon("cd.png");
	private ImageIcon question = new ImageIcon("question.png");
	private ImageIcon question2 = new ImageIcon("question2.png");
	private ImageIcon question3 = new ImageIcon("question3.png");
	private ImageIcon ok = new ImageIcon("ok.png");
	private ImageIcon occ = new ImageIcon("occ.png");
	private ImageIcon sost = new ImageIcon("sost.png");
	private ImageIcon remove = new ImageIcon("remove.png");
	private ImageIcon vuoto = new ImageIcon("null.png");
	private ImageIcon info = new ImageIcon("info.png");
	private ImageIcon freccia = new ImageIcon("freccia1.png");
	private ImageIcon freccia2 = new ImageIcon("freccia2.png");
	private PortaCD[] p = new PortaCD[2];
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 36);
	private String ricerca;
	private int[] posizione;
	private int nTrovati;
	private int selezionato=0;
	private JButton cds[];
	private Object[] pos;
	private Object[] titoli;
	private CD cd;
	private int yesNo, rimuovi, i, uguali;
	private boolean ricomincia, errore;
	public GUI() {
		p[selezionato]=new PortaCD(18);
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setTitle("Porta CD");
		impostaGriglia();
		btn.setBounds(20, 500, 213, 50);
		btn.setBorder(null);
		btn.setCursor(c);
		btn.addActionListener(this);
		add(btn);
		btn2.setBounds(320, 500, 213, 50);
		btn2.setBorder(null);
		btn2.setCursor(c);
		btn2.addActionListener(this);
		add(btn2);
		btn3.setBounds(320, 500, 213, 50);
		btn3.setBorder(null);
		btn3.setCursor(c);
		btn3.addActionListener(this);
		frecc.setIcon(freccia);
		frecc.setBounds(700, 503, 55, 42);
		frecc.setBorder(null);
		frecc.setCursor(c);
		frecc.setBackground(Color.black);
		frecc.setOpaque(false);
		frecc.setVisible(false);
		frecc.addActionListener(this);
		add(frecc);
		indici.setBounds(750, 550, 60, 60);
		indici.setForeground(Color.white);
		indici.setFont(font);
		add(indici);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn)) {
			do {
				ricomincia = false;
				scelta=JOptionPane.showInputDialog(null, "Inserire operazione:", "Modifica Porta CD", JOptionPane.QUESTION_MESSAGE, question, op, op[0]);
				if (scelta==op[0]) {
					do {
						errore=false;
						scelta = JOptionPane.showConfirmDialog(null, campi, "Compila", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, question2);
						j1.setBackground(Color.white);
						j2.setBackground(Color.white);
						j3.setBackground(Color.white);
						if (j1.getText().isEmpty()) {
							j1.setBackground(Color.orange);
							errore=true;
						}
						if (j2.getText().isEmpty()) {
							j2.setBackground(Color.orange);
							errore=true;
						}
						if (j3.getText().isEmpty() || !isIntero(j3.getText())) {
							j3.setBackground(Color.orange);
							errore=true;
						}
					} while (errore && scelta != (Object)JOptionPane.CANCEL_OPTION && scelta != (Object) JOptionPane.CLOSED_OPTION);
					if (scelta == (Object)JOptionPane.CANCEL_OPTION || scelta == (Object) JOptionPane.CLOSED_OPTION) {
						ricomincia=true;
						j1.setBackground(Color.white);
						j2.setBackground(Color.white);
						j3.setBackground(Color.white);
					} else {
						cd = new CD(j1.getText(), j2.getText(), Integer.valueOf(j3.getText()));
						do {
							ricomincia=false;
							sceltaPos = JOptionPane.showInputDialog(null, "In quale posizione vuoi inserire questo CD?", "Posizione", JOptionPane.OK_OPTION, question3, pos, pos[0]);
							if (sceltaPos != null) {
								int posi=cdDaAggiungere();
								if (p[selezionato].getCD(posi)==null) {
									p[selezionato].setCD(cd, posi);
									JOptionPane.showMessageDialog(null, "CD aggiunto!", "Ok!", JOptionPane.INFORMATION_MESSAGE, ok);
								} else {
									yesNo = JOptionPane.showConfirmDialog(null, "C'è già un CD in posizione "+(posi+1)+":\n"+p[selezionato].getCD(posi).getTitolo()+"\nSovrascrivo?", "Vuoi sovrascrivere?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, occ);
									if (yesNo==0) {
										p[selezionato].setCD(cd, posi);
										JOptionPane.showMessageDialog(null, "CD sostituito!", "Ok!", JOptionPane.INFORMATION_MESSAGE, sost);
									} else {
										ricomincia=true;
									}
								}
							}
						} while (ricomincia);
						aggiungiCD();
					}
				} else {
					if (scelta==op[1]) {
						sceltaPos2 = JOptionPane.showInputDialog(null, "Quale CD vuoi rimuovere?", "Rimuovi CD", JOptionPane.OK_OPTION, remove, titoli, titoli[0]);
						if (sceltaPos2 == (Object)JOptionPane.CANCEL_OPTION || sceltaPos2 == null) {
							ricomincia=true;
						} else {
							if (String.valueOf(sceltaPos2).contains("[Posizione libera]")) {
								JOptionPane.showMessageDialog(null, "Non c'è nessun CD in questa posizione.", "Posizione vuota", JOptionPane.INFORMATION_MESSAGE, vuoto);
							} else {
								rimuovi = cdDaRimuovere(); 
								p[selezionato].killCD(rimuovi);
								cds[rimuovi].setIcon(cdVuoto);
								cds[rimuovi].setEnabled(false);
								aggiungiCD();
							}
						}
					} else {
						if (scelta==op[2]) {
							do {
								ricerca=JOptionPane.showInputDialog(null, "Cerca CD per titolo:", "Ricerca", JOptionPane.QUESTION_MESSAGE);
							} while (ricerca!= null && ricerca.isEmpty());
							if (ricerca == null) {
								ricomincia = true;
							} else {
								nTrovati = cdTrovati(ricerca);
								if (nTrovati==0) {
									JOptionPane.showMessageDialog(null, "Nessun CD trovato con titolo \""+ricerca+"\".", "Nessun CD trovato", JOptionPane.INFORMATION_MESSAGE, vuoto);
								} else {
									for (i=0; i<nTrovati; i++) {
										JOptionPane.showMessageDialog(null, "CD trovato in posizione "+(posizione[i]+1)+"! ("+(i+1)+"/"+nTrovati+")\n"+p[selezionato].getCD(posizione[i]).toString(), "CD trovato!", JOptionPane.INFORMATION_MESSAGE, vuoto);
									}
								}
							}
						}
					}
				}
			} while (ricomincia);
			j1.setText("");
			j2.setText("");
			j3.setText("");
		}
		
		for (i=0; i<p[selezionato].getDim(); i++) {
			if (e.getSource().equals(cds[i])) {
				JOptionPane.showMessageDialog(null, "Informazioni CD "+(i+1)+":\n"+p[selezionato].getCD(i).toString(), "Info", JOptionPane.INFORMATION_MESSAGE, info);
			}
		}
		if (e.getSource().equals(btn2)) {
			do {
				errore=false;
				ricerca=JOptionPane.showInputDialog(null, "Capienza:", "Nuovo Porta CD", JOptionPane.QUESTION_MESSAGE);
				if (!isIntero(ricerca)) {
					errore=true;
				} else {
					if (Integer.valueOf(ricerca)>18 || Integer.valueOf(ricerca)<1) {
						JOptionPane.showMessageDialog(null, "La capienza deve essere compresa tra 1 e 18 CD.", "Errore", JOptionPane.ERROR_MESSAGE);
						errore=true;
					}
				}
			} while (errore);
			JOptionPane.showMessageDialog(null, "Porta CD creato!", "OK!", JOptionPane.INFORMATION_MESSAGE, ok);
			indici.setText("1/2");
			p[1] = new PortaCD(Integer.valueOf(ricerca));
			btn2.setVisible(false);
			frecc.setVisible(true);
			add(btn3);
		}
		if (e.getSource().equals(btn3)) {
			uguali=p[0].confrontaCollezione(p[1]);
			if (uguali==0) {
				JOptionPane.showMessageDialog(null, "I due porta CD non hanno alcun CD in comune.", "Nessuna corrispondenza", JOptionPane.INFORMATION_MESSAGE, vuoto);
			} else {
				JOptionPane.showMessageDialog(null, "I due porta CD hanno "+uguali+" CD in comune.", "Corrispondenze trovate", JOptionPane.INFORMATION_MESSAGE, vuoto);
			}
		}
		if (e.getSource().equals(frecc)) {
			rimuoviGriglia();
			if (selezionato==0) {
				selezionato=1;
				frecc.setIcon(freccia2);
				indici.setText("2/2");
			} else {
				selezionato=0;
				frecc.setIcon(freccia);
				indici.setText("1/2");
			}
			impostaGriglia();
		}
	}
	
	private boolean isIntero(String x) {
		boolean b=true;
		int i=0;
		while (i<x.length() && b) {
			if (x.charAt(i) < '0' || x.charAt(i) > '9') {
				b=false;
			}
			i++;
		}
		return b;
	}
	
	private void rimuoviGriglia() {
		for (i=0; i<p[selezionato].getDim(); i++) {
			remove(cds[i]);
		}
		repaint();
	}
	
	private void impostaGriglia() {
		posizione = new int[p[selezionato].getDim()];
		cds = new JButton[p[selezionato].getDim()];
		pos = new Object[p[selezionato].getDim()];
		titoli = new Object[p[selezionato].getDim()];
		for (i=0; i<p[selezionato].getDim(); i++) {
			cds[i] = new JButton(cdVuoto);
			cds[i].setBounds(140+(i%6)*90, (i/6+1)*117, 80, 84);
			cds[i].setBorder(null);
			cds[i].setBackground(Color.black);
			cds[i].setFocusPainted(false);
			cds[i].setBorderPainted(false);
			cds[i].setCursor(c);
			cds[i].setOpaque(false);
			cds[i].setEnabled(false);
			cds[i].addActionListener(this);
			add(cds[i]);
			pos[i] = i+1;
			titoli[i] = (i+1)+". [Posizione libera]";
		}
		aggiungiCD();
	}
	
	private void aggiungiCD() {
		for (i=0; i<p[selezionato].getDim(); i++) {
			if (p[selezionato].getCD(i)!=null) {
				cds[i].setEnabled(true);
				cds[i].setIcon(cdPieno);
				titoli[i] = (i+1)+". "+p[selezionato].getCD(i).getTitolo();
			} else {
				titoli[i] = (i+1)+". [Posizione libera]";
			}
		}
	}
	private int cdDaRimuovere() {
		String r = ""+String.valueOf(sceltaPos2).charAt(0);
		if (String.valueOf(sceltaPos2).charAt(1)!='.') {
			r = r+String.valueOf(sceltaPos2).charAt(1);
		}
		return Integer.valueOf(r)-1;
	}
	private int cdDaAggiungere() {
		String r = ""+String.valueOf(sceltaPos).charAt(0);
		if (String.valueOf(sceltaPos).length()>1) {
			r = r+String.valueOf(sceltaPos).charAt(1);
		}
		return Integer.valueOf(r)-1;
	}
	private int cdTrovati(String ricerca) {
		int partenza=p[selezionato].cercaCDperTitolo(ricerca, 0);
		int cd=0;
		while (partenza!=-1) {
			posizione[cd]=partenza;
			cd++;
			partenza=p[selezionato].cercaCDperTitolo(ricerca, partenza+1);
		}
		
		return cd;
	}
}
