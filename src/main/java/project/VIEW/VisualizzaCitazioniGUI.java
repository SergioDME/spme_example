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

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class VisualizzaCitazioniGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTable table2;

	public VisualizzaCitazioniGUI(Controller c, List<Citazione> listaCitazione, String titoloMateriale) {
		setType(Type.POPUP);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaCitazioniGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> citato = new Vector<String>();
		citato.add("Citato da");

		Vector<String> citante = new Vector<String>();
		citante.add("Cita");

		Vector<Vector<Object>> datiCitato = new Vector<Vector<Object>>();

		Vector<Vector<Object>> datiCitante = new Vector<Vector<Object>>();

		for(Citazione citazione: listaCitazione) {
			Vector<Object> vector = new Vector<Object>();

			if(citazione.getMaterialeCitante().getTitolo().equals(titoloMateriale)) {
				vector.add(citazione.getMaterialeCitato().getTitolo());
				datiCitante.add(vector);
			}
			else {
				vector.add(citazione.getMaterialeCitante().getTitolo());
				datiCitato.add(vector);
			}
		}
		contentPane.setLayout(null);



		table = new JTable(new DefaultTableModel(datiCitato, citato));
		table.setEnabled(false);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(true);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setLocation(263, 68);
		scrollpane.setSize(237, 260);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JLabel modificaLabel = new JLabel("Citazioni riguardanti \""+titoloMateriale+"\"");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 490, 46);
		contentPane.add(modificaLabel);


		table2 = new JTable(new DefaultTableModel(datiCitante, citante));
		table2.setEnabled(false);
		table2.setAutoCreateRowSorter(true);
		table2.setRowSelectionAllowed(true);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table2.setBounds(263, 116, 237, 290);

		JScrollPane scrollpane_1 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane_1.setLocation(10, 68);
		scrollpane_1.setSize(237, 260);
		scrollpane_1.setViewportView(table2);
		contentPane.add(scrollpane_1);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 510, 339);
		contentPane.add(imageLabel);
	}
}
