package project.VIEW;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import project.CONTROLLER.Controller;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessoPortaleAmministratoreGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public AccessoPortaleAmministratoreGUI(Controller c) {
		setResizable(false);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccessoPortaleAmministratoreGUI.class.getResource("/GUI/libri.png")));
		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//finestra a centro dello schermo
		setLocationRelativeTo(null);

		JLabel titoloPortaleAmministratoreLabel = new JLabel("ACCESSO PORTALE AMMINISTRATORE");
		titoloPortaleAmministratoreLabel.setOpaque(true);
		titoloPortaleAmministratoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPortaleAmministratoreLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloPortaleAmministratoreLabel.setBounds(10, 11, 459, 41);
		contentPane.add(titoloPortaleAmministratoreLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameField.setToolTipText("Username\r\n");
		usernameField.setBounds(172, 78, 161, 23);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAmministrazione();
			}

			private void loginAmministrazione() {
				String passwordString = new String(passwordField.getPassword());
				controller.loginAmministratore(usernameField.getText(), passwordString);

				    usernameField.setText("");
					passwordField.setText("");
			}
		});
		loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
		loginButton.setBounds(172, 182, 89, 23);
		contentPane.add(loginButton);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setOpaque(true);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameLabel.setBounds(91, 78, 86, 23);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setOpaque(true);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordLabel.setBounds(91, 136, 86, 23);
		contentPane.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordField.setBounds(172, 136, 161, 23);
		contentPane.add(passwordField);



		JCheckBox mostraPassword = new JCheckBox("Mostra");
		mostraPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		mostraPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mostraPassword.isSelected()) {

				passwordField.setEchoChar((char)0);
		}
	    else {

				passwordField.setEchoChar('a');
			}

			}
		});
		mostraPassword.setBounds(339, 139, 77, 21);
		contentPane.add(mostraPassword);

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openHome();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		backButton.setBounds(10, 227, 48, 23);
		contentPane.add(backButton);


		JLabel imageLabel = new JLabel("");
		imageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 479, 261);
		contentPane.add(imageLabel);

	}

}
