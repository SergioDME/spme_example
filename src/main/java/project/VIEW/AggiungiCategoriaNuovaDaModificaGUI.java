package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.DAO.DAOPostgres.CategoriaDiAppartenenzaDAOPostgres;
import project.MODEL.Categoria;
import project.MODEL.CategoriaDiAppartenenza;
import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.MODEL.ParolaChiave;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;

public class AggiungiCategoriaNuovaDaModificaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public AggiungiCategoriaNuovaDaModificaGUI(Controller c, Materiale materiale) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaCategoriaNuovaGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JTextPane txtpnCreareUnaNuova = new JTextPane();
		txtpnCreareUnaNuova.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnCreareUnaNuova.setEditable(false);
		txtpnCreareUnaNuova.setText("Creare una nuova gerarchia di categorie o scegliere una supercategoria esistente?");
		txtpnCreareUnaNuova.setBounds(10, 11, 295, 49);
		contentPane.add(txtpnCreareUnaNuova);

		JButton nuovaButton = new JButton("NUOVO");
		nuovaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAggiuntaGerarchiaNuovaDaModifica(materiale);
				dispose();
			}
		});
		nuovaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		nuovaButton.setBounds(10, 71, 123, 29);
		contentPane.add(nuovaButton);

		JButton esistenteButton = new JButton("ESISTENTE");
		esistenteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAggiuntaAGerarchiaEsistenteDaModifica(materiale);
				dispose();
			}
		});
		esistenteButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esistenteButton.setBounds(182, 71, 123, 29);
		contentPane.add(esistenteButton);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 313, 111);
		contentPane.add(imageLabel);
	}
}
