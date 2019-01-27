import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Classe Finestra: qui verrà visualizzata la matrice creata nel main assieme ai pulsanti funzionali.
public class Finestra extends JFrame implements ActionListener {
	private int n, m, i, j;
	Matrice mat;
	// Variabile che indica il numero di bottoni totali.
	private int nBottoni=0;
	// Ogni schermata ha 5 pulsanti. Questa variabile
	// mi serve per stabilire se nascondere il pulsante "freccia avanti".
	private int nSchermate;

	// I font sono 3 perchè, nel metodo disegnaGUI, ho fatto in modo che il numero di cifre di un numero
	// della matrice influisca sulla sua dimensione
	// per fare in modo che non esca dai margini.
	private Font f = new Font("TimesRoman", Font.BOLD, 23);
	private Font f2 = new Font("TimesRoman", Font.BOLD, 18);
	private Font f3 = new Font("TimesRoman", Font.BOLD, 16);
	private ImageIcon sfondo = new ImageIcon("sfondo.jpg");
	// Possibili operazioni del bottone 5
	private Object[] sel = {"Media di una riga", "Media di una colonna"};
	// Scelta effettuata dall'utente (Usato nel bottone 5)
	private Object selezione;
	// Sfondo 2: rosso anzichè blu. Lo userò come sfondo
	// delle finestre che si apriranno cliccando sui pulsanti funzionali.
	private ImageIcon sad = new ImageIcon("sad.png");
	private ImageIcon happy = new ImageIcon("happy.png");
	private ImageIcon img = new ImageIcon("Rettangolo.png");
	// Matrice di JLabel: rettangoli...
	private JLabel[][] ret;
	// ...e numeri.
	private JLabel[][] num;
	// Vettore monodimensionale di JLabel contenenti i numeri (0,1,2,3...) di righe...
	private JLabel[] rig;
	// ...e colonne.
	private JLabel[] col;
	private Object[] selRiga;
	private Object[] selCol;
	// Cursore di tipo HAND_CURSOR: il mouse prenderà la forma della mano
	// quando passerà dai pulsanti.
	// Lo utilizzerò per i vari pulsanti attraverso il metodo setCursor della classe JButton.
	// Non capisco perchè mi dia un warning.
	private Cursor c = new Cursor(HAND_CURSOR);
	private JButton[] b;
	// Bottone "Ripristina colori"
	private JButton bR = new JButton(new ImageIcon("bR.png"));
	// Bottone freccia avanti
	private JButton av = new JButton(new ImageIcon("avanti.png"));
	// Bottone freccia indietro
	private JButton in = new JButton(new ImageIcon("indietro.png"));
	// int posizione: Posizione della riga o della colonna di cui calcolare la media.
	// Usato nell'operazione del bottone 5
	// int schermata: numero che indica in quale schermata di pulsanti ci troviamo
	private int posizione, schermata=0;
	// Boolean utilizzato in vari pulsanti perchè resetta i colori nel caso valga true,
	// in modo da evitare accavallamenti di colori diversi.
	private boolean colori = false;
	// Costruttore: la finestra riceverà le due dimensioni.
	public Finestra(int n, int m){
		this.n=n;
		this.m=m;
		mat = new Matrice(n, m);
		mat.genera(0, 1000);
		// Creazione di un array da 0 a n per le righe
		selRiga= new Object[n];
		for (i=0; i<n; i++) {
			selRiga[i]=i;
		}
		// e per le colonne
		selCol= new Object[m];
		for (i=0; i<m; i++) {
			selCol[i]=i;
		}
		// Questi due array Object[] saranno usati per un menù
		// del bottone 5
		// Il costruttore crea anche i vettori e le matrici dell'interfaccia
		// in base alle dimensioni della matrice creata nel main.
		rig = new JLabel[n];
		col = new JLabel[m];
		ret = new JLabel[n][m];
		num = new JLabel[n][m];
		File immagine;
		// In questa parte di codice saranno calcolati il numero di pulsanti
		// e, di conseguenza, il numero di schermate.
		do {
			immagine=new File("btn"+nBottoni+".png");
			nBottoni++;
		} while (immagine.exists());
		nBottoni--;
		nSchermate=(int)((nBottoni-1)/5);
		b = new JButton[nBottoni];
		disegnaGUI();
	}
	
	public void disegnaGUI() {
		// Questi oggetti rendono i pulsanti trasparenti.
		FixedStateButtonModel vuotoAvanti = new FixedStateButtonModel();
		FixedStateButtonModel vuotoIndietro = new FixedStateButtonModel();
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		pack();
		setTitle("La tua matrice");
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Doppio for: qui verranno aggiunti, man mano, i rettangoli, i numeri di riga e i numeri presenti nella matrice.
		for (i=0; i<n; i++) {
			for (j=0; j<m; j++) {
				rig[i] = new JLabel(String.valueOf(i));
				col[j] = new JLabel(String.valueOf(j));
				rig[i].setFont(f2);
				rig[i].setForeground(Color.WHITE);
				if (i==0) {
					rig[i].setBounds(67, 48, 20, 20);
				} else {
					// Per i setBounds, ho impostato un'espressione matematica per fare in modo
					// che i numeri si spostino man mano verso il basso (numeri di righe).
					if (i>9) {
						rig[i].setBounds(60, (i+1)*44-((i-1)*3), 20, 20);
					} else {
						rig[i].setBounds(67, (i+1)*45-(i*3), 20, 20);
					}
				}
				col[j].setFont(f2);
				col[j].setForeground(Color.WHITE);
				// Stessa cosa per i numeri di colonne, che si spostano man mano verso destra.
				if (j==0) {
					col[j].setBounds(115, 18, 20, 20);
				} else {
					col[j].setBounds((j+1)*110-(j*26), 18, 20, 20);
				}
				add(rig[i]);
				add(col[j]);
				ret[i][j] = new JLabel(img);
				num[i][j] = new JLabel (String.valueOf(mat.getElem(i, j)));
				// Anche i rettangoli e i numeri della matrice si spostano man mano.
				ret[i][j].setBounds((j+1)*82, (i+1)*41, 82, 41);
				num[i][j].setBounds(((j+1)*82)+34, (i+1)*41, 82, 41);
				num[i][j].setFont(f);
				num[i][j].setForeground(Color.WHITE);
				// In base al numero di cifre, la JLabel sarà posizionata in modi diversi, in modo da tenere il numero sempre centrato
				// dato che non si centra in automatico.
				if (mat.getElem(i, j) >=10 && mat.getElem(i, j) <= 99) {
					num[i][j].setBounds(((j+1)*82)+30, (i+1)*41, 82, 41);
				}
				if (mat.getElem(i, j) >= 100 && mat.getElem(i, j) <= 999) {
					num[i][j].setBounds(((j+1)*82)+23, (i+1)*41, 82, 41);
				}
				if (mat.getElem(i, j) >= 1000 && mat.getElem(i, j) <= 9999) {
					num[i][j].setBounds(((j+1)*82)+16, (i+1)*41, 82, 41);
				}
				if (mat.getElem(i, j) >= 10000 && mat.getElem(i, j) <= 99999) {
					num[i][j].setBounds(((j+1)*82)+12, (i+1)*41, 82, 41);
				}
				// Se il numero ha più di 5 cifre, sarà più piccolo.
				if (mat.getElem(i, j) >= 100000 && mat.getElem(i, j) <= 999999) {
					num[i][j].setFont(f2);
					num[i][j].setBounds(((j+1)*82)+14, (i+1)*41, 82, 41);
				}
				if (mat.getElem(i, j) >= 1000000 && mat.getElem(i, j) <= 9999999) {
					num[i][j].setFont(f2);
					num[i][j].setBounds(((j+1)*82)+10, (i+1)*41, 82, 41);
				}
				if (mat.getElem(i, j) >= 10000000 && mat.getElem(i, j) <= 99999999) {
					num[i][j].setFont(f3);
					num[i][j].setBounds(((j+1)*82)+10, (i+1)*41, 82, 41);
				}
				add(ret[i][j]);
				add(num[i][j]);
			}
		}
		bR.setVisible(false);
		bR.setBounds(581, 21, 213, 50);
		bR.setBorder(null);
		bR.setCursor(c);
		bR.addActionListener(this);
		add(bR);
		av.setVisible(true);
		av.setBounds(762, 552, 33, 24);
		av.setBorder(null);
		av.setCursor(c);
		av.setBackground(Color.black);
		av.setFocusPainted(false);
		av.setFocusable(false);
		av.setModel(vuotoAvanti);
		av.addActionListener(this);
		add(av);
		in.setVisible(false);
		in.setBounds(578, 552, 33, 24);
		in.setBorder(null);
		in.setCursor(c);
		in.setBackground(Color.black);
		in.setFocusPainted(false);
		in.setFocusable(false);
		in.setModel(vuotoIndietro);
		in.addActionListener(this);
		add(in);
		// Creazione dei pulsanti schermata 0
		creaPulsanti();
		repaint();
	}
	public void actionPerformed(ActionEvent e) {
		// Se l'utente clicca sul bottone 1, verrà visualizzato
		// un messaggio contenente una stringa creata con il richiamo
		// del metodo sommaRighe.
		if (e.getSource().equals(b[0])) {
			JOptionPane.showMessageDialog(this, mat.sommaRighe(), "Risultato", JOptionPane.INFORMATION_MESSAGE);
		}
		// Se l'utente clicca sul bottone 2, sarà richiamato il metodo matriceTrasposta.
		if (e.getSource().equals(b[1])) {
			JOptionPane.showMessageDialog(this, "La matrice trasposta è la seguente: \n"+mat.matriceTrasposta(), "Risultato", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource().equals(b[2])) {
			// Calcolo delle diagonali e sottomatrici: solo se la matrice è quadrata.
			if (n==m) {
				if (colori) {
					colorReset();
				}
				bR.setEnabled(true);
				bR.setVisible(true);
				colori=true;
				for (i=0; i<n; i++) {
					for (j=0; j<m; j++) {
						if (i==j) {
							num[i][j].setForeground(Color.red);
						} else {
							if (i<j) {
								num[i][j].setForeground(Color.green);
							} else {
								num[i][j].setForeground(Color.yellow);
							}
						}
					}
				}
				JOptionPane.showMessageDialog(null, mat.somme(), "Risultato", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "La matrice non è quadrata.", "Errore", JOptionPane.ERROR_MESSAGE, sad);
			}
		}
		if (e.getSource().equals(b[3])) {
			// Menù che chiede all'utente se vuole effettuare la media di una riga o di una colonna.
			selezione = JOptionPane.showInputDialog(null, "Seleziona operazione:", "Opzioni", JOptionPane.INFORMATION_MESSAGE, null, sel, sel[0]);
			if (selezione!=null) {
				// Se l'utente ha scelto la prima opzione
				if (selezione.equals(sel[0])) {
					// Chiedo di quale riga si vuole calcolare la media
					// con riferimento all'array creato alla riga 69, che contiene numeri da 0 a n.
					selezione = JOptionPane.showInputDialog(null, "Di quale riga vuoi calcolare la media?", "Seleziona riga", JOptionPane.INFORMATION_MESSAGE, null, selRiga, selRiga[0]);
					if (selezione!=null) {
						// La riga scelta diventa un intero.
						posizione=(int)selezione;
						if (colori) {
							colorReset();
						}
						// Faccio comparire il pulsante 4 (color reset)
						bR.setEnabled(true);
						bR.setVisible(true);
						colori=true;
						for (i=0; i<m; i++) {
							num[posizione][i].setForeground(Color.green);
						}
						// Visualizzo il risultato.
						JOptionPane.showMessageDialog(null, "La media della riga "+posizione+" è: "+mat.medRiga(posizione), "Risultato", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					// Stessa cosa nel caso in cui l'utente abbia scelto
					// "Media di una colonna"
					if (selezione.equals(sel[1])) {
						selezione = JOptionPane.showInputDialog(null, "Di quale colonna vuoi calcolare la media?", "Seleziona colonna", JOptionPane.INFORMATION_MESSAGE, null, selCol, selCol[0]);
						if (selezione!=null) {
							posizione=(int)selezione;
							if (colori) {
								colorReset();
							}
							bR.setEnabled(true);
							bR.setVisible(true);
							colori=true;
							for (i=0; i<n; i++) {
								num[i][posizione].setForeground(Color.green);
							}
							JOptionPane.showMessageDialog(null, "La media della colonna "+posizione+" è: "+mat.medColonna(posizione), "Risultato", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		}
		// Pulsante "È simmetrica?"
		if (e.getSource().equals(b[4])) {
			if (n==m) {
				if (mat.isSimmetrica()) {
					JOptionPane.showMessageDialog(null, "La matrice è simmetrica.", "È simmetrica?", JOptionPane.INFORMATION_MESSAGE, happy);
				} else {
					JOptionPane.showMessageDialog(null, "La matrice non è simmetrica.", "È simmetrica?", JOptionPane.INFORMATION_MESSAGE, sad);
				}
			} else {
				JOptionPane.showMessageDialog(null, "La matrice non è quadrata.", "È simmetrica?", JOptionPane.ERROR_MESSAGE, sad);
			}
		}

		// Pulsante "Massimo e minimo" 
		if (e.getSource().equals(b[5])) {
			if (colori) {
				colorReset();
			}
			bR.setEnabled(true);
			bR.setVisible(true);
			colori=true;
			JOptionPane.showMessageDialog(null, "Il numero più grande dell'intera matrice è: "+mat.max()+"\nIl numero più piccolo dell'intera matrice è: "+mat.min()+".", "Massimo e minimo", JOptionPane.INFORMATION_MESSAGE, happy);
			num[mat.getiMax()][mat.getjMax()].setForeground(Color.green);
			num[mat.getiMin()][mat.getjMin()].setForeground(Color.decode("#ff50ff"));
		}
		// Pulsante "Media totale"
		if (e.getSource().equals(b[6])) {
			JOptionPane.showMessageDialog(null, "La media di tutti i numeri è: "+mat.media(), "Media totale", JOptionPane.INFORMATION_MESSAGE, happy);
		}
		if (e.getSource().equals(b[7])) {
			JOptionPane.showMessageDialog(null, "La somma di tutti i numeri della matrice è: "+mat.somma(), "Media totale", JOptionPane.INFORMATION_MESSAGE, happy);
		}
		// Quando l'utente clicca sul pulsante "avanti"
		if (e.getSource().equals(av)) {
			// I pulsanti della schermata attuale diventeranno nascosti.
			nascondiPulsanti();
			// Si passa alla schermata successiva...
			schermata++;
			// E verranno visualizzati i pulsanti della schermata attuale.
			creaPulsanti();
		}
		// Stessa cosa per il pulsante "indietro".
		if (e.getSource().equals(in)) {
			nascondiPulsanti();
			schermata--;
			creaPulsanti();
		}
		// Pulsante "Ripristina colori"
		if (e.getSource().equals(bR)) {
			colorReset();
			bR.setEnabled(false);
			bR.setVisible(false);
		}
	}
	
	// Il giorno 10 Ottobre 2018, ho ritenuto opportuno creare una classe "Matrice"
	// e inserire tutti i metodi inerenti alla matrice lì.
	
	private void nascondiPulsanti() {
		int i=schermata*5;
		while (i<(schermata*5)+5 && i<nBottoni) {
			b[i].setVisible(false);
			b[i].setEnabled(false);
			i++;
		}
	}
	// Metodo per la creazione e la visualizzazione dei pulsanti
	// per ogni schermata.
	// Ogni schermata permette di inserire 5 pulsanti.
	private void creaPulsanti() {
		int spostamento=0;
		int i=schermata*5;
		while (i<(schermata*5)+5 && i<nBottoni) {
			b[i] = new JButton(new ImageIcon("btn"+i+".png"));
			b[i].setVisible(true);
			b[i].setBounds(581, 251+(60*spostamento), 213, 50);
			b[i].setBorder(null);
			b[i].setCursor(c);
			b[i].addActionListener(this);
			add(b[i]);
			i++;
			spostamento++;
		}
		// Dato che questo metodo è richiamato quando
		// Passo alla schermata precedente o successiva,
		// effettuo un controllo sul numero di schermata corrente.
		// Se la schermata corrente è uguale alla schermata massima
		// allora faccio scomparire il pulsante della freccia avanti,
		// dato che, appunto, le schermate sono finite
		if (schermata==nSchermate) {
			av.setVisible(false);
		} else {
			av.setVisible(true);
		}
		// Stessa cosa per l'inverso: se ci troviamo nella prima schermata
		// Il pulsante indietro scompare
		if (schermata==0) {
			in.setVisible(false);
		} else {
			in.setVisible(true);
		}
	}
	private void colorReset() {
		// Reset dei colori.
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (num[i][j].getForeground()!=Color.white) {
					num[i][j].setForeground(Color.white);
				}
			}
		}
		colori=false;
	}
	// Classe utilizzata per rendere i bottoni delle frecce trasparenti.
	public class FixedStateButtonModel extends DefaultButtonModel    {

        @Override
        public boolean isPressed() {
            return false;
        }

        @Override
        public boolean isRollover() {
            return false;
        }

        @Override
        public void setRollover(boolean b) {
            
        }

    }
}