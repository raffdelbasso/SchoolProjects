import java.awt.Color;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Carica extends TimerTask{
	GUI gioco;
	public Carica (GUI gioco){
		this.gioco=gioco;
		gioco.removeKeyListener(gioco);
	}

	public void run() {
		gioco.getContentPane().removeAll();
		gioco.setContentPane(new JLabel(new ImageIcon("loading.jpg")));
		gioco.getContentPane().revalidate();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gioco.getContentPane().removeAll();
		gioco.setContentPane(new JLabel(new ImageIcon("sfondo.jpg")));
		gioco.getContentPane().revalidate();
		gioco.getNav().setBounds(384, 350, 32, 27);
		gioco.add(gioco.getNav());
		gioco.getShot().setBounds(gioco.getNav().getX()+14, 340, 4, 27);
		gioco.getShot().setVisible(false);
		gioco.add(gioco.getShot());
		gioco.nuovoNemico();
		gioco.add(gioco.getEn());
		gioco.getShotNem().setBounds(gioco.getEn().getX()+14, 60, 4, 27);
		gioco.getShotNem().setVisible(false);
		gioco.add(gioco.getShotNem());
		gioco.getnRimanenti().setBounds(10, 10, 300, 25);
		gioco.getnRimanenti().setForeground(Color.white);
		gioco.getnRimanenti().setFont(gioco.getMyFont());
		gioco.add(gioco.getnRimanenti());
		gioco.getnVite().setBounds(563, 10, 240, 25);
		gioco.getnVite().setForeground(Color.white);
		gioco.getnVite().setFont(gioco.getMyFont());
		gioco.add(gioco.getnVite());
		gioco.repaint();
		gioco.setMenu(false);
		gioco.addKeyListener(gioco);
	}
}