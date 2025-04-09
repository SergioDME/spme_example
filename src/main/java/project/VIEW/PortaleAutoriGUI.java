package project.VIEW;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.MODEL.Autore;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PortaleAutoriGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public PortaleAutoriGUI(Controller c, Autore a) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PortaleAutoriGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;
		Autore autore = a;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);


		JLabel titoloPortaleAutoriLabel = new JLabel("PORTALE AUTORI");
		titoloPortaleAutoriLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloPortaleAutoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPortaleAutoriLabel.setBounds(10, 11, 451, 46);
		titoloPortaleAutoriLabel.setOpaque(true);
		contentPane.add(titoloPortaleAutoriLabel);


		JButton inserisciButton = new JButton("INSERISCI");
		inserisciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openInsesciMaterialeGUI();
			}
		});
		inserisciButton.setFont(new Font("Arial", Font.PLAIN, 14));
		inserisciButton.setBounds(10, 104, 125, 38);
		contentPane.add(inserisciButton);

		JButton modificaButton = new JButton("MODIFICA / ELIMINA");
		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openModificaMateriale();
			}
		});
		modificaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		modificaButton.setBounds(145, 104, 181, 38);
		contentPane.add(modificaButton);

		JButton libreriaButton = new JButton("LIBRERIA");
		libreriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLibreria();
				dispose();
				setVisible(false);
			}
		});
		libreriaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		libreriaButton.setBounds(336, 104, 125, 38);
		contentPane.add(libreriaButton);

		JLabel autoreLabel = new JLabel("Benvenuto "+autore.getNome()+" "+autore.getCognome());
		autoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autoreLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		autoreLabel.setBounds(10, 218, 451, 32);
		autoreLabel.setOpaque(true);
		contentPane.add(autoreLabel);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton LogOutButton = new JButton("Logout");
		LogOutButton.setFont(new Font("Arial", Font.PLAIN, 14));
		LogOutButton.setBounds(372, 184, 89, 23);
		contentPane.add(LogOutButton);
		LogOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.setAutore(null);
				controller.openHome();
			}

		});




		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 471, 262);
		contentPane.add(imageLabel);
	}
}
