package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog extends JFrame {

	public Dialog(String testo) {
		JOptionPane.showInternalMessageDialog(null,testo);
	}
}
