import java.util.TimerTask;

public class MovimentoPersonaggio extends TimerTask{
	private GUI gioco;
	public MovimentoPersonaggio (GUI gioco){
		this.gioco=gioco;
	}

	public void run() {
		while (gioco.isDestra() && !gioco.isGiocMorto()) {
			if (gioco.getNav().getX()!=700) {
				gioco.getNav().setBounds(gioco.getNav().getX()+1, 350, 32, 27);
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
