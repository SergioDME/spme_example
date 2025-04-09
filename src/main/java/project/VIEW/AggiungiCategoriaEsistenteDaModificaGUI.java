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
import project.CONTROLLER.Controller;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AggiungiCategoriaEsistenteDaModificaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField sceltaField;

	public AggiungiCategoriaEsistenteDaModificaGUI(Controller c, List<Categoria> listaCategorie, Materiale materiale) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaCategoriaEsistenteGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		Vector<String> colonne = new Vector<String>();
		colonne.add("Nome");
		colonne.add("Supercategoria");


		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(Categoria categoria: listaCategorie) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(categoria.getNome());
			if(categoria.getSupercategoria() == null)
				vector.add("");
			else
				vector.add(categoria.getSupercategoria().getNome());
			dati.add(vector);
		}
		contentPane.setLayout(null);

		table = new JTable(new DefaultTableModel(dati, colonne));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setLocation(10, 5);
		scrollpane.setSize(414, 213);

		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);

		sceltaField = new JTextField();
		sceltaField.setToolTipText("Scegliere la categoria da associare al materiale");
		sceltaField.setFont(new Font("Arial", Font.PLAIN, 12));
		sceltaField.setBounds(126, 229, 176, 34);
		contentPane.add(sceltaField);
		sceltaField.setColumns(10);

		JLabel sceltaLabel = new JLabel("Categoria scelta:");
		sceltaLabel.setOpaque(true);
		sceltaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		sceltaLabel.setBounds(10, 229, 161, 34);
		contentPane.add(sceltaLabel);

		JButton confermaButton = new JButton("ASSOCIA");
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Categoria categoria: listaCategorie) {
					if(categoria.getNome().equals(sceltaField.getText())) {
						controller.associaCategoriaEsistente(materiale, categoria);
						sceltaField.setText("");
						return;
					}
				}
				sceltaField.setText("");
				controller.openDialog("Titolo non valido(titolo assente o che causerebbe ciclita");
			}

		});
		confermaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		confermaButton.setBounds(312, 229, 112, 35);
		contentPane.add(confermaButton);

		JButton esciButton = new JButton("INDIETRO");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openAggiungiCategoriaDaModifica(materiale);
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esciButton.setBounds(312, 269, 112, 35);
		contentPane.add(esciButton);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 434, 315);
		contentPane.add(imageLabel);

	}
}
