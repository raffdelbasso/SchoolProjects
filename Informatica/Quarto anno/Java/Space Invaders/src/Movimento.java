import java.util.TimerTask;

public class Movimento extends TimerTask{
	private GUI gioco;
	public Movimento (GUI gioco){
		this.gioco=gioco;
	}

	public void run() {
		boolean indietro=false;
		int cas;
		while (!gioco.isMorto() && !gioco.isFinito()) {
			if (!indietro) {
				gioco.getEn().setBounds(gioco.getEn().getX()+1, 50, 32, 27);
				if (gioco.getEn().getX()==700) {
					indietro=true;
				}
			} else {
				gioco.getEn().setBounds(gioco.getEn().getX()-1, 50, 32, 27);
				if (gioco.getEn().getX()==100) {
					indietro=false;
				}
			}
			if (!gioco.isAvvio()) {
				cas= (int)(Math.random()*70);
				if (cas==0) {
					gioco.getShotNem().setBounds(gioco.getEn().getX()+14, 60, 4, 27);
					gioco.getShotNem().setVisible(true);
					gioco.setAvvio(true);
					gioco.dShotEn = new ColpoNemico(gioco);
					gioco.getT3().schedule(gioco.dShotEn, 0);
				}
			}
			try {
				Thread.sleep(10-(gioco.getPunteggio()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
