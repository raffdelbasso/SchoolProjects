import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Rappresenta il pannello che gestisce l'inserimento dei dati di input e la visualizzazione dei dati di output, effettuando i calcoli presenti nei metodi delle classi Dispositivo, Miscelatore e Tubo
 * 
 * @author De Santis Giuseppe, Del Basso Raffaele, Maselli Sergio
 * @version 1.0
 */
public class PannelloComandi extends JPanel implements ActionListener {
	/**
	 * Istanti della durata della simulazione
	 */
	private int N;
	/**
	 * Oggetto di classe Dispositivo usato per i calcoli
	 */
	private Dispositivo disp;
	/**
	 * Oggetto di classe Miscelatore usato per i calcoli
	 */
	private Miscelatore misc;
	/**
	 * Oggetto di classe Tubo usato per i calcoli
	 */
	private Tubo tub;
	/**
	 * Area di testo che consente di inserire i dati di input dell'istante corrente
	 */
	private TextArea a;
	/**
	 * Contatore che indica l'istante corrente
	 */
	private int tempo;
	/**
	 * Etichetta che indica l'istante corrente e, se la simulazione è terminata, visualizza "FINE"
	 */
	private JLabel titolo;
	/**
	 * Bottone che consente di passare all'istante successivo
	 */
	private JButton succ;
	/**
	 * Campo di testo che consente di inserire il valore del flusso di acqua fredda
	 */
	private JTextField valFF;
	/**
	 * Campo di testo che consente di inserire il valore della temperatura dell'acqua fredda
	 */
	private JTextField valTF;
	/**
	 * Campo di testo che consente di inserire il valore della temperatura dell'acqua calda
	 */
	private JTextField valTC;
	/**
	 * Costruttore del pannello comandi che setta gli attributi e disegna il pannello
	 *
	 * @param N
	 * @param disp
	 * @param misc
	 * @param tub
	 * @param a
	 */
	public PannelloComandi(int N, Dispositivo disp, Miscelatore misc, Tubo tub, TextArea a) {
		this.N = N;
		this.disp = disp;
		this.misc = misc;
		this.tub = tub;
		this.a = a;
		disegnaPannello();
	}
	
	/**
	 * Disegna il pannello e i suoi elementi
	 */
	private void disegnaPannello() {
		tempo = 1;
		titolo = new JLabel("TEMPO "+tempo);
		valFF = new JTextField(3);
		valTF = new JTextField(3);
		valTC = new JTextField(3);
		succ = new JButton("Successivo");
		succ.addActionListener(this);
		setLayout(new GridLayout(4,1));
		add(titolo);
		add(new Label("Valore FF:", Label.CENTER));
		add(valFF);
		add(new Label("Valore TF:", Label.CENTER));
		add(valTF);
		add(new Label("Valore TC:", Label.CENTER));
		add(valTC);
		add(succ);
	}
	
	/**
	 * Gestore degli eventi
	 */
	public void actionPerformed(ActionEvent e) {
		if (!controllaCampi()) {
			misc.setFF(Float.valueOf(valFF.getText()));
			misc.setTF(Float.valueOf(valTF.getText()));
			misc.setTC(Float.valueOf(valTC.getText()));
			disp.regolaFC();
			misc.miscela();
			mostraValori();
			tempo++;
			titolo.setText("TEMPO "+tempo);
			if (tempo > N) {
				succ.setEnabled(false);
				titolo.setText("FINE.");
			}
		}
	}
	
	/**
	 * Mostra i valori nell'area di testo
	 */
	private void mostraValori() {
		a.append(tempo+"\t");
		a.append(arrotonda(misc.getFF())+"\t");
		a.append(arrotonda(misc.getTF())+"\t");
		a.append(arrotonda(misc.getFC())+"\t");
		a.append(arrotonda(misc.getTC())+"\t");
		a.append(arrotonda(tub.misuraT())+"\t");
		a.append(arrotonda(misc.TM())+"\n");
	}
	
	/**
	 * Arrotonda il valore passato in input alla prima cifra decimale
	 * 
	 * @param f Numero da arrotondare
	 * @return il numero arrotondato
	 */
	private float arrotonda(float f) {
		return (float) (Math.round(f*10)) / 10;
	}
	
	/**
	 * Metodo che controlla la validità dei dati inseriti dall'utente:
	 * "Le temperature devono variare nell’intervallo fra 0 e 99.9 gradi centigradi e i flussi nell’intervallo fra 0 e 50 litri al secondo".
	 * 
	 * @return True: uno o più errori. False: nessun errore.
	 */
	private boolean controllaCampi() {
		valFF.setBackground(Color.white);
		valTF.setBackground(Color.white);
		valTC.setBackground(Color.white);
		boolean errore=false;
		if (!isFloat(valFF.getText()) || valFF.getText().isEmpty()) {
			valFF.setBackground(Color.decode("#ff9393"));
			errore=true;
		} else {
			if (Float.valueOf(valFF.getText())>50 || Float.valueOf(valFF.getText())<=0) {
	            valFF.setBackground(Color.decode("#ff9393"));
	            errore=true;
	        }
		}
		if (!isFloat(valTF.getText()) || valTF.getText().isEmpty()) {
			valTF.setBackground(Color.decode("#ff9393"));
			errore=true;
		} else {
			if (Float.valueOf(valTF.getText())>Float.valueOf("99.9") || Float.valueOf(valTF.getText())<=0) {
	            valTF.setBackground(Color.decode("#ff9393"));
	            errore=true;
	        }
		}
		if (!isFloat(valTC.getText()) || valTC.getText().isEmpty()) {
			valTC.setBackground(Color.decode("#ff9393"));
			errore=true;
		} else {
			if (Float.valueOf(valTC.getText())>Float.valueOf("99.9") || Float.valueOf(valTC.getText())<=0) {
	            valTC.setBackground(Color.decode("#ff9393"));
	            errore=true;
	        }
		}
		return errore;
	}
	
	/**
	 * Verifica se una stringa data rappresenta un float oppure no
	 * @param x Stringa da controllare
	 * @return True: la stringa rappresenta un float. False: la stringa non rappresenta un float.
	 */
	public boolean isFloat(String x) {
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
	
	public TextArea getA() {
		return a;
	}
}