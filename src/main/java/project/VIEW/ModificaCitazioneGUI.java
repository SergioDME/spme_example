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

public class ModificaCitazioneGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTable table2;
	private JTextField eliminaField;

	public ModificaCitazioneGUI(Controller c, List<Citazione> listaCitazione, Materiale materiale) {
		setType(Type.POPUP);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificaCitazioneGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> citante = new Vector<String>();
		citante.add("Cita");


		Vector<Vector<Object>> datiCitante = new Vector<Vector<Object>>();

		for(Citazione citazione: listaCitazione) {
			Vector<Object> vector = new Vector<Object>();

			vector.add(citazione.getMaterialeCitato().getTitolo());
			datiCitante.add(vector);

		}
		contentPane.setLayout(null);



		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JLabel modificaLabel = new JLabel("<html>Citazioni fatte da <br>\""+materiale.getTitolo()+"\"</html>");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 358, 92);
		contentPane.add(modificaLabel);


		table2 = new JTable(new DefaultTableModel(datiCitante, citante));
		table2.setEnabled(false);
		table2.setAutoCreateRowSorter(true);
		table2.setRowSelectionAllowed(true);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table2.setBounds(263, 116, 237, 290);

		JScrollPane scrollpane_1 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane_1.setLocation(10, 114);
		scrollpane_1.setSize(358, 224);
		scrollpane_1.setViewportView(table2);
		contentPane.add(scrollpane_1);

		JButton aggiungiButton = new JButton("AGGIUNGI CITAZIONE");
		aggiungiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openAggiuntaCitazioneDaModifica(materiale);
				dispose();
				}
		});
		aggiungiButton.setFont(new Font("Arial", Font.PLAIN, 14));
		aggiungiButton.setBounds(10, 349, 358, 34);
		contentPane.add(aggiungiButton);

		JButton eliminaButton = new JButton("ELIMINA:");
		eliminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(Citazione citazione: listaCitazione) {
					if(citazione.getMaterialeCitato().getTitolo().equals(eliminaField.getText()))
					{
						controller.eliminaCitazione(citazione);

						dispose();
						controller.openModificaCitazioni(materiale);
						return;
					}
				}

				controller.openDialog("Citazione da eliminare non valida");
			}
		});
		eliminaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		eliminaButton.setBounds(10, 394, 119, 34);
		contentPane.add(eliminaButton);

		eliminaField = new JTextField();
		eliminaField.setFont(new Font("Arial", Font.PLAIN, 12));
		eliminaField.setColumns(10);
		eliminaField.setBounds(139, 394, 229, 34);
		contentPane.add(eliminaField);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 383, 443);
		contentPane.add(imageLabel);
	}
}
