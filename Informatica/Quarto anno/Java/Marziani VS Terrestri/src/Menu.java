import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Menu extends JFrame implements ActionListener{
	private ImageIcon sfondo = new ImageIcon("sfondo.jpg");
	private ImageIcon q1 = new ImageIcon("q1.png");
	private ImageIcon q2 = new ImageIcon("q2.png");
	private ImageIcon l1 = new ImageIcon("l1.png");
	private ImageIcon l2 = new ImageIcon("l2.png");
	private Object[] num = { 1, 3, 5 };
	private Object liv;
	private JButton btnMar = new JButton(new ImageIcon("btnMar.png"));
	private JButton btnTer = new JButton(new ImageIcon("btnTer.png"));
	private Cursor c = new Cursor(HAND_CURSOR);
	private int scelta;
	public Menu() {
		disegnaGUI();
	}
	
	private void disegnaGUI() {
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
		setIconImage(icon);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(sfondo));
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setTitle("Marziani VS Terrestri - Menu principale");
		btnMar.setBounds(110, 429, 369, 168);
		btnMar.setBorder(null);
		btnMar.setCursor(c);
		btnMar.setBackground(Color.black);
		btnMar.setFocusPainted(false);
		btnMar.setFocusable(false);
		btnMar.setOpaque(false);
		btnMar.setContentAreaFilled(false);
		btnMar.setBorderPainted(false);
		btnMar.addActionListener(this);
		add(btnMar);
		btnTer.setBounds(517, 429, 369, 168);
		btnTer.setBorder(null);
		btnTer.setCursor(c);
		btnTer.setBackground(Color.black);
		btnTer.setFocusPainted(false);
		btnTer.setFocusable(false);
		btnTer.setOpaque(false);
		btnTer.setContentAreaFilled(false);
		btnTer.setBorderPainted(false);
		btnTer.addActionListener(this);
		add(btnTer);
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource().equals(btnMar)) {
			scelta=JOptionPane.showConfirmDialog(null, "Vuoi giocare con il marziano?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, q1);
			if (scelta==0) {
				liv = JOptionPane.showInputDialog(null, "Seleziona numero di livelli", "Livelli", JOptionPane.QUESTION_MESSAGE, l1, num, num[0]);
				if (liv != null) {
					dispose();
					
				}
			}
		}
		if (ev.getSource().equals(btnTer)) {
			scelta=JOptionPane.showConfirmDialog(null, "Vuoi giocare con il terrestre?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, q2);
			if (scelta==0) {
				liv = JOptionPane.showInputDialog(null, "Seleziona numero di livelli", "Livelli", JOptionPane.QUESTION_MESSAGE, l2, num, num[0]);
				if (liv != null) {
					dispose();
					GiocoT g = new GiocoT(new Terrestre("su", 3, 100, 20, 40, 0, 'N'), new Marziano("giu", 1, 100, 5, 5, 0, 'N'), 1);
				}
			}
		}
	}
}