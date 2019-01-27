import java.util.TimerTask;

import javax.swing.JLabel;
public class ScaloVelocita extends TimerTask {
	private Automobile a;
	private JLabel x;
	public ScaloVelocita(Automobile a, JLabel x) {
		this.a=a;
		this.x=x;
	}
	@Override
	public void run() {
		int n1=0, n2=0, n3=0;
		switch (a.getMod()) {
		case 'E':
			n1=1;
			n2=8;
			n3=0;
			break;
		case 'C':
			n1=0;
			n2=8;
			n3=0;
			break;
		case 'S':
			n1=4;
			n2=0;
			n3=0;
		}
		while (a.getV1()!=n1 || a.getV2()!=n2 || a.getV3()!=n3) {
			a.setV3(a.getV3()-1);
			if (a.getV3()<0) {
				a.setV3(9);
				a.setV2(a.getV2()-1);
				if (a.getV2()<0) {
					a.setV2(9);
					a.setV1(a.getV1()-1);
				}
			}
			x.setText(a.getV1()+""+a.getV2()+""+a.getV3());
			try {
				if (a.getMod()=='C') {
					Thread.sleep(8);
				} else {
					if (a.getMod()=='S') {
						Thread.sleep(2);
					} else {
						Thread.sleep(12);
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
