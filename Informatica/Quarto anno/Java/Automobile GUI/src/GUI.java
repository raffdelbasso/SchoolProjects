import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;
import java.util.Timer;
public class GUI extends JFrame implements ActionListener, KeyListener {
	public Automobile a=new Automobile();
	private ImageIcon sfondo = new ImageIcon ("Sfondo.jpg");
	private JLabel lblVel = new JLabel ();
	private JLabel lblCity = new JLabel();
	private JLabel lblEco = new JLabel();
	private JLabel lblSport = new JLabel();
	private JLabel lblC = new JLabel();
	private JLabel lblP = new JLabel();
	private JButton play = new JButton(new ImageIcon("play.png"));
	private JButton inf = new JButton(new ImageIcon("info.png"));
	private JButton pau = new JButton(new ImageIcon("stop.png"));
	private JButton f1 = new JButton(new ImageIcon("freccia1.png"));
	private JButton f2 = new JButton(new ImageIcon("freccia2.png"));
	private boolean err=false;
	public boolean cambio=false;
	boolean musica=false;
	boolean pausa=false;
	boolean corr=false;
	public String errore="Errore: i seguenti file sono corrotti. Si prega di rimuoverli o di controllarne la validità:\n";
	public int n;
	public Clip[] clip;
	public String per = "src/";
	public int i, canzoneAttiva=5;
	public AudioInputStream[] we;
	public Timer t = new Timer();
	public TimerTask d = new DissolvenzaBianca(lblVel, a);
	public TimerTask d2 = new DissolvenzaBianca(lblCity, a);
	public TimerTask d3 = new DissolvenzaGrigia(lblEco, a);
	public TimerTask d4 = new DissolvenzaGrigia(lblSport, a);
	public TimerTask d5;
	public GUI (int n) {
		this.n=n;
		if (n!=0) {
			we = new AudioInputStream[n];
			clip = new Clip[n];
			for (i=0; i<clip.length; i++) {
				per= "src/"+(String.valueOf((i+1)))+".wav";
				creaCanzoni(per, i);
			}
			if (corr) {
			    JOptionPane.showMessageDialog(null, errore);
				System.exit(0);
			}
		}
		disegnaGUI();
		aggiungiListener();
	}
	private void disegnaGUI() {
		FixedStateButtonModel b = new FixedStateButtonModel();
		Cursor c = new Cursor(HAND_CURSOR);
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("led.ttf");
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(72f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("myr.ttf");
		Font font2 = null;
		try {
			font2 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(22f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable (false);
		pack();
		setTitle("Automobile");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		if (n!=0) {
			play.setVisible(true);
			play.setBounds(154, 343, 18, 20);
			play.setBorder(null);
			play.setCursor(c);
			pau.setVisible(false);
			pau.setBounds(154, 343, 19, 20);
			pau.setBorder(null);
			pau.setBackground(Color.black);
			pau.setCursor(c);
			f1.setVisible(true);
			f1.setBounds(112, 344, 29, 20);
			f1.setBorder(null);
			f1.setCursor(c);
			f2.setVisible(true);
			f2.setBounds(186, 343, 29, 20);
			f2.setBorder(null);
			f2.setCursor(c);
			lblC.setText("Canzone in esecuzione: ");
			lblC.setFont(font2);
			lblC.setBounds(15, 260, 300, 300);
			lblC.setVisible(false);
			lblC.setForeground(Color.WHITE);
			lblP.setText("Pausa");
			lblP.setFont(font2);
			lblP.setBounds(135, 178, 300, 300);
			lblP.setVisible(false);
			lblP.setForeground(Color.WHITE);
			add(lblP);
			add(lblC);
			add(play);
			add(pau);
			add(f1);
			add(f2);
		} else {
			JLabel lblErr = new JLabel("Nessuna canzone trovata.");
			lblErr.setFont(font2);
			lblErr.setBounds(47, 230, 300, 300);
			lblErr.setVisible(true);
			lblErr.setForeground(Color.white);
			add(lblErr);
		}
		lblCity.setText("CITY");
		lblCity.setFont(font2);
		lblCity.setBounds(431, 285, 200, 200);
		lblCity.setForeground(Color.black);
		lblEco.setText("ECO");
		lblEco.setFont(font2);
		lblEco.setBounds(433, 242, 200, 200);
		lblEco.setForeground(Color.black);
		inf.setBounds(865, 465, 30, 30);
		inf.setFocusPainted(false);
		inf.setFocusable(false);
		inf.setBorder(null);
		inf.setModel(b);
		inf.setBackground(Color.black);
		inf.setCursor(c);
		lblSport.setText("SPORT");
		lblSport.setFont(font2);
		lblSport.setBounds(423, 328, 200, 200);
		lblSport.setForeground(Color.black);
		lblVel.setText(a.getV1()+""+a.getV2()+""+a.getV3());
		lblVel.setFont(font);
		lblVel.setBounds(403, 130, 300, 100);
		lblVel.setForeground(Color.black);
		add(lblVel);
		add(lblCity);
		add(inf);
		add(lblEco);
		add(lblSport);
		t.schedule(d, 1000);
		t.schedule(d2, 0);
		t.schedule(d3, 0);
		t.schedule(d4, 0);
		repaint();
	}
	public void aggiungiListener() {
		play.addActionListener(this);
		play.addKeyListener(this);
		pau.addActionListener(this);
		pau.addKeyListener(this);
		inf.addActionListener(this);
		f1.addActionListener(this);
		f1.addKeyListener(this);
		f2.addActionListener(this);
		f2.addKeyListener(this);
		addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		TimerTask f = new ScaloVelocita(a, lblVel);
		if ((e.getKeyChar() == 'e' || e.getKeyChar() == 'E') && a.getMod()!='E') {
			a.setVelMax(180);
			err=false;
			switch (a.getMod()) {
			case 'C':
				d2=new DissolvenzaNera(lblCity, a);
				d3=new DissolvenzaNera(lblEco, a);
				d4=new DissolvenzaNera(lblSport, a);
				d5=new CambioPosizioni(lblEco, lblCity, lblSport);
				break;
			case 'S':
				d3=new DissolvenzaNera(lblCity, a);
				d4=new DissolvenzaNera(lblEco, a);
				d2=new DissolvenzaNera(lblSport, a);
				d5=new CambioPosizioni(lblEco, lblCity, lblSport);
				break;
			}
			cambio=true;
			a.setMod('E');
			t.schedule(d2, 1);
			t.schedule(d4, 100);
			t.schedule(d3, 200);
			t.schedule(d5, 300);
			d2=new DissolvenzaBianca(lblEco, a);
			d3=new DissolvenzaGrigia(lblCity, a);
			d4=new DissolvenzaGrigia(lblSport, a);
			t.schedule(d2, 400);
			t.schedule(d3, 500);
			t.schedule(d4, 600);
			cambio=false;
		}
		if ((e.getKeyChar() == 'c' || e.getKeyChar() == 'C') && a.getMod()!='C') {
			a.setVelMax(80);
			switch (a.getMod()) {
			case 'E':
				d2=new DissolvenzaNera(lblCity, a);
				d3=new DissolvenzaNera(lblEco, a);
				d4=new DissolvenzaNera(lblSport, a);
				d5=new CambioPosizioni(lblCity, lblSport, lblEco);
				break;
			case 'S':
				d3=new DissolvenzaNera(lblCity, a);
				d4=new DissolvenzaNera(lblEco, a);
				d2=new DissolvenzaNera(lblSport, a);
				d5=new CambioPosizioni(lblCity, lblSport, lblEco);
				break;
			}
			cambio=true;
			a.setMod('C');
			t.schedule(d2, 1);
			t.schedule(d4, 100);
			t.schedule(d3, 200);
			t.schedule(d5, 300);
			d2=new DissolvenzaGrigia(lblEco, a);
			d3=new DissolvenzaBianca(lblCity, a);
			d4=new DissolvenzaGrigia(lblSport, a);
			t.schedule(d3, 400);
			t.schedule(d2, 500);
			t.schedule(d4, 600);
			cambio=false;
		}
		if ((e.getKeyChar() == 's' || e.getKeyChar() == 'S') && a.getMod()!='S') {
		switch (a.getMod()) {
		case 'C':
			d2=new DissolvenzaNera(lblCity, a);
			d3=new DissolvenzaNera(lblEco, a);
			d4=new DissolvenzaNera(lblSport, a);
			d5=new CambioPosizioni(lblSport, lblEco, lblCity);
			break;
		case 'E':
			d3=new DissolvenzaNera(lblCity, a);
			d4=new DissolvenzaNera(lblEco, a);
			d2=new DissolvenzaNera(lblSport, a);
			d5=new CambioPosizioni(lblSport, lblEco, lblCity);
			break;
		}
		cambio=true;
		a.setMod('S');
		t.schedule(d2, 1);
		t.schedule(d4, 100);
		t.schedule(d3, 200);
		t.schedule(d5, 300);
		d2=new DissolvenzaBianca(lblSport, a);
		d3=new DissolvenzaGrigia(lblCity, a);
		d4=new DissolvenzaGrigia(lblEco, a);
		t.schedule(d2, 400);
		t.schedule(d3, 500);
		t.schedule(d4, 600);
		cambio=false;
		}
		if (e.getKeyChar() == '1') {
			clip[0].start();
			canzoneAttiva=0;
			clip[1].close();
			clip[2].close();
			creaCanzoni("src/2.wav", 1);
			creaCanzoni("src/3.wav", 2);
			musica=true;
		}
		if (e.getKeyChar() == '2') {
			canzoneAttiva=1;
			clip[1].start();
			clip[0].close();
			clip[2].close();
			creaCanzoni("src/1.wav", 0);
			creaCanzoni("src/3.wav", 2);
			musica=true;
		}
		if (e.getKeyChar() == '3') {
			clip[2].start();
			canzoneAttiva=2;
			clip[0].close();
			clip[1].close();
			creaCanzoni("src/1.wav", 0);
			creaCanzoni("src/2.wav", 1);
			musica=true;
		}
		if (e.getKeyChar()=='p' && musica) {
			if (clip[canzoneAttiva].isActive()) {
				lblP.setForeground(new Color(255, 255, 255, 255));
				lblP.setText("Pausa");
				lblP.setBounds(135, 178, 300, 300);
				lblP.setVisible(true);
				d2=new DissolvenzaNera2(lblP, a);
				clip[canzoneAttiva].stop();
				t.schedule(d2, 500);
			} else {
				lblP.setForeground(new Color(255, 255, 255, 255));
				lblP.setText("Play");
				lblP.setBounds(143, 178, 300, 300);
				d2=new DissolvenzaNera2(lblP, a);
				clip[canzoneAttiva].start();
				t.schedule(d2, 500);
			}
		}
		if (e.getKeyChar() == 'a' || e.getKeyChar()=='A') {
			if (!err && !a.isCambio()) {
				a.accelera();
			}
		}
		if ((e.getKeyChar() == 'f' || e.getKeyChar()=='F') && (!(a.getV1()==0&&a.getV2()==0&&a.getV3()==0))) {
			if (!err && !a.isCambio()) {
				a.frena();
			}
		}
		if (!a.controlloVel()) {
			t.schedule(f, 0);
		}
		lblVel.setText(a.getV1()+""+a.getV2()+""+a.getV3());
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

	}
	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		lblC.setForeground(new Color(255, 255, 255, 255));
		if (e.getSource().equals(play)) {
			play.setVisible(false);
			pau.setVisible(true);
			for (i=0; i<clip.length; i++) {
				per="src/"+String.valueOf(i+1)+".wav";
				creaCanzoni(per, i);
			}
			canzoneAttiva=0;
			clip[canzoneAttiva].start();
			lblC.setText("Canzone in esecuzione: "+String.valueOf(canzoneAttiva+1)+".wav");
			musica=true;
		}
		if (e.getSource().equals(pau)) {
			play.setVisible(true);
			pau.setVisible(false);
			canzoneAttiva=0;
			for (i=0; i<clip.length; i++) {
				clip[i].close();
			}
			musica=false;
		}
		
		if (e.getSource().equals(inf)) {
			FinestraInfo info = new FinestraInfo();
			info.setVisible(true);			
		}
		
		if (e.getSource().equals(f1)) {
			if ((canzoneAttiva-1)>=0 && musica) {
				canzoneAttiva--;
				for (i=0; i<clip.length; i++) {
					per="src/";
					per+=(String.valueOf(i+1))+".wav";
					clip[i].close();
					creaCanzoni(per, i);
				}
				clip[canzoneAttiva].start();
				lblC.setText("Canzone in esecuzione: "+String.valueOf(canzoneAttiva+1)+".wav");
				musica=true;
			}
		}
		
		if (e.getSource().equals(f2)) {
			if ((canzoneAttiva+1)<clip.length && musica) {
				canzoneAttiva++;
				clip[canzoneAttiva].start();
				lblC.setText("Canzone in esecuzione: "+String.valueOf(canzoneAttiva+1)+".wav");
				for (i=0; i<clip.length; i++) {
					per="src/";
					if (i!=canzoneAttiva) {
						per+=(String.valueOf(i+1))+".wav";
						clip[i].close();
						creaCanzoni(per, i);
					}
				}
				musica=true;
			}
		}
		if (musica) {
			lblC.setVisible(true);
		} else lblC.setVisible(false);
	}
	
	
	public void creaCanzoni(String nome, int n) {
	    try {
	        we[n] = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        clip[n] = AudioSystem.getClip();
	        clip[n].open(we[n]);
	    } catch(Exception ex) {
	    	errore+=nome+"\n";
	    corr=true;
		ex.printStackTrace();
	    }
	}
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