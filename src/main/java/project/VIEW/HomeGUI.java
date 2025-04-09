package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.CONTROLLER.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class HomeGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public HomeGUI(Controller c) {
		setTitle("Library managment system");

		controller = c;

		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		//finestra a centro dello schermo
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		JLabel titoloHomeLabel = new JLabel(" LIBRARY MANAGEMENT SYSTEM ");
		titoloHomeLabel.setBackground(SystemColor.window);
		titoloHomeLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloHomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloHomeLabel.setBounds(24, 11, 391, 40);
		titoloHomeLabel.setOpaque(true);  //serve a rendere lo sfondo della scritta opaco
		contentPane.add(titoloHomeLabel);


		JButton libreriaButton = new JButton("LIBRERIA");
		libreriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openLibreria();
			}
		});
		libreriaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		libreriaButton.setBounds(123, 70, 183, 50);
		contentPane.add(libreriaButton);

		JButton portaleAutoriButton = new JButton("PORTALE AUTORI");
		portaleAutoriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAccessoPortaleAutori();
			}
		});
		portaleAutoriButton.setFont(new Font("Arial", Font.PLAIN, 14));
		portaleAutoriButton.setBounds(123, 131, 183, 50);
		contentPane.add(portaleAutoriButton);

		JButton amministrazioneButton = new JButton("LOGIN AMMINISTRAZIONE");
		amministrazioneButton.setFont(new Font("Arial", Font.PLAIN, 11));
		amministrazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAccessoPortaleAmministratore();
			}
		});
		amministrazioneButton.setBounds(117, 228, 208, 23);
		contentPane.add(amministrazioneButton);

		//inserimento immagine libreria
		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 440, 262);
		contentPane.add(imageLabel);


	}
}
