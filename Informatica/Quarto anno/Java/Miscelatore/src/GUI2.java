import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Rappresenta la finestra che consente di resettare le impostazioni o di uscire
 * e mostra il pannello che visualizza i dati di output.
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class GUI2 extends JFrame implements ActionListener {
	/**
	 * Oggetto di classe Dimension che rappresenta le dimensioni dello schermo
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Cursore: forma "mano"
	 */
	private Cursor c = new Cursor(HAND_CURSOR);
	/**
	 * Bottone utilizzato per resettare le impostazioni
	 */
	private JButton btn1 = new JButton(new ImageIcon("btn3.png"));
	/**
	 * Bottone utilizzato per uscire dall'applicazione
	 */
	private JButton btn2 = new JButton(new ImageIcon("btn4.png"));
	/**
	 * Frame che contiene un pannello per inserire i dati di input,
	 * e un'area di testo per visualizzare i dati di output
	 */
	private Frame f = new Frame("Simulazione");
	/**
	 * Pannello comandi utilizzata per l'inserimento dei dati di input
	 */
	private PannelloComandi pc;
	/**
	 * Icona della finestra principale
	 */
	private Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	/**
	 * Icona della finestra di input/output
	 */
	private Image icon2 = Toolkit.getDefaultToolkit().getImage("logo2.png");
	/**
	 * Costruttore dell'interfaccia
	 * 
	 * @param pc Pannello dei comandi da inserire nella finestra
	 */
	public GUI2(PannelloComandi pc) {
		this.pc=pc;
		disegnaFinestre();
	}
	
	/**
	 * Disegna le finestre con gli elementi e i settaggi
	 */
	private void disegnaFinestre() {
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("sfondo2.jpg")));
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2)-100);
		setTitle("Miscelatore");
		btn1.setBounds(26, 170, 224, 72);
		btn1.addActionListener(this);
		btn1.setBorder(null);
		btn1.setPressedIcon(new ImageIcon("btn3(2).png"));
		btn1.setContentAreaFilled(false);
		btn1.setCursor(c);
		btn2.setBounds(252, 170, 224, 72);
		btn2.addActionListener(this);
		btn2.setBorder(null);
		btn2.setPressedIcon(new ImageIcon("btn4(2).png"));
		btn2.setContentAreaFilled(false);
		btn2.setCursor(c);
		add(btn1);
		add(btn2);
		setVisible(true);
		pc.getA().setText("sec.\tFF\tTF\tFC\tTC\tT\tTM\n");
		f.setResizable(false);
		f.setIconImage(icon2);
		f.add(pc, "West");
		f.add(pc.getA(), "Center");
		f.setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2)+230);
		f.setSize (520, 200);
		f.setVisible (true);
	}
	/**
	 * Gestore degli eventi
	 */
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource().equals(btn1)) {
			dispose();
			f.dispose();
			GUI g = new GUI();
		} else {
			System.exit(0);
		}
	}
}
