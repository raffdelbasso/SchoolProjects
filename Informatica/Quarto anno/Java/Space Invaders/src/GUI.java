import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.TimerTask;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class GUI extends JFrame implements KeyListener{
	private ImageIcon sfondo = new ImageIcon("sfondo.gif");
	private ImageIcon expl = new ImageIcon("expl.gif");
	private boolean sparo=false;
	private boolean colpito=false;
	private boolean avvio=false;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon coppe[] = new ImageIcon[3];
	private ImageIcon nemici[] = new ImageIcon[11];
	private TimerTask d;
	private TimerTask dShot;
	public TimerTask dShotEn;
	private TimerTask mov;
	private TimerTask carica;
	private Timer t = new Timer();
	private Timer t2 = new Timer();
	private Timer t3 = new Timer();
	private Timer t4 = new Timer();
	private Timer t5 = new Timer();
	private int punteggio=-1;
	private int vite=3;
	private boolean tasto=false;
	private boolean morto = false;
	private boolean giocMorto = false;
	private boolean sinistra;
	private boolean destra;
	private boolean finito=false;
	private Clip c;
	private boolean menu=true;
	private JLabel en = new JLabel();
	private JLabel nVite = new JLabel("Vite rimanenti: 3");
	private JLabel nav = new JLabel(new ImageIcon("nav.png"));
	private JLabel shot = new JLabel(new ImageIcon("shot.png"));
	private JLabel shotNem = new JLabel(new ImageIcon("shot.png"));
	private JLabel nRimanenti = new JLabel();
	private JLabel coppa = new JLabel();
	private Font myFont;
	private AudioInputStream input;
	public GUI() {
		creaSuono("null.wav");
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/font.ttf"))).deriveFont(Font.BOLD, 20);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		pack();
		setLocation((int)((screenSize.getWidth())/2)-(this.getWidth()/2), (int)((screenSize.getHeight())/2)-(this.getHeight()/2));
		setTitle("Space Invaders");
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		for (int i=0; i<11; i++) {
			nemici[i] = new ImageIcon("en"+i+".png");
		}
	}
	
	private void disegnaGUI() {
		carica = new Carica(this);
		t5.schedule(carica, 0);
	}

	public void keyPressed(KeyEvent ev) {
		if (ev.getKeyCode()==KeyEvent.VK_RIGHT && nav.getX()<700 && !giocMorto) {
			destra=true;
			mov = new MovimentoPersonaggio(this);
			t4.schedule(mov, 0);
			if (!sparo) {
				shot.setBounds(nav.getX()+14, 340, 4, 27);
			}
		}
		if (ev.getKeyCode()==KeyEvent.VK_LEFT && nav.getX()>100 && !giocMorto) {
			sinistra=true;
			mov = new MovimentoPersonaggio2(this);
			t4.schedule(mov, 0);
			if (!sparo) {
				shot.setBounds(nav.getX()+14, 340, 4, 27);
			}
		}
		if (ev.getKeyCode()==KeyEvent.VK_SPACE && !sparo && !menu && !finito && !giocMorto) {
			creaSuono("shot.wav");
			colpito=false;
			dShot = new Colpo(this);
			t2.schedule(dShot, 0);
		}
		if (ev.getKeyCode()==KeyEvent.VK_SPACE && menu) {
			disegnaGUI();
		}
	}

	public void keyReleased(KeyEvent ev) {
		if (ev.getKeyCode()==KeyEvent.VK_RIGHT) {
			destra=false;
		}
		if (ev.getKeyCode()==KeyEvent.VK_LEFT) {
			sinistra=false;
		}
	}
	
	public void nuovoNemico() {
		if (punteggio!=9) {
			en.setBounds(384, 50, 32, 27);
			en.setIcon(nemici[(int)(Math.random()*11)]);
			shot.setBounds(nav.getX()+14, 340, 4, 27);
			en.setVisible(true);
			morto=false;
			punteggio++;
			d = new Movimento(this);
			t.schedule(d, 0);
			nRimanenti.setText("Nemici rimanenti: "+(10-punteggio));
		} else {
			if (!finito) {
				finito=true;
				creaSuono("victory.wav");
				getContentPane().removeAll();
				setContentPane(new JLabel(new ImageIcon("vinto.jpg")));
				if (vite>0) {
					for (int i=0; i<3; i++) {
						coppe[i] = new ImageIcon("cup"+i+".png");
					}
					coppa.setBounds(364, 277, 92, 123);
					coppa.setIcon(coppe[vite-1]);
					coppa.setVisible(true);
					add(coppa);
				}
				getContentPane().revalidate();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
				GUI g = new GUI();
			}
		}
	}
	public void morteGiocatore() {
		nav.setBounds(384, 350, 32, 27);
		nav.setIcon(new ImageIcon("nav.png"));
		shot.setBounds(nav.getX()+14, 340, 4, 27);
		shotNem.setBounds(en.getX()+14, 60, 4, 27);
		vite--;
		if (vite!=-1) {
			nVite.setText("Vite rimanenti: "+vite);
		} else {
			if (!finito) {
				finito=true;
				creaSuono("gameOver.wav");
				getContentPane().removeAll();
				setContentPane(new JLabel(new ImageIcon("gameOver.jpg")));
				getContentPane().revalidate();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				dispose();
				GUI g = new GUI();
			}
		}
		giocMorto=false;
	}

	public void creaSuono(String nome) {
		try {
	        input = AudioSystem.getAudioInputStream(new File(nome).getAbsoluteFile());
	        c = AudioSystem.getClip();
	        c.open(input);
	    } catch(Exception ex) {
		ex.printStackTrace();
	    }
		c.start();
	}
	
	public void keyTyped(KeyEvent arg0) {

	}

	public ImageIcon getExpl() {
		return expl;
	}

	public boolean isSparo() {
		return sparo;
	}

	public void setSparo(boolean sparo) {
		this.sparo = sparo;
	}

	public boolean isColpito() {
		return colpito;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}
	
	public boolean isMorto() {
		return morto;
	}

	public void setColpito(boolean colpito) {
		this.colpito = colpito;
	}

	public JLabel getEn() {
		return en;
	}

	public void setEn(JLabel en) {
		this.en = en;
	}

	public JLabel getNav() {
		return nav;
	}

	public void setNav(JLabel nav) {
		this.nav = nav;
	}

	public JLabel getShot() {
		return shot;
	}

	public void setShot(JLabel shot) {
		this.shot = shot;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public JLabel getShotNem() {
		return shotNem;
	}

	public void setShotNem(JLabel shotNem) {
		this.shotNem = shotNem;
	}

	public boolean isAvvio() {
		return avvio;
	}

	public void setAvvio(boolean avvio) {
		this.avvio = avvio;
	}

	public Timer getT3() {
		return t3;
	}

	public void setT3(Timer t3) {
		this.t3 = t3;
	}

	public TimerTask getdShotEn() {
		return dShotEn;
	}

	public void setdShotEn(TimerTask dShotEn) {
		this.dShotEn = dShotEn;
	}

	public boolean isTasto() {
		return tasto;
	}

	public void setDestra(boolean tasto) {
		this.tasto = tasto;
	}

	public boolean isGiocMorto() {
		return giocMorto;
	}

	public void setGiocMorto(boolean giocMorto) {
		this.giocMorto = giocMorto;
	}

	public boolean isSinistra() {
		return sinistra;
	}

	public void setSinistra(boolean sinistra) {
		this.sinistra = sinistra;
	}

	public boolean isDestra() {
		return destra;
	}

	public boolean isFinito() {
		return finito;
	}

	public void setFinito(boolean finito) {
		this.finito = finito;
	}

	public JLabel getnVite() {
		return nVite;
	}

	public void setnVite(JLabel nVite) {
		this.nVite = nVite;
	}

	public JLabel getnRimanenti() {
		return nRimanenti;
	}

	public void setnRimanenti(JLabel nRimanenti) {
		this.nRimanenti = nRimanenti;
	}

	public Font getMyFont() {
		return myFont;
	}

	public void setMyFont(Font myFont) {
		this.myFont = myFont;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}
}
