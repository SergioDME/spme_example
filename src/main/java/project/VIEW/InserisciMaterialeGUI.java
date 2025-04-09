package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.MODEL.Autore;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class InserisciMaterialeGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;


	public InserisciMaterialeGUI(Controller c) {

		controller = c;

		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserisciMaterialeGUI.class.getResource("/GUI/libri.png")));
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel tipoLabel = new JLabel("TIPO DA INSERIRE:");
		tipoLabel.setBounds(10, 11, 414, 40);
		tipoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tipoLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		tipoLabel.setOpaque(true);
		contentPane.add(tipoLabel);

		JButton libroButton = new JButton("Libro");
		libroButton.setBounds(68, 62, 135, 40);
		libroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInserimentoLibro();
			}
		});
		libroButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(libroButton);

		JButton datasetButton = new JButton("Dataset");
		datasetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInserimentoDataset();
			}
		});
		datasetButton.setBounds(221, 62, 135, 40);
		datasetButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(datasetButton);

		JButton articoloSuConferenzaButton = new JButton("Articolo su Conferenza");
		articoloSuConferenzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInserimentoArticoloSuConferenza();
			}
		});
		articoloSuConferenzaButton.setBounds(68, 113, 288, 40);
		articoloSuConferenzaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(articoloSuConferenzaButton);

		JButton articoloSuRivistaButton = new JButton("Articolo su Rivista");
		articoloSuRivistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInserimentoArticoloSuRivista();
			}
		});
		articoloSuRivistaButton.setBounds(68, 164, 288, 40);
		articoloSuRivistaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(articoloSuRivistaButton);

		JButton risorsaOnlineButton = new JButton("Risorsa Online");
		risorsaOnlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInserimentoRisoraOnline();
			}
		});
		risorsaOnlineButton.setBounds(68, 215, 288, 40);
		risorsaOnlineButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(risorsaOnlineButton);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.returnToPortaleAutori();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(10, 224, 48, 23);
		contentPane.add(backButton);


		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 440, 262);
		contentPane.add(imageLabel);
	}
}
