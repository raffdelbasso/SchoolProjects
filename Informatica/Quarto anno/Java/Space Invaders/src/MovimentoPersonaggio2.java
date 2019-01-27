import java.util.TimerTask;

public class MovimentoPersonaggio2 extends TimerTask{
	private GUI gioco;
	public MovimentoPersonaggio2 (GUI gioco){
		this.gioco=gioco;
	}

	public void run() {
		while (gioco.isSinistra() && !gioco.isGiocMorto()) {
			if (gioco.getNav().getX()!=100) {
				gioco.getNav().setBounds(gioco.getNav().getX()-1, 350, 32, 27);
				if (!gioco.isSparo()) {
					gioco.getShot().setBounds(gioco.getNav().getX()+14, 340, 4, 27);
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
