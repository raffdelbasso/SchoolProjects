import java.util.TimerTask;

import javax.swing.JLabel;
public class CambioPosizioni extends TimerTask {
	private JLabel xS, xC, xG;
	public CambioPosizioni(JLabel xS, JLabel xC, JLabel xG) {
		this.xS=xS;
		this.xC=xC;
		this.xG=xG;
	}
	@Override
	public void run() {
		//Sopra passa al centro
		xS.setBounds(434, 333, 300, 100);
		//Centro passa sotto
		xC.setBounds(432, 379, 300, 100);
		//Sotto passa sopra
		xG.setBounds(427, 287, 300, 100);
	}

}
