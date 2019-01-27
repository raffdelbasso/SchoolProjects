import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
public class GiocoT extends JFrame implements KeyListener{
	private final int n=15;
	private int x=13;
	private int y=7;
	private int xNem=7;
	private int yNem=7;
	private int nPassi=1;
	private int nPot=0;
	private int mov=0;
	private boolean fatto;
	private boolean movFatto;
	private boolean combattimento=false;
	private JLabel barraVita = new JLabel();
	private ImageIcon sfondo = new ImageIcon("sfondo2.jpg");
	private ImageIcon rockUL = new ImageIcon("rockUL.jpg");
	private ImageIcon rockU = new ImageIcon("rockU.jpg");
	private ImageIcon rockUR = new ImageIcon("rockUR.jpg");
	private ImageIcon rockR = new ImageIcon("rockR.jpg");
	private ImageIcon rockDR = new ImageIcon("rockDR.jpg");
	private ImageIcon rockDL = new ImageIcon("rockDL.jpg");
	private ImageIcon rockL = new ImageIcon("rockL.jpg");
	private ImageIcon rockD = new ImageIcon("rockD.jpg");
	private ImageIcon casella = new ImageIcon("casella.jpg");
	private ImageIcon vita = new ImageIcon("vita.jpg");
	private ImageIcon scudo = new ImageIcon("scudo.jpg");
	private ImageIcon forza = new ImageIcon("forza.jpg");
	private ImageIcon entr = new ImageIcon("entr.jpg");
	private ImageIcon exit = new ImageIcon("exit.jpg");
	private ImageIcon exB = new ImageIcon("exB.jpg");
	private ImageIcon pow = new ImageIcon("pow.jpg");
	private JLabel[][] floor = new JLabel[n][n];
	private JLabel info = new JLabel(new ImageIcon("info.png"));
	private JLabel att = new JLabel("");
	private JLabel dif = new JLabel("");
	private JLabel livello = new JLabel("");
	private JLabel vite = new JLabel("");
	private JLabel tAtt = new JLabel(new ImageIcon("attacco.png"));
	private boolean[][] ost = new boolean[n][n];
	private int liv;
	private Font myFont;
	private Marziano m;
	private Terrestre t;
	public GiocoT(Terrestre t, Marziano m, int liv) {
		this.t=t;
		this.m=m;
		this.liv=liv;
		disegna();
		creaLiv();
	}
	
	private void disegna() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Marziani VS Terrestri");
		addKeyListener(this);
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("src/f.ttf"))).deriveFont(Font.BOLD, 40);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		att.setText(String.valueOf(t.getAtt()));
		att.setBounds(800, 245, 50, 50);
		att.setForeground(Color.black);
		att.setFont(myFont);
		add(att);
		dif.setText(String.valueOf(t.getDif()));
		dif.setBounds(775, 315, 50, 50);
		dif.setForeground(Color.black);
		dif.setFont(myFont);
		add(dif);
		livello.setText(String.valueOf(String.valueOf(liv)));
		livello.setBounds(890, 400, 50, 50);
		livello.setForeground(Color.black);
		livello.setFont(myFont);
		add(livello);
		vite.setText(String.valueOf("x"+String.valueOf(t.getnVite())));
		vite.setBounds(735, 405, 100, 50);
		vite.setForeground(Color.black);
		vite.setFont(myFont);
		add(vite);
		tAtt.setBounds(77, 510, 428, 89);
		tAtt.setVisible(false);
		add(tAtt);
		barraVita.setIcon(new ImageIcon(String.valueOf(t.getVita())+".png"));
		barraVita.setBounds(730, 220, 184, 17);
		add(barraVita);
		info.setBounds(600, 50, 360, 460);
		add(info);
	}
	
	private void creaLiv() {
		int i, j;
		for (i=0; i<n; i++) {
			for (j=0; j<n; j++) {
				floor[i][j] = new JLabel(casella);
				floor[i][j].setBounds((j+1)*34, 14+(1*i)+(i+1)*30, 34, 31);
				floor[i][j].setVisible(true);
				add(floor[i][j]);
				ost[i][j] = false;
			}
		}
		for (i=0; i<n; i++) {
			ost[i][0]=true;
			floor[i][0].setIcon(rockL);
			ost[0][i]=true;
			floor[0][i].setIcon(rockU);
			ost[i][n-1]=true;
			floor[i][n-1].setIcon(rockR);
			ost[n-1][i]=true;
			floor[n-1][i].setIcon(rockD);
			
		}
		ost[0][n-1]=true;
		floor[0][n-1].setIcon(rockUR);
		ost[0][0]=true;
		floor[0][0].setIcon(rockUL);
		ost[n-1][0]=true;
		floor[n-1][0].setIcon(rockDL);
		ost[n-1][n-1]=true;
		floor[n-1][n-1].setIcon(rockDR);
		ost[14][7]=true;
		floor[14][7].setIcon(entr);
		ost[0][7]=true;
		floor[0][7].setIcon(exB);
		cambiaPosizioneT(x, y);
		cambiaPosizioneM(xNem, yNem);
		int k=0;
		while (k<40) {
			if(creaObst()) {
				k++;
			}
		}
	}
	private void cambiaPosizioneT(int x, int y) {
		floor[x][y].setIcon(new ImageIcon(t.getOrientamento()+t.getCol()+t.getSpada()+".jpg"));
	}
	
	private void cambiaPosizioneM(int x, int y) {
		floor[x][y].setIcon(new ImageIcon(m.getOrientamento()+m.getCol()+"M.jpg"));
	}
	
	private boolean creaObst() {
		int nObst;
		int x, y;
		x = (int) (Math.random()*(n-2))+1;
		y = (int) (Math.random()*(n-2))+1;
		nObst = (int) Math.round((Math.random()*1));
		if ((x==7 && y==7) || (x==13 && y==7) || (x==13 && y==6) || (x==13 && y==8) || (x==12 && y==7) || (x==1 && y==7) || (x==1 && y==6) || (x==1 && y==8) || (x==2 && y==7)) {
			return false;
		} else {
			ost[x][y] = true;
			floor[x][y].setIcon(new ImageIcon("obst"+String.valueOf(nObst)+".jpg"));
			return true;
		}
	}
	
	public void keyPressed(KeyEvent ev) {
		movFatto=false;
		if (!combattimento) {
			t.setSpada('N');
			if (ev.getKeyCode()==KeyEvent.VK_LEFT) {
				if (!ost[x][y-1] && t.getOrientamento().equals("sinistra")) {
						floor[x][y].setIcon(casella);
						y--;
						nPassi++;
						movFatto=true;
				} else {
					t.setOrientamento("sinistra");
				}
			}
			if (ev.getKeyCode()==KeyEvent.VK_UP) {
				if (!ost[x-1][y] && t.getOrientamento().equals("su")) {
					floor[x][y].setIcon(casella);
					x--;
					nPassi++;
					movFatto=true;
				} else {
					if (floor[x-1][y].getIcon()==exit && t.getOrientamento().equals("su")) {
						dispose();
						GiocoT g = new GiocoT(t, new Marziano("giu", 1, 100, 5+(liv*4), 5+(liv*4), 0, 'N'), liv+1);
					} else {
						t.setOrientamento("su");
					}
				}
			}
			if (ev.getKeyCode()==KeyEvent.VK_RIGHT) {
				if (!ost[x][y+1] && t.getOrientamento().equals("destra")) {
					floor[x][y].setIcon(casella);
					y++;
					nPassi++;
					movFatto=true;
				} else {
					t.setOrientamento("destra");
				}
			}
			if (ev.getKeyCode()==KeyEvent.VK_DOWN) {
				if (!ost[x+1][y] && t.getOrientamento().equals("giu")) {
					floor[x][y].setIcon(casella);
					x++;
					nPassi++;
					movFatto=true;
				} else {
					t.setOrientamento("giu");
				}
			}
			controllaPot();
			fatto=false;
			muoviNemico();
			if ((x+1==xNem && y==yNem) && t.getOrientamento().equals("giu")) {
				t.setSpada('S');
			}
			if ((x==xNem && y-1==yNem) && t.getOrientamento().equals("sinistra")) {
				t.setSpada('S');
			}
			if ((x-1==xNem && y==yNem) && t.getOrientamento().equals("su")) {
				t.setSpada('S');
			}
			if ((x==xNem && y+1==yNem) && t.getOrientamento().equals("destra")) {
				t.setSpada('S');
			}
			cambiaPosizioneT(x, y);
			controllaPos();
			if (nPassi==10 && nPot<4) {
				mostraPotenziamento();
				nPassi=0;
				nPot++;
			}
			if (t.getSpada()=='S') {
				tAtt.setVisible(true);
			} else {
				tAtt.setVisible(false);
			}
		}
		if (ev.getKeyCode()==KeyEvent.VK_ENTER && combattimento) {
			combattimento=false;
			m.attaccaTerrestre(t);
			if (m.getVita()<=0) {
				floor[xNem][yNem].setIcon(casella);
				xNem=0;
				yNem=0;
				floor[0][7].setIcon(exit);
				tAtt.setVisible(false);
			}
			if (t.getVita()<=0) {
				x=13;
				y=7;
				t.setOrientamento("su");
				t.setnVite(t.getnVite()-1);
				if (t.getnVite()<0) {
					dispose();
					JOptionPane.showMessageDialog(null, "Game over.");
				}
				if (t.getVita()==0 && m.getVita()!=0) {
					cambiaPosizioneM(xNem, yNem);
				}
				floor[0][0].setIcon(rockUL);
				t.setVita(100);
				cambiaPosizioneT(x, y);
			}
		}
		if (ev.getKeyCode()==KeyEvent.VK_SPACE && t.getSpada()=='S') {
			t.attaccaMarziano(m);
			if (m.getVita()<=0) {
				floor[xNem][yNem].setIcon(casella);
				xNem=0;
				yNem=0;
				floor[0][7].setIcon(exit);
				tAtt.setVisible(false);
			}
			if (t.getVita()<=0) {
				floor[x][y].setIcon(casella);
				x=13;
				y=7;
				t.setOrientamento("su");
				t.setnVite(t.getnVite()-1);
				if (t.getnVite()<0) {
					dispose();
					JOptionPane.showMessageDialog(null, "Game over.");
				}
				t.setVita(100);
				cambiaPosizioneT(x, y);
			}
		}
		aggiornaStatistiche();
	}

	private void aggiornaStatistiche() {
		vite.setText("x"+String.valueOf(t.getnVite()));
		dif.setText(String.valueOf(t.getDif()));
		att.setText(String.valueOf(t.getAtt()));
		barraVita.setIcon(new ImageIcon(String.valueOf(t.getVita())+".png"));
	}
	
	private void muoviNemico() {
		if (movFatto && m.getVita()>0) {
			if (x!=xNem || y!=yNem) {
				floor[xNem][yNem].setIcon(casella);
			}
			while(!fatto) {
				mov = (int) (Math.random()*4);
				switch (mov) {
				case 0:
					if (!ost[xNem][yNem-1]) {
						fatto=true;
						yNem--;
						m.setOrientamento("sinistra");
					}
					break;
				case 1:
					if (!ost[xNem+1][yNem]) {
						fatto=true;
						xNem++;
						m.setOrientamento("giu");
					}
					break;
				case 2:
					if (!ost[xNem][yNem+1]) {
						fatto=true;
						yNem++;
						m.setOrientamento("destra");
					}
					break;
				case 3:
					if (!ost[xNem-1][yNem]) {
						fatto=true;
						xNem--;
						m.setOrientamento("su");
					}
					break;
				}
			}
			cambiaPosizioneM(xNem, yNem);
		}
	}
	
	private void controllaPos() {
		if (x==xNem && y==yNem) {
			floor[x][y].setIcon(pow);
			combattimento=true;
			m.attaccaTerrestre(t);
		}
	}
	
	private void controllaPot() {
		if (floor[x][y].getIcon()==vita) {
			if (t.getVita()!=100) {
				t.setVita(t.getVita()+25);
			}
			t.setCol(1);
		}
		if (floor[x][y].getIcon()==scudo) {
			t.setDif(t.getDif()+5);
			t.setCol(2);
			
		}
		if (floor[x][y].getIcon()==forza) {
			t.setAtt(t.getAtt()+5);
			t.setCol(3);
		}
	}
	
	private void mostraPotenziamento() {
		int n, x, y;
		do {
			x = (int) (Math.random()*(this.n-2))+1;
			y = (int) (Math.random()*(this.n-2))+1;
		} while (ost[x][y] || (x==this.x && y==this.y) || (x==xNem && y==yNem) );
		n = (int) (Math.random()*3);
		switch (n) {
		case 0:
			floor[x][y].setIcon(vita);
			break;
		case 1:
			floor[x][y].setIcon(scudo);
			break;
		case 2:
			floor[x][y].setIcon(forza);
			break;
		}
	}
	
	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
