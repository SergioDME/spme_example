package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AggiuntaCategoriaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;

	public AggiuntaCategoriaGUI(Controller c, Materiale materiale) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaCategoriaGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel titoloLabel = new JLabel("MODALITA' DI INSERIMENTO");
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setBounds(38, 11, 331, 45);
		contentPane.add(titoloLabel);

		JButton nuovaButton = new JButton("NUOVA");
		nuovaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAggiuntaCategoriaNuova(materiale);
				dispose();
			}
		});
		nuovaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		nuovaButton.setBounds(113, 67, 181, 45);
		contentPane.add(nuovaButton);

		JButton btnScegliEsistente = new JButton("SCEGLI ESISTENTE");
		btnScegliEsistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAggiungiCategoriaEsistente(materiale);
				dispose();
			}
		});
		btnScegliEsistente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnScegliEsistente.setBounds(113, 123, 181, 45);
		contentPane.add(btnScegliEsistente);

		JButton btnNewButton = new JButton("ESCI");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.chiediCitazioni(materiale);
			}
		});
		btnNewButton.setBounds(323, 179, 74, 35);
		contentPane.add(btnNewButton);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 407, 225);
		contentPane.add(imageLabel);
	}
}
