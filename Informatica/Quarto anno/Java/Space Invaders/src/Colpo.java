import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Colpo extends TimerTask{
	private GUI gioco;
	public Colpo (GUI gioco){
		this.gioco=gioco;
	}

	public void run() {
		gioco.getShot().setVisible(true);
		while (!gioco.isMorto() && gioco.getShot().getY()>-25 && !gioco.isColpito()) {
			gioco.setSparo(true);
			gioco.getShot().setBounds(gioco.getShot().getX(), gioco.getShot().getY()-1, 4, 27);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gioco.getShot().getBounds().intersects(gioco.getEn().getBounds())) {
				gioco.setColpito(true);
				gioco.setMorto(true);
				gioco.getEn().setIcon(gioco.getExpl());
				gioco.creaSuono("exp.wav");
				gioco.getShot().setVisible(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gioco.nuovoNemico();
			}
		}
		gioco.getShot().setVisible(false);
		gioco.setSparo(false);
		gioco.getShot().setBounds(gioco.getNav().getX()+14, 340, 4, 27);
	}
}
