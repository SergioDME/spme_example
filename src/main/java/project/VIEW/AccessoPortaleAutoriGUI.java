package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.CONTROLLER.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AccessoPortaleAutoriGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public AccessoPortaleAutoriGUI(Controller c) {

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library managment system");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AccessoPortaleAutoriGUI.class.getResource("/GUI/libri.png")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//finestra a centro dello schermo
		setLocationRelativeTo(null);

		JLabel titoloPortaleAutoriLabel = new JLabel("ACCESSO PORTALE AUTORI");
		titoloPortaleAutoriLabel.setOpaque(true);
		titoloPortaleAutoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPortaleAutoriLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloPortaleAutoriLabel.setBounds(10, 11, 414, 41);
		contentPane.add(titoloPortaleAutoriLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameField.setToolTipText("Username\r\n");
		usernameField.setBounds(172, 78, 161, 23);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAutore();
			}

			private void loginAutore() {
				String passwordString = new String(passwordField.getPassword());
				controller.loginAutore(usernameField.getText(), passwordString);

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
		usernameLabel.setBounds(91, 80, 82, 19);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setOpaque(true);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordLabel.setBounds(91, 138, 82, 19);
		contentPane.add(passwordLabel);

		JLabel registrazioneLabel = new JLabel("Se non sei registrato, clicca");
		registrazioneLabel.setOpaque(true);
		registrazioneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		registrazioneLabel.setBounds(91, 232, 151, 14);
		contentPane.add(registrazioneLabel);

		JButton quiButton = new JButton("qui");
		quiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openRegistrazione();
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		quiButton.setFont(new Font("Arial", Font.PLAIN, 12));
		quiButton.setBounds(242, 232, 64, 14);
		contentPane.add(quiButton);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordField.setBounds(172, 136, 161, 23);
		contentPane.add(passwordField);


		JCheckBox mostraPassword = new JCheckBox("Mostra");
		mostraPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		mostraPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxPassword(mostraPassword);
			}

			private void checkBoxPassword(JCheckBox mostraPassword) {
				if(mostraPassword.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('a');
				}
			}
		});
		mostraPassword.setBounds(339, 136, 74, 23);
		contentPane.add(mostraPassword);

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openHome();
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		backButton.setFont(new Font("Arial", Font.BOLD, 12));
		backButton.setBounds(10, 225, 48, 23);

		contentPane.add(backButton);


		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 440, 262);
		contentPane.add(imageLabel);
	}



}


