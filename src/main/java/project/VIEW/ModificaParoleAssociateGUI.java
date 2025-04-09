package project.VIEW;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.MODEL.Materiale;
import project.MODEL.ParolaChiave;
import project.CONTROLLER.Controller;
import project.struttureDiAppoggio.RigaTabellaMateriale;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class ModificaParoleAssociateGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField eliminaField;
	private JTextField aggiungiField;

	public ModificaParoleAssociateGUI(Controller c, List<ParolaChiave> listaParole, Materiale materiale) {

		setType(Type.POPUP);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificaParoleAssociateGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> colonne = new Vector<String>();
		colonne.add("Parola");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(ParolaChiave parola: listaParole) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(parola.getParola());
			dati.add(vector);
		}
		contentPane.setLayout(null);



		table = new JTable(new DefaultTableModel(dati, colonne));
		table.setEnabled(false);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(true);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setLocation(10, 116);
		scrollpane.setSize(286, 281);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JLabel modificaLabel = new JLabel("<html>Parole associate a<br/>"+materiale.getTitolo()+"</html>", SwingConstants.CENTER);
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 286, 94);
		contentPane.add(modificaLabel);

		JButton eliminaButton = new JButton("ELIMINA:");
		eliminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(ParolaChiave parola: listaParole) {
					if(parola.getParola().equals(eliminaField.getText()))
					{
						controller.eliminaParolaChiave(parola, materiale);

						setVisible(false);
						controller.openModificaParoleChiave(materiale);
						dispose();
						return;
					}
				}

				controller.openDialog("Parola non presente");
				eliminaField.setText("");
			}
		});
		eliminaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		eliminaButton.setBounds(10, 453, 119, 34);
		contentPane.add(eliminaButton);

		eliminaField = new JTextField();
		eliminaField.setFont(new Font("Arial", Font.PLAIN, 12));
		eliminaField.setBounds(139, 454, 157, 34);
		contentPane.add(eliminaField);
		eliminaField.setColumns(10);

		aggiungiField = new JTextField();
		aggiungiField.setFont(new Font("Arial", Font.PLAIN, 12));
		aggiungiField.setColumns(10);
		aggiungiField.setBounds(139, 408, 157, 34);
		contentPane.add(aggiungiField);

		JButton aggiungiButton = new JButton("AGGIUNGI:");
		aggiungiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(aggiungiField.getText().isBlank())
					controller.openDialog("Campo vuoto non ammesso");
				else {
					ParolaChiave parola = new ParolaChiave(aggiungiField.getText());
					controller.associaParola(materiale, parola);

					setVisible(false);
					controller.openModificaParoleChiave(materiale);
					dispose();
				}

			}
		});
		aggiungiButton.setFont(new Font("Arial", Font.PLAIN, 14));
		aggiungiButton.setBounds(10, 408, 119, 34);
		contentPane.add(aggiungiButton);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 363, 498);
		contentPane.add(imageLabel);
	}
}
