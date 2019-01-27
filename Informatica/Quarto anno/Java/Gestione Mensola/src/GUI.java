import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
	private ImageIcon sfondo = new ImageIcon("sfondo.jpg");
	private JButton btn = new JButton(new ImageIcon("btn.png"));
	private JTextField j1 = new JTextField();
	private JTextField j2 = new JTextField();
	private JTextField j3 = new JTextField();
	private Cursor c = new Cursor(HAND_CURSOR);
	private Object[] op = {"Aggiungi libro", "Rimuovi libro"};
	private Object[] campi = {"Titolo: ", j1, "Autore: ", j2, "Numero pagine: ", j3};
	private Object scelta, sceltaPos, sceltaPos2;
	private ImageIcon question = new ImageIcon("question.png");
	private ImageIcon question2 = new ImageIcon("question2.png");
	private ImageIcon question3 = new ImageIcon("question3.png");
	private ImageIcon ok = new ImageIcon("ok.png");
	private ImageIcon occ = new ImageIcon("occ.png");
	private ImageIcon sost = new ImageIcon("sost.png");
	private ImageIcon remove = new ImageIcon("remove.png");
	private ImageIcon vuoto = new ImageIcon("null.png");
	private ImageIcon info = new ImageIcon("info.png");
	private Mensola m = new Mensola();
	private JButton libri[] = new JButton[m.getMaxNumVolumi()];
	private Object[] pos = new Object[m.getMaxNumVolumi()];
	private Object[] titoli = new Object[m.getMaxNumVolumi()];
	private Libro l;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int yesNo, rimuovi;
	private boolean ricomincia, errore;
	public GUI() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2));
		setTitle("La tua mensola");
		for (int i=0; i<m.getMaxNumVolumi(); i++) {
			libri[i] = new JButton(new ImageIcon("l"+(int)((Math.random()*6)+1)+".png"));
			libri[i].setBounds(270+(i+1)*28, 247, 28, 157);
			libri[i].setBorder(null);
			libri[i].setVisible(false);
			libri[i].setCursor(c);
			libri[i].addActionListener(this);
			add(libri[i]);
			pos[i] = i+1;
			titoli[i] = (i+1)+". [Posizione libera]";
		}
		btn.setBounds(20, 450, 213, 50);
		btn.setBorder(null);
		btn.setCursor(c);
		btn.addActionListener(this);
		add(btn);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn)) {
			do {
				ricomincia = false;
				scelta=JOptionPane.showInputDialog(null, "Inserire operazione:", "Modifica mensola", JOptionPane.QUESTION_MESSAGE, question, op, op[0]);
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
						l = new Libro(j1.getText(), j2.getText(), Integer.valueOf(j3.getText()));
						do {
							ricomincia=false;
							sceltaPos = JOptionPane.showInputDialog(null, "In quale posizione vuoi inserire questo libro?", "Posizione", JOptionPane.OK_OPTION, question3, pos, pos[0]);
							if (sceltaPos != (Object) JOptionPane.CANCEL_OPTION && sceltaPos != null) {
								int posi=libroDaAggiungere();
								if (m.setVolume(l, posi)==-3) {
									JOptionPane.showMessageDialog(null, "Libro aggiunto!", "Ok!", JOptionPane.INFORMATION_MESSAGE, ok);
								} else {
									yesNo = JOptionPane.showConfirmDialog(null, "C'è già un libro in posizione "+(posi+1)+":\n"+m.getVolume(posi).getTitolo()+"\nSovrascrivo?", "Vuoi sovrascrivere?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, occ);
									if (yesNo==0) {
										m.setVolume2(l, posi);
										libri[posi].setIcon(new ImageIcon("l"+(int)((Math.random()*6)+1)+".png"));
										JOptionPane.showMessageDialog(null, "Libro sostituito!", "Ok!", JOptionPane.INFORMATION_MESSAGE, sost);
									} else {
										ricomincia=true;
									}
								}
							}
						} while (ricomincia);
						aggiungiLibri();
					}
					
				} else {
					if (scelta==op[1]) {
						sceltaPos2 = JOptionPane.showInputDialog(null, "Quale libro vuoi rimuovere?", "Rimuovi libro", JOptionPane.OK_OPTION, remove, titoli, titoli[0]);
						if (sceltaPos2 == (Object)JOptionPane.CANCEL_OPTION || sceltaPos2 == null) {
							ricomincia=true;
						} else {
							if (String.valueOf(sceltaPos2).contains("[Posizione libera]")) {
								JOptionPane.showMessageDialog(null, "Non c'è nessun libro in questa posizione.", "Posizione vuota", JOptionPane.INFORMATION_MESSAGE, vuoto);
							} else {
								rimuovi = libroDaRimuovere(); 
								m.rimuoviVolume(rimuovi);
								libri[rimuovi].setVisible(false);
								aggiungiLibri();
							}
						}
					}
				}
			} while (ricomincia);
			j1.setText("");
			j2.setText("");
			j3.setText("");
		}
		for (int i=0; i<m.getMaxNumVolumi(); i++) {
			if (e.getSource().equals(libri[i])) {
				JOptionPane.showMessageDialog(null, "Informazioni libro "+(i+1)+":\n"+m.getVolume(i).toString(), "Info", JOptionPane.INFORMATION_MESSAGE, info);
			}
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
	private void aggiungiLibri() {
		for (int i=0; i<m.getMaxNumVolumi(); i++) {
			if (m.getVolume(i)!=null) {
				libri[i].setVisible(true);
				titoli[i] = (i+1)+". "+m.getVolume(i).getTitolo();
			} else {
				titoli[i] = (i+1)+". [Posizione libera]";
			}
		}
	}
	private int libroDaRimuovere() {
		String r = ""+String.valueOf(sceltaPos2).charAt(0);
		if (String.valueOf(sceltaPos2).charAt(1)!='.') {
			r = r+String.valueOf(sceltaPos2).charAt(1);
		}
		return Integer.valueOf(r)-1;
	}
	private int libroDaAggiungere() {
		String r = ""+String.valueOf(sceltaPos).charAt(0);
		if (String.valueOf(sceltaPos).length()>1) {
			r = r+String.valueOf(sceltaPos).charAt(1);
		}
		return Integer.valueOf(r)-1;
	}
}
