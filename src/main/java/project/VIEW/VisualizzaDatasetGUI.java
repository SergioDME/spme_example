package project.VIEW;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.CONTROLLER.Controller;
import project.struttureDiAppoggio.RigaTabellaDataset;
import project.struttureDiAppoggio.RigaTabellaLibro;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class VisualizzaDatasetGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField visualizzaField;

	public VisualizzaDatasetGUI(Controller c, List<RigaTabellaDataset> listaDataset) {
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaDatasetGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 497);
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
		colonne.add("Numero Colonne");
		colonne.add("Numero Righe");
		colonne.add("URL");
		colonne.add("Citazioni ricevute");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(RigaTabellaDataset rigaTabella: listaDataset) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(rigaTabella.getAutore().getNome()+ " " + rigaTabella.getAutore().getCognome());
			vector.add(rigaTabella.getAutore().getOrcid());
			vector.add(rigaTabella.getPubblicazione().getDataPubblicazione());
			vector.add(rigaTabella.getPubblicazione().getDataInserimento());
			vector.add(rigaTabella.getDataset().getTitolo());
			vector.add(rigaTabella.getDataset().getDoi());
			vector.add(rigaTabella.getDataset().getDescrizione());
			vector.add(rigaTabella.getDataset().getNcolonne());
			vector.add(rigaTabella.getDataset().getNrighe());
			vector.add(rigaTabella.getDataset().getUrl());
			vector.add(rigaTabella.getDataset().getNumeroCitazioni());
			dati.add(vector);
		}
		contentPane.setLayout(null);

		table = new JTable(new DefaultTableModel(dati, colonne));
		table.setEnabled(false);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(true);
		table.getColumnModel().getColumn(0).setPreferredWidth("123456789012345".length()*10);
		table.getColumnModel().getColumn(1).setPreferredWidth("1234567890123456".length()*8);
		table.getColumnModel().getColumn(2).setPreferredWidth("Data pubblicazione".length()*7);
		table.getColumnModel().getColumn(3).setPreferredWidth("Data inserimento".length()*7);
		table.getColumnModel().getColumn(4).setPreferredWidth("titoloDelDataset".length()*10);
		table.getColumnModel().getColumn(5).setPreferredWidth("doiDelDat".length()*15);
		table.getColumnModel().getColumn(6).setPreferredWidth("descrizione".length()*10);
		table.getColumnModel().getColumn(7).setPreferredWidth("NumeroColonne".length()*8);
		table.getColumnModel().getColumn(8).setPreferredWidth("NumeroRighe".length()*8);
		table.getColumnModel().getColumn(9).setPreferredWidth("URLDelDataset".length()*7);
		table.getColumnModel().getColumn(10).setPreferredWidth("citazioniRic".length()*7);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setLocation(10, 5);
		scrollpane.setSize(845, 295);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);

		JButton esciButton = new JButton("ESCI");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();

				if(controller.getADMIN()) {
					controller.openPortaleAmministratore();
					return;
				}

				if(controller.getAutore() == null) {
					controller.openHome();
					return;
				}

				controller.openPortaleAutori();
				return;
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 14));
		esciButton.setBounds(735, 409, 120, 38);
		contentPane.add(esciButton);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton libriButton = new JButton("LIBRI");
		libriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openVisualizzaLibri();
				dispose();
				setVisible(false);
			}
		});
		libriButton.setToolTipText("mostra i libri nel dettaglio");
		libriButton.setFont(new Font("Arial", Font.PLAIN, 14));
		libriButton.setBounds(10, 360, 120, 38);
		contentPane.add(libriButton);

		JButton datasetButton = new JButton("DATASET");
		datasetButton.setEnabled(false);
		datasetButton.setToolTipText("mostra i dataset nel dettaglio");
		datasetButton.setFont(new Font("Arial", Font.PLAIN, 14));
		datasetButton.setBounds(140, 360, 131, 38);
		contentPane.add(datasetButton);

		JButton risorseOnlineButton = new JButton("RISORSE ONLINE");
		risorseOnlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openVisualizzaRisorsaOnline();
				dispose();
				setVisible(false);
			}
		});
		risorseOnlineButton.setToolTipText("mostra le risorse online nel dettaglio");
		risorseOnlineButton.setFont(new Font("Arial", Font.PLAIN, 14));
		risorseOnlineButton.setBounds(281, 360, 171, 38);
		contentPane.add(risorseOnlineButton);

		JButton articoliConferenza = new JButton("ARTICOLI SU CONFERENZA");
		articoliConferenza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaArticoloSuConferenza();
			}
		});
		articoliConferenza.setToolTipText("mostra gli articoli su conferenza nel dettaglio");
		articoliConferenza.setFont(new Font("Arial", Font.PLAIN, 14));
		articoliConferenza.setBounds(10, 409, 233, 38);
		contentPane.add(articoliConferenza);

		JButton articoliRivistaButton = new JButton("ARTICOLI SU RIVISTA ");
		articoliRivistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaArticoloSuRivista();
			}
		});
		articoliRivistaButton.setToolTipText("mostra gli articoli su rivista nel dettaglio");
		articoliRivistaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		articoliRivistaButton.setBounds(253, 409, 199, 38);
		contentPane.add(articoliRivistaButton);

		JButton btnVisualizza = new JButton("VISUALIZZA:");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(RigaTabellaDataset rigaTabella: listaDataset) {
					if(rigaTabella.getDataset().getTitolo().equals(visualizzaField.getText())) {
						controller.openDettagliDataset(rigaTabella.getDataset());
						return;
					}
				}
				controller.openDialog("Titolo non valido");
			}
		});

		btnVisualizza.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizza.setBounds(10, 311, 120, 38);
		contentPane.add(btnVisualizza);

		visualizzaField = new JTextField();
		visualizzaField.setFont(new Font("Arial", Font.PLAIN, 12));
		visualizzaField.setToolTipText("Scrivere il titolo del materiale da visualizzare nel dettaglio");
		visualizzaField.setColumns(10);
		visualizzaField.setBounds(140, 311, 312, 38);
		contentPane.add(visualizzaField);

		JButton btnNewButton = new JButton("TUTTI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaLibreria();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(462, 409, 89, 38);
		contentPane.add(btnNewButton);


		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 879, 465);
		contentPane.add(imageLabel);
	}
}
