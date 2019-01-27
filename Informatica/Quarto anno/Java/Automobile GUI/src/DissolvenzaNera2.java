import java.awt.Color;
import java.util.TimerTask;

import javax.swing.JLabel;
public class DissolvenzaNera2 extends TimerTask {
	private JLabel x;
	private Automobile a;
	public DissolvenzaNera2(JLabel x, Automobile a) {
		this.x=x;
		this.a=a;
	}
	@Override
	public void run() {
		for (int i=255; i>0; i--) {
			x.setForeground(new Color(255, 255, 255, i));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		a.setCambio(false);
	}

}
