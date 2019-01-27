import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class FaseInizialeManual extends JFrame implements ActionListener, KeyListener{
	public JButton[][] pos;
	public JButton conferma;
	public ImageIcon casella = new ImageIcon("casella.png");
	private ImageIcon sfondo = new ImageIcon("sfondo.jpg");
	private ImageIcon errore = new ImageIcon("cuoreRotto.png");
	private JButton settings = new JButton(new ImageIcon("settings.png"));
	public int n;
	public int m;
	public int i;
	public int j;
	private Object s;
	private RegoleGioco g;
	public FaseInizialeManual (int n, int m, Object s, RegoleGioco g) {
		this.n=n;
		this.m=m;
		this.s=s;
		this.g=g;
		pos = new JButton[n][m];
		disegnaGUI();
	}
	
	private void disegnaGUI() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		pack();
		setTitle("Fase iniziale");
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		settings.setBounds(550, 484, 268, 64);
		settings.setBorder(null);
		settings.setBackground(Color.black);
		settings.setFocusPainted(false);
		settings.setFocusable(false);
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorderPainted(false);
		settings.addActionListener(this);
		add(settings);
		// Creazione dei bottoni in base al numero di righe e colonne acquisiti nel main
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				pos[i][j] = new JButton(casella);
				pos[i][j].setBounds((j+1)*26, 16+(1*i)+(i+1)*24, 26, 24);
				pos[i][j].setVisible(true);
				pos[i][j].setBorder(null);
				pos[i][j].setBackground(Color.black);
				pos[i][j].setFocusPainted(false);
				pos[i][j].setFocusable(false);
				pos[i][j].addActionListener(this);
				add(pos[i][j]);
				repaint();
			}
		}
		addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Se l'utente clicca su un bottone
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				if (e.getSource().equals(pos[i][j])) {
					// Se quel bottone non è già rosso
					if (pos[i][j].getBackground()!=Color.red) {
						// Allora diventa rosso
						pos[i][j].setBackground(Color.red);
					} else {
						// Altrimenti, torna nero
						pos[i][j].setBackground(Color.black);
					}
				}
			}
		} 
		if (e.getSource().equals(settings)) {
			dispose();
			CreaMatrice inizio = new CreaMatrice(g);
		}
	}

	public void keyPressed(KeyEvent tasto) {
		// Quando l'utente preme il tasto 'invio'
		if (tasto.getKeyCode()==KeyEvent.VK_ENTER) {
			// Viene chiesta la conferma
			int scelta=JOptionPane.showConfirmDialog(null, "Confernare?", "Conferma", JOptionPane.YES_NO_OPTION);
			if (scelta==0) {
				// Se l'utente clicca "Sì"
				// Viene creata la matrice di partenza
				boolean almenoUno = false;
				// E verranno controllati tutti i bottoni.
				for (i=0; i<n; i++) {
					for (j=0; j<m; j++) {
						// Se il bottone è di colore rosso
						if (pos[i][j].getBackground()==Color.red) {
							// mat si riempie con un boolean true
							g.getGenAttuale().setElem(i, j, 1);
							almenoUno = true;
						} else {
							// Altrimenti, si riempie con un false
							g.getGenAttuale().setElem(i, j, 0);						
						}
					}
				}
				// Se tutti i bottoni sono neri
				if (!almenoUno) {
					// Errore. Non si può cominciare con una generazione vuota.
					JOptionPane.showMessageDialog(null, "Impossibile creare una generazione vuota.", "Generazione vuota", JOptionPane.INFORMATION_MESSAGE, errore);
				} else {
					// Altrimenti, la finestra si chiude, e verrà creata l'altra, assieme alle info.
					dispose();
					Gioco gui = new Gioco(g, n, m, s);
					// Alla schermata di informazioni, passo anche l'interfaccia
					// perchè lì verrà richiamato il metodo per cambiare generazione
				}
			}
		}
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}

}
