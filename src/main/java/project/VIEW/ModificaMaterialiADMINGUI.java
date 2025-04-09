package project.VIEW;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.MODEL.Materiale;
import project.CONTROLLER.Controller;
import project.struttureDiAppoggio.RigaTabellaMateriale;

import javax.swing.JLabel;
import java.awt.Toolkit;

public class ModificaMaterialiADMINGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField modificaField;

	public ModificaMaterialiADMINGUI(Controller c, List<RigaTabellaMateriale> listaRiga) {
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificaMaterialeGUI.class.getResource("/GUI/libri.png")));

		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> colonne = new Vector<String>();
		colonne.add("Autore");
		colonne.add("ORCID");
		colonne.add("Data pubblicazione");
		colonne.add("Data inserimento");
		colonne.add("Titolo");
		colonne.add("DOI");
		colonne.add("Descrizione");
		colonne.add("Tipo");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(RigaTabellaMateriale rigaTabella: listaRiga) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(rigaTabella.getAutore().getNome()+" "+rigaTabella.getAutore().getCognome());
			vector.add(rigaTabella.getAutore().getOrcid());
			vector.add(rigaTabella.getPubblicazione().getDataPubblicazione());
			vector.add(rigaTabella.getPubblicazione().getDataInserimento());
			vector.add(rigaTabella.getMateriale().getTitolo());
			vector.add(rigaTabella.getMateriale().getDoi());
			vector.add(rigaTabella.getMateriale().getDescrizione());
			vector.add(rigaTabella.getMateriale().getTipo());
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

		modificaField = new JTextField();
		modificaField.setFont(new Font("Arial", Font.PLAIN, 12));
		modificaField.setToolTipText("Titolo del materiale da modifica");
		modificaField.setBounds(10, 292, 177, 38);
		contentPane.add(modificaField);
		modificaField.setColumns(10);

		JButton modificaButton = new JButton("MODIFICA");
		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(RigaTabellaMateriale rigaTabella: listaRiga) {
					if(rigaTabella.getMateriale().getTitolo().equals(modificaField.getText())) {
						controller.modificaMateriale(rigaTabella.getMateriale());
						setVisible(false);
						return;
					}
				}
				modificaField.setText("");
				controller.openDialog("Titolo non valido");
			}
		});
		modificaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		modificaButton.setBounds(197, 291, 120, 38);
		contentPane.add(modificaButton);

		JButton esciButton = new JButton("ESCI");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				if(controller.getADMIN())
					controller.openPortaleAmministratore();
				else
					controller.returnToPortaleAutori();
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 14));
		esciButton.setBounds(735, 291, 120, 38);
		contentPane.add(esciButton);

		JLabel lblNewLabel = new JLabel("Titolo da modificare:");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 267, 143, 14);
		contentPane.add(lblNewLabel);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton eliminaButton = new JButton("ELIMINA");
		eliminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(RigaTabellaMateriale rigaTabella: listaRiga) {
					if(rigaTabella.getMateriale().getTitolo().equals(modificaField.getText())) {
						controller.eliminaMateriale(rigaTabella.getMateriale());
						setVisible(false);
						return;
					}
				}
				modificaField.setText("");
				controller.openDialog("Titolo non valido");
			}
		});
		eliminaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		eliminaButton.setBounds(327, 289, 107, 38);
		contentPane.add(eliminaButton);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 867, 340);
		contentPane.add(imageLabel);
	}
}
