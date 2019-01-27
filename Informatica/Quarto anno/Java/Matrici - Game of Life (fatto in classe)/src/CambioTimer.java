import java.util.TimerTask;

public class CambioTimer extends TimerTask{
	private Gioco gui;
	public CambioTimer (Gioco gui, RegoleGioco g){
		this.gui=gui;
	}

	public void run() {
		while (gui.tempo) {
			try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			gui.visualizzaRis();
			
		}
		
	}
}
