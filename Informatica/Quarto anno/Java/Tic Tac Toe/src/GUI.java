import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
public class GUI extends JFrame implements ActionListener {
	private ImageIcon sfondo = new ImageIcon("sfondo.jpg");
	private ImageIcon ness = new ImageIcon("-100.jpg");
	private JButton vuoto[][] = new JButton[3][3];
	private Cursor c = new Cursor(HAND_CURSOR);
	private JLabel g = new JLabel("G1: Tocca a te!");
	private int i, j;
	private Font myFont;
	private int vincitore;
	private ImageIcon[] segno = new ImageIcon[2];
	private ImageIcon draw = new ImageIcon("draw.png");
	private ImageIcon win = new ImageIcon("win.png");
	private ImageIcon rematch = new ImageIcon("rematch.png");
	private RegoleGioco gioco;
	public GUI() {
		segno[0] = new ImageIcon("1.jpg");
		segno[1] = new ImageIcon("2.jpg");
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/roboto.ttf"))).deriveFont(Font.PLAIN, 30);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		imposta();
	}
	private void imposta() {
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Tic Tac Toe");
		creaGUI();
	}
	public void actionPerformed(ActionEvent ev) {
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				if (ev.getSource().equals(vuoto[i][j]) && (gioco.getMat().getElem(i, j)==-100)) {
					vuoto[i][j].setIcon(segno[gioco.getGioc()-1]);
					vuoto[i][j].setCursor(null);
					gioco.nuovoTurno(i, j);
					aggiornaStato();
				}
			}
		}
	}
	
	public void aggiornaStato() {
		g.setText("G"+gioco.getGioc()+": tocca a te!");
		vincitore=gioco.vincita();
		if(vincitore!=-1) {
			int scelta;
			if (vincitore==10) {
				JOptionPane.showMessageDialog(null, "Pareggio!", "Pareggio", JOptionPane.INFORMATION_MESSAGE, draw);
			} else {
				JOptionPane.showMessageDialog(null, "Ha vinto il giocatore "+(vincitore+1)+"!", "Vittoria", JOptionPane.INFORMATION_MESSAGE, win);
			}
			scelta = JOptionPane.showConfirmDialog(null, "Vuoi giocare un'altra partita?", "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, rematch);
			if (scelta==0) {
				gioco=null;
				resettaBtn();
				creaGUI();
			} else {
				System.exit(0);
			}
		}
	}
	
	public void resettaBtn() {
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				remove(vuoto[i][j]);
				vuoto[i][j]=null;
			}
		}
	}
	
	public void creaGUI() {
		gioco = new RegoleGioco();
		for (i=0; i<3; i++) {
			for (j=0; j<3; j++) {
				vuoto[i][j] = new JButton(ness);
				vuoto[i][j].setBounds(15+(j+1)*79, 35+i+(i+1)*75, 63, 65);
				vuoto[i][j].setVisible(true);
				vuoto[i][j].setCursor(c);
				vuoto[i][j].setBorderPainted(false);
				vuoto[i][j].addActionListener(this);
				add(vuoto[i][j]);
				gioco.getMat().setElem(i, j, -100);
				repaint();
			}
		}
		g.setText("G1: tocca a te!");
		g.setForeground(Color.white);
		g.setBounds(105, 311, 300, 100);
		g.setFont(myFont);
		add(g);
	}
}