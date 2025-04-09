package project.VIEW;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import project.MODEL.Autore;
import project.CONTROLLER.Controller;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class ModificaAutoreADMINGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField ORCIDField;

	public ModificaAutoreADMINGUI(Controller c, Autore autore) {
		setResizable(false);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneGUI.class.getResource("/GUI/libri.png")));
		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 setLocationRelativeTo(null);

		JLabel titoloPortaleAutoriLabel = new JLabel("MODIFICA AUTORE");
		titoloPortaleAutoriLabel.setOpaque(true);
		titoloPortaleAutoriLabel.setBounds(15, 16, 404, 40);
		titoloPortaleAutoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloPortaleAutoriLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));

		usernameField = new JTextField();
		usernameField.setText(autore.getUsername());
		usernameField.setBounds(177, 167, 151, 23);
		usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameField.setToolTipText("Username\r\n");
		usernameField.setColumns(10);

		JButton registrazioneButton = new JButton("MODIFICA");
		registrazioneButton.setBounds(130, 231, 161, 25);
		registrazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String passwordString = new String(passwordField.getPassword());

				if(passwordString.isBlank()){
					if(controller.updateAutore(nomeField.getText(), cognomeField.getText(), ORCIDField.getText(), usernameField.getText(), autore))					{
						pulisciCampi();
						dispose();
						controller.openModificaAutoriADMIN();
					}

				} else {
					if(controller.updateAutore(nomeField.getText(), cognomeField.getText(), ORCIDField.getText(), usernameField.getText(), passwordString, autore)) {
						pulisciCampi();
						dispose();
						controller.openModificaAutoriADMIN();
					}
				}
			}

			private void pulisciCampi() {
				nomeField.setText("");
				cognomeField.setText("");
				ORCIDField.setText("");
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		registrazioneButton.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel cognomeLabel = new JLabel("Cognome:");
		cognomeLabel.setOpaque(true);
		cognomeLabel.setBounds(96, 108, 76, 19);
		cognomeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setOpaque(true);
		passwordLabel.setBounds(96, 200, 76, 19);
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		passwordField = new JPasswordField();
		passwordField.setText("");
		passwordField.setBounds(177, 199, 151, 21);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setOpaque(true);
		nomeLabel.setBounds(96, 74, 76, 19);
		nomeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		nomeField = new JTextField();
		nomeField.setText(autore.getNome());
		nomeField.setBounds(177, 72, 151, 23);
		nomeField.setToolTipText("Nome\r\n");
		nomeField.setFont(new Font("Arial", Font.PLAIN, 12));
		nomeField.setColumns(10);

		cognomeField = new JTextField();
		cognomeField.setText(autore.getCognome());
		cognomeField.setBounds(177, 106, 151, 23);
		cognomeField.setToolTipText("Cognome");
		cognomeField.setFont(new Font("Arial", Font.PLAIN, 12));
		cognomeField.setColumns(10);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setOpaque(true);
		usernameLabel.setBounds(96, 172, 76, 19);
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		ORCIDField = new JTextField();
		ORCIDField.setText(autore.getOrcid());
		ORCIDField.setBounds(177, 138, 151, 23);
		ORCIDField.setToolTipText("ORCID(16 caratteri numerici)");
		ORCIDField.setFont(new Font("Arial", Font.PLAIN, 12));
		ORCIDField.setColumns(10);

		JLabel orcidLabel = new JLabel("ORCID:");
		orcidLabel.setOpaque(true);
		orcidLabel.setBounds(96, 142, 76, 19);
		orcidLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton backButton = new JButton("\u2190");
		backButton.setBounds(15, 231, 48, 24);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pulisciCampi();
				dispose();
				controller.openModificaAutoriADMIN();
			}

			private void pulisciCampi() {
				nomeField.setText("");
				cognomeField.setText("");
				ORCIDField.setText("");
				usernameField.setText("");
				passwordField.setText("");
			}
		});

		JCheckBox modificaPassword = new JCheckBox("Modifica");
		modificaPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		modificaPassword.setBounds(334, 200, 107, 19);
		modificaPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modificaPassword.isSelected()) {

				passwordField.setEchoChar((char)0);
		}
	    else {

				passwordField.setEchoChar('a');
			}

			}
		});
		contentPane.setLayout(null);
		contentPane.add(titoloPortaleAutoriLabel);
		contentPane.add(nomeLabel);
		contentPane.add(nomeField);
		contentPane.add(cognomeLabel);
		contentPane.add(cognomeField);
		contentPane.add(ORCIDField);
		contentPane.add(orcidLabel);
		contentPane.add(usernameLabel);
		contentPane.add(usernameField);
		contentPane.add(passwordLabel);
		contentPane.add(passwordField);
		contentPane.add(modificaPassword);
		contentPane.add(backButton);
		contentPane.add(registrazioneButton);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 412, 261);
		contentPane.add(imageLabel);


	}
}
