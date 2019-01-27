import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class FinestraInfo extends JFrame implements KeyListener {
	private ImageIcon[] sfondi = new ImageIcon[4];
	private JLabel[] s = new JLabel[4];
	private int i;
	private int schermata=0;
	private String per;
	public FinestraInfo() {
		setEnabled(true);
		disegnaGUI();
		aggiungiListener();
	}
	private void disegnaGUI() {
		
		for (i=0; i<4; i++) {
			per= "s"+String.valueOf(i)+".jpeg";
			sfondi[i] = new ImageIcon(per);
			s[i]=new JLabel(sfondi[i]);
			if (i==0) {
				setLayout(new BorderLayout());
				setContentPane(s[i]);
			} else {
				s[i].setVisible(false);
				s[i].setBounds(5,5,900,500);
				add(s[i]);
			}
		}
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setVisible(false);
		setResizable(false);
		setLocation(40, 40);
		pack();
		setTitle("Informazioni");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	private void aggiungiListener() {
		addKeyListener(this);
	}
	
	public class FixedStateButtonModel extends DefaultButtonModel    {

        @Override
        public boolean isPressed() {
            return false;
        }

        @Override
        public boolean isRollover() {
            return false;
        }

        @Override
        public void setRollover(boolean b) {
            
        }

    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==37 && schermata!=1) {
			schermata--;
			if (schermata==0) {
				setLayout(new BorderLayout());
				setContentPane(s[0]);
			} else {
				s[schermata].setVisible(true);
			}
		}
		if (arg0.getKeyCode()==39 && (schermata+1)<=3) {
			if (schermata==1 || schermata==2) {
				s[schermata].setVisible(false);
			}
			if (schermata==0) {
				schermata++;
			}
			schermata++;
			s[schermata].setVisible(true);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
