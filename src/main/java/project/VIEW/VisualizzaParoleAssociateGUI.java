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

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class VisualizzaParoleAssociateGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;

	public VisualizzaParoleAssociateGUI(Controller c, List<ParolaChiave> listaParole, String titoloMateriale) {
		setType(Type.POPUP);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaParoleAssociateGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 456);
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
		scrollpane.setSize(237, 290);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JLabel modificaLabel = new JLabel("<html>Parole associate a<br/>"+titoloMateriale+"</html>", SwingConstants.CENTER);
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 235, 94);
		contentPane.add(modificaLabel);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 301, 426);
		contentPane.add(imageLabel);
	}
}
