import java.awt.Color;
import java.util.TimerTask;

import javax.swing.JLabel;
public class DissolvenzaGrigia extends TimerTask {
	private JLabel x;
	private Automobile a;
	public DissolvenzaGrigia(JLabel x, Automobile a) {
		this.x=x;
		this.a=a;
	}
	@Override
	public void run() {
		for (int i=0; i<255; i++) {
			x.setForeground(new Color(165, 165, 165, i));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		a.setCambio(false);
	}
}
