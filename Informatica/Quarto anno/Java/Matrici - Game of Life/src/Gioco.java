import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class Gioco extends JFrame implements ActionListener{
	private int n;
	private int m;
	private int i;
	private int j;
	private int nVivi;
	private int nMorti;
	private int perc;
	private int scelta;
	private Object s;
	boolean tempo=false;
	private ImageIcon sfondo = new ImageIcon("sfondo2.jpg");
	private ImageIcon casellaVuota = new ImageIcon("casella.png");
	private ImageIcon casellaPiena = new ImageIcon("casellaPiena.png");
	private JLabel[][] casella;
	private JLabel titolo = new JLabel(new ImageIcon("titolo.png"));
	private JLabel info = new JLabel(new ImageIcon("info.png"));
	private JLabel lblTot;
	private JLabel lblVive = new JLabel();
	private JLabel lblMorte = new JLabel();
	private JLabel lblPerc = new JLabel();
	private JButton settings = new JButton(new ImageIcon("settings.png"));
	private JButton btn = new JButton(new ImageIcon("btn.png"));
	private JButton btn2 = new JButton(new ImageIcon("btn2.png"));
	private JButton btn3 = new JButton(new ImageIcon("btn3.png"));
	private Font f = new Font("Times Roman", Font.PLAIN, 22);
	public TimerTask d;
	public Timer t = new Timer();
	private RegoleGioco g;
	// La finestra di info mi serve anche in questa interfaccia per avere la possibilità
	// di chiuderla alla fine del gioco.
	public Gioco (RegoleGioco g, int n, int m, Object s) {
		this.n=n;
		this.m=m;
		this.s=s;
		this.g=g;
		lblTot = new JLabel(String.valueOf(n*m));
		casella = new JLabel[n][m];
		disegnaGUI();
	}
	
	private void disegnaGUI() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Game of Life");
		info.setBounds(560, 140, 269, 276);
		lblTot.setBounds(720, 150, 50, 50);
		lblTot.setForeground(Color.white);
		lblTot.setFont(f);
		lblVive.setBounds(705, 218, 50, 50);
		lblVive.setForeground(Color.white);
		lblVive.setFont(f);
		lblMorte.setBounds(730, 288, 50, 50);
		lblMorte.setForeground(Color.white);
		lblMorte.setFont(f);
		lblPerc.setBounds(780, 356, 60, 50);
		lblPerc.setForeground(Color.white);
		lblPerc.setFont(f);
		titolo.setBounds(555, 20, 289, 69);
		settings.setBounds(570, 484, 268, 64);
		settings.setBorder(null);
		settings.setBackground(Color.black);
		settings.setFocusPainted(false);
		settings.setFocusable(false);
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorderPainted(false);
		settings.addActionListener(this);
		add(settings);
		if (s=="Con pulsante") {
			btn.setBounds(570, 420, 268, 64);
			btn.setFocusPainted(false);
			btn.setFocusable(false);
			btn.setBorder(null);
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);
			btn.setBackground(Color.black);
			btn.addActionListener(this);
			add(btn);
		} else {
			//Creazione bottone play/pausa per il tempo
			btn2.setBounds(570, 420, 268, 64);
			btn2.setBorder(null);
			btn2.setBackground(Color.black);
			btn2.setFocusPainted(false);
			btn2.setFocusable(false);
			btn2.setOpaque(false);
			btn2.setContentAreaFilled(false);
			btn2.setBorderPainted(false);
			btn2.addActionListener(this);
			add(btn2);
			btn3.setBounds(570, 420, 268, 64);
			btn3.setBorder(null);
			btn3.setBackground(Color.black);
			btn3.setFocusPainted(false);
			btn3.setFocusable(false);
			btn3.setOpaque(false);
			btn3.setContentAreaFilled(false);
			btn3.setBorderPainted(false);
			btn3.addActionListener(this);
			btn3.setVisible(false);
			add(btn3);
		}
		add(lblTot);
		add(lblVive);
		add(lblMorte);
		add(lblPerc);
		add(titolo);
		add(info);
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				casella[i][j] = new JLabel();
				casella[i][j].setBounds((j+1)*26, 16+(1*i)+(i+1)*24, 26, 24);
				casella[i][j].setVisible(true);
				add(casella[i][j]);
				repaint();
			}
		}
		mostraCelle();
	}


	public void mostraCelle() {
		nVivi=0;
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				if (g.getGenAttuale().getElem(i, j)==0) {
					casella[i][j].setIcon(casellaVuota);
				} else {
					casella[i][j].setIcon(casellaPiena);
					nVivi++;
				}
			}
		}
		nMorti=(n*m)-nVivi;
		perc=(int)Math.floor(Math.round(nVivi*100/(n*m)));
		info.setText("Celle in vita: "+String.valueOf(nVivi));
		lblPerc.setText(String.valueOf(perc)+"%");
		lblVive.setText(String.valueOf(nVivi));
		lblMorte.setText(String.valueOf(nMorti));
		if (nVivi==0) {
			tempo=false;
			scelta=JOptionPane.showConfirmDialog(null, "Gioco finito! Vuoi ricominciare?", "Game over", JOptionPane.YES_NO_OPTION);
			if (scelta==0) {
				dispose();
				CreaMatrice ricomincia = new CreaMatrice(g);
			} else {
				System.exit(0);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btn)) {
			visualizzaRis();
		}
		if (e.getSource().equals(btn2)) {
			d = new CambioTimer(this, g);
			tempo=true;
			btn3.setVisible(true);
			btn2.setVisible(false);
			t.schedule(d, 0);
		}
		if (e.getSource().equals(btn3)) {
			tempo=false;
			btn2.setVisible(true);
			btn3.setVisible(false);
		}
		if (e.getSource().equals(settings)) {
			dispose();
			CreaMatrice inizio = new CreaMatrice(g);
		}
	}
	public void visualizzaRis() {
		g.copiaAttualeInPrecedente();
		g.nuovaGenerazione();
		mostraCelle();
	}
}
