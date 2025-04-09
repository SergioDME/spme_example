package project.VIEW;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.MODEL.Categoria;
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

public class ModificaCategorieGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField eliminaField;

	public ModificaCategorieGUI(Controller c, List<Categoria> listaCategorie, Materiale materiale) {
		setType(Type.POPUP);
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificaCategorieGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		Vector<String> colonne = new Vector<String>();
		colonne.add("Categoria");
		colonne.add("Supercategoria");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(Categoria categoria: listaCategorie) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(categoria.getNome());
			vector.add(categoria.getSupercategoria().getNome());
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
		scrollpane.setLocation(10, 69);
		scrollpane.setSize(430, 290);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JLabel modificaLabel = new JLabel("Categorie associate a \""+materiale.getTitolo()+"\"");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 430, 47);
		contentPane.add(modificaLabel);

		JButton aggiungiButton = new JButton("AGGIUNGI CATEGORIA");
		aggiungiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openAggiungiCategoriaDaModifica(materiale);
			}
		});
		aggiungiButton.setFont(new Font("Arial", Font.PLAIN, 14));
		aggiungiButton.setBounds(10, 370, 430, 34);
		contentPane.add(aggiungiButton);

		JButton eliminaButton = new JButton("ELIMINA:");
		eliminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Categoria categoria: listaCategorie) {
					if(categoria.getNome().equals(eliminaField.getText()))
					{
						controller.eliminaCategoria(materiale, categoria);
						dispose();
						controller.openModificaCategorie(materiale);
						return;
					}
				}

				controller.openDialog("Categoria da eliminare non valida");
				eliminaField.setText("");
			}
		});
		eliminaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		eliminaButton.setBounds(10, 415, 119, 34);
		contentPane.add(eliminaButton);

		eliminaField = new JTextField();
		eliminaField.setFont(new Font("Arial", Font.PLAIN, 12));
		eliminaField.setColumns(10);
		eliminaField.setBounds(139, 415, 301, 34);
		contentPane.add(eliminaField);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 465, 462);
		contentPane.add(imageLabel);
	}
}
