import java.util.TimerTask;

import javax.swing.ImageIcon;

public class ColpoNemico extends TimerTask{
	private GUI gioco;
	public ColpoNemico (GUI gioco){
		this.gioco=gioco;
	}

	public void run() {
		boolean fine=false;
		gioco.creaSuono("shot.wav");
		while (gioco.getShotNem().getY()<400 && !fine ) {
			gioco.getShotNem().setBounds(gioco.getShotNem().getX(), gioco.getShotNem().getY()+1, 4, 27);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gioco.getShotNem().getBounds().intersects(gioco.getNav().getBounds()) && !gioco.isFinito()) {
				fine=true;
				gioco.setDestra(false);
				gioco.setSinistra(false);
				gioco.setGiocMorto(true);
				gioco.creaSuono("exp.wav");
				gioco.getNav().setIcon(gioco.getExpl());
				gioco.creaSuono("exp.wav");
				gioco.getShotNem().setVisible(false);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gioco.morteGiocatore();
			}
		}
		gioco.getShotNem().setBounds(gioco.getEn().getX()+14, 60, 4, 27);
		gioco.getShotNem().setVisible(false);
		gioco.setAvvio(false);
	}
}
