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


public class PortaleAmministratoreGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public PortaleAmministratoreGUI(Controller c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PortaleAutoriGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);


		JLabel titoloPortaleAdminlbl = new JLabel("PORTALE ADMIN");
		titoloPortaleAdminlbl.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloPortaleAdminlbl.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPortaleAdminlbl.setBounds(10, 11, 395, 46);
		titoloPortaleAdminlbl.setOpaque(true);
		contentPane.add(titoloPortaleAdminlbl);

		JButton modificaButton = new JButton("MODIFICA / ELIMINA MATERIALI");
		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openModificaMaterialeADMIN();
			}
		});
		modificaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		modificaButton.setBounds(10, 117, 395, 38);
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
		libreriaButton.setBounds(10, 166, 395, 38);
		contentPane.add(libreriaButton);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton LogOutButton = new JButton("Logout");
		LogOutButton.setFont(new Font("Arial", Font.PLAIN, 14));
		LogOutButton.setBounds(316, 215, 89, 23);
		contentPane.add(LogOutButton);
		LogOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.logoutADMIN();
				controller.openHome();
			}

		});

			JButton btnEliminaAutori = new JButton("MODIFICA / ELIMINA AUTORI");
			btnEliminaAutori.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					controller.openModificaAutoriADMIN();
				}
			});
			btnEliminaAutori.setFont(new Font("Arial", Font.PLAIN, 14));
			btnEliminaAutori.setBounds(10, 68, 395, 38);
			contentPane.add(btnEliminaAutori);

//		JLabel autoreLabel = new JLabel("Benvenuto "+autore.getNome()+" "+autore.getCognome());
//		autoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		autoreLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
//		autoreLabel.setBounds(10, 218, 395, 32);
//		autoreLabel.setOpaque(true);
//		contentPane.add(autoreLabel);

				JLabel imageLabel = new JLabel("");




					imageLabel.setIcon(img);
					imageLabel.setBounds(0, 0, 415, 251);
					contentPane.add(imageLabel);
	}
}
