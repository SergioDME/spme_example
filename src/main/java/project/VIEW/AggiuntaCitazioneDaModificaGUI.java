package project.VIEW;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.MODEL.Citazione;
import project.MODEL.Materiale;
import project.CONTROLLER.Controller;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class AggiuntaCitazioneDaModificaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField citaField;
	private JLabel titoloLabel;

	public AggiuntaCitazioneDaModificaGUI(Controller c, List<Materiale> listaMateriali, Materiale materialeCitante) {
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaCitazioneDaModificaGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> colonne = new Vector<String>();
		colonne.add("Titolo");
		colonne.add("DOI");
		colonne.add("Descrizione");
		colonne.add("Tipo");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(Materiale materiale: listaMateriali) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(materiale.getTitolo());
			vector.add(materiale.getDoi());
			vector.add(materiale.getDescrizione());
			vector.add(materiale.getTipo());
			dati.add(vector);
		}
		contentPane.setLayout(null);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setLocation(10, 5);
		scrollpane.setSize(845, 251);

		table = new JTable(new DefaultTableModel(dati, colonne));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBounds(114, 11, 425, 347);

		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);

		citaField = new JTextField();
		citaField.setFont(new Font("Arial", Font.PLAIN, 12));
		citaField.setToolTipText("Titolo del materiale da citare");
		citaField.setBounds(10, 299, 177, 38);
		contentPane.add(citaField);
		citaField.setColumns(10);

		JButton citaButton = new JButton("CITA");
		citaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int trovato = 0;

				if(citaField.getText().equals(materialeCitante.getTitolo())) {
					controller.openDialog("Citazione ricorsiva non ammessa");
					citaField.setText("");

				} else {

					if(!citaField.getText().isBlank()) {
						for(Materiale materiale: listaMateriali) {
							if(materiale.getTitolo().equals(citaField.getText())) {
								trovato = 1;
								Citazione citazione = new Citazione(materialeCitante.getTitolo(), citaField.getText());
								controller.aggiungiCitazione(citazione);
								citaField.setText("");
								}
							}
						if(trovato == 0) {
							citaField.setText("");
							controller.openDialog("Titolo non valido");
						}
					}
					else {
						citaField.setText("");
						controller.openDialog("Titolo non valido");

					}
				}

			}
		});
		citaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		citaButton.setBounds(198, 298, 107, 38);
		contentPane.add(citaButton);

		JButton esciButton = new JButton("ESCI");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openModificaCitazioni(materialeCitante);
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esciButton.setBounds(735, 298, 120, 38);
		contentPane.add(esciButton);

		titoloLabel = new JLabel("Titolo del materiale da citare:");
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloLabel.setBounds(10, 274, 203, 14);
		contentPane.add(titoloLabel);


		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 879, 349);
		contentPane.add(imageLabel);
	}
}
