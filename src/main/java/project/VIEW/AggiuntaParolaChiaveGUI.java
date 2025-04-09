package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.MODEL.ParolaChiave;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.Toolkit;

public class AggiuntaParolaChiaveGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField parolaField;

	public AggiuntaParolaChiaveGUI(Controller c, Materiale materiale) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaParolaChiaveGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);


		JLabel titoloLabel = new JLabel("ASSOCIA PAROLA CHIAVE");
		titoloLabel.setOpaque(true);
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloLabel.setBounds(11, 11, 294, 37);
		contentPane.add(titoloLabel);

		parolaField = new JTextField();
		parolaField.setToolTipText("massimo 25 caratteri");
		parolaField.setFont(new Font("Arial", Font.PLAIN, 12));
		parolaField.setBounds(10, 59, 169, 28);
		contentPane.add(parolaField);
		parolaField.setColumns(10);

		JLabel lunghezzaLabel = new JLabel("(massimo 25 caratteri)");
		lunghezzaLabel.setOpaque(true);
		lunghezzaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lunghezzaLabel.setBounds(20, 108, 131, 14);
		contentPane.add(lunghezzaLabel);

		JButton btnNewButton = new JButton("Associa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(parolaField.getText().isBlank())
					controller.openDialog("Campo vuoto non ammesso");
				else {
					if(parolaField.getText().length()<26) {
						ParolaChiave parola = new ParolaChiave(parolaField.getText());
						controller.associaParola(materiale, parola);
						}
					else
						controller.openDialog("Lunghezza massima 25 caratteri");
					}

				parolaField.setText("");
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(205, 59, 100, 28);
		contentPane.add(btnNewButton);

		JButton esciButton = new JButton("Esci");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				parolaField.setText("");
				dispose();
				controller.chiediCategoria(materiale);
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esciButton.setBounds(205, 99, 100, 23);
		contentPane.add(esciButton);

		JScrollBar parolaScrollBar = new JScrollBar();
		parolaScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		parolaScrollBar.setBounds(11, 85, 168, 14);
		BoundedRangeModel brmParola = parolaField.getHorizontalVisibility();
		parolaScrollBar.setModel(brmParola);
		contentPane.add(parolaScrollBar);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 316, 133);
		contentPane.add(imageLabel);
	}
}

