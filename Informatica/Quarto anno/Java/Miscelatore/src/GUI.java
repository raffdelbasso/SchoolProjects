import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Rappresenta la finestra che consente l'inserimento dei settaggi del miscelatore
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class GUI extends JFrame implements ActionListener {
	/**
	 * Oggetto di classe Dimension che rappresenta le dimensioni dello schermo
	 */
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Array di campi di testo
	 */
	private JTextField[] txt = new JTextField[5];
	/**
	 * Bottone utilizzato per cominciare la simulazione
	 */
	private JButton btn = new JButton(new ImageIcon("btn0.png"));
	/**
	 * Font di dimensione media
	 */
	private Font f = new Font("", 0, 25);
	/**
	 * Oggetto di classe Image usato per impostare l'icona dell'applicazione
	 */
	private Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
	/**
	 * Cursore: forma "mano"
	 */
	private Cursor c = new Cursor(HAND_CURSOR);
	/**
	 * Contatore usato in varie occasioni
	 */
	private int i;
	/**
	 * Costruttore dell'interfaccia
	 */
	public GUI() {
		disegnaInterfaccia();
	}
	
	/**
	 * Disegna la finestra con gli elementi e i settaggi
	 */
	private void disegnaInterfaccia() {
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("sfondo.jpg")));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2));
		setTitle("Miscelatore");
		for (i=0; i<5; i++) {
			txt[i] = new JTextField();
			txt[i].setBounds(525, 166+(75*i), 60, 33);
			txt[i].setFont(f);
			add(txt[i]);
		}
		btn.setBounds(653, 522, 120, 71);
		btn.addActionListener(this);
		btn.setBorder(null);
		btn.setRolloverIcon(new ImageIcon("btn1.png"));
		btn.setPressedIcon(new ImageIcon("btn2.png"));
		btn.setContentAreaFilled(false);
		btn.setCursor(c);
		add(btn);
		repaint();
		setVisible(true);
	}
	
	/**
	 * Gestore degli eventi derivanti dai click del mouse
	 */
	public void actionPerformed(ActionEvent ev) {
		if (!controllaCampi()) {
			dispose();
			Tubo tub = new Tubo(Integer.valueOf(txt[0].getText()), Float.valueOf(txt[2].getText()));
			Miscelatore misc = new Miscelatore(Float.valueOf(txt[3].getText()), tub);
			Dispositivo disp = new Dispositivo(Integer.valueOf(txt[1].getText()), tub, misc);
			TextArea a = new TextArea(50, 15);
			PannelloComandi pc = new PannelloComandi(Integer.valueOf(txt[4].getText()), disp, misc, tub, a);
			GUI2 msg = new GUI2(pc);
		}
	}
	
	/**
	 * Verifica se una stringa data rappresenta un float oppure no
	 * @param x Stringa da controllare
	 * @return True: la stringa rappresenta un float. False: la stringa non rappresenta un float.
	 */
	private boolean isFloat(String x) {
		boolean bool = true;
		int i=0;
		while (i<x.length() && bool) {
			if ((x.charAt(i)<'0' || x.charAt(i)>'9') && x.charAt(i)!='.') {
				bool=false;
			}
			i++;
		}
		return bool;
	}
	
	/**
	 * Verifica se una stringa data rappresenta un intero oppure no
	 * @param x Stringa da controllare
	 * @return True: la stringa rappresenta un intero. False: la stringa non rappresenta un intero.
	 */
	private boolean isIntero(String x) {
		boolean bool = true;
		int i=0;
		while (i<x.length() && bool) {
			if (x.charAt(i)<'0' || x.charAt(i)>'9') {
				bool=false;
			}
			i++;
		}
		return bool;
	}
	
	/**
	 * Metodo che controlla la validità dei dati inseriti dall'utente
	 * I valori devono essere tutti positivi e, come da traccia, "Le temperature devono variare nell’intervallo fra 0 e 99.9 gradi centigradi e i flussi nell’intervallo fra 0 e 50 litri al secondo".
	 * 
	 * @return True: uno o più errori. False: nessun errore.
	 */
	private boolean controllaCampi() {
        boolean errore=false;
        for (i=0; i<5; i++) {
            txt[i].setBackground(Color.white);
            if (i==0 || i==4) {
                if (txt[i].getText().isEmpty() || !isIntero(txt[i].getText())) {
                    txt[i].setBackground(Color.decode("#ff9393"));
                    errore=true;
                }
            } else {
                if (txt[i].getText().isEmpty() || !isFloat(txt[i].getText())) {
                    txt[i].setBackground(Color.decode("#ff9393"));
                    errore=true;
                }
            }
        }
        if (txt[0].getBackground()==Color.white) {
        	if (Integer.valueOf(txt[0].getText())>50 || Integer.valueOf(txt[0].getText())<=0) {
                txt[0].setBackground(Color.decode("#ff9393"));
                errore=true;
            }
        }
        if (txt[2].getBackground()==Color.white) {
            if (Float.valueOf(txt[2].getText())>Float.valueOf("99.9") || Float.valueOf(txt[2].getText())<=Float.valueOf("0")) {
                txt[2].setBackground(Color.decode("#ff9393"));
                errore=true;
            }
        }
        if (txt[3].getBackground()==Color.white) {
            if (Float.valueOf(txt[3].getText())>50 || Float.valueOf(txt[3].getText())<=Float.valueOf("0")) {
                txt[3].setBackground(Color.decode("#ff9393"));
                errore=true;
            }
        }
        if (txt[4].getBackground()==Color.white) {
            if (Integer.valueOf(txt[4].getText())>50 || Integer.valueOf(txt[4].getText())<=0) {
                txt[4].setBackground(Color.decode("#ff9393"));
                errore=true;
            }
        }
        return errore;
    }
}
