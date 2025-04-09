package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.CONTROLLER.Controller;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibreriaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public LibreriaGUI(Controller c) {
		setTitle("Library managment system");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LibreriaGUI.class.getResource("/GUI/libri.png")));
		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 setLocationRelativeTo(null);

		JLabel titoloLibreriaLabel = new JLabel("LIBRERIA");
		titoloLibreriaLabel.setOpaque(true);
		titoloLibreriaLabel.setBounds(103, 11, 181, 29);
		titoloLibreriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLibreriaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		contentPane.add(titoloLibreriaLabel);

		JButton ricercaButton = new JButton("RICERCA");
		ricercaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openRicerca();
			}
		});
		ricercaButton.setBounds(10, 60, 142, 38);
		contentPane.add(ricercaButton);

		JButton visualizzaButton = new JButton("VISUALIZZA");
		visualizzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openVisualizzaLibreria();
			}
		});
		visualizzaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		visualizzaButton.setBounds(236, 60, 142, 38);
		contentPane.add(visualizzaButton);

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				if(controller.getADMIN()) {
					controller.openPortaleAmministratore();
					return;
				}

				if(controller.getAutore() == null) {
					controller.openHome();
					return;
				}

				controller.openPortaleAutori();
				return;

			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(10, 109, 48, 23);
		contentPane.add(backButton);


		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 388, 153);
		contentPane.add(imageLabel);
	}

}
