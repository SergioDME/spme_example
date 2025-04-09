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
import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;
import project.struttureDiAppoggio.RigaTabellaLibro;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class VisualizzaArticoliSuConferenzaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField dettaglioField;

	public VisualizzaArticoliSuConferenzaGUI(Controller c, List<RigaTabellaArticoloSuConferenza> listaArticoloSuConferenza) {
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaArticoliSuConferenzaGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 465);
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
		colonne.add("Target");
		colonne.add("Titolo conferenza");
		colonne.add("Data conferenza");
		colonne.add("Sede");
		colonne.add("Casa editrice");
		colonne.add("Durata(minuti)");
		colonne.add("Citazioni ricevute");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(RigaTabellaArticoloSuConferenza rigaTabella: listaArticoloSuConferenza) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(rigaTabella.getAutore().getNome()+ " " + rigaTabella.getAutore().getCognome());
			vector.add(rigaTabella.getAutore().getOrcid());
			vector.add(rigaTabella.getPubblicazione().getDataPubblicazione());
			vector.add(rigaTabella.getPubblicazione().getDataInserimento());
			vector.add(rigaTabella.getArticoloSuConferenza().getTitolo());
			vector.add(rigaTabella.getArticoloSuConferenza().getDoi());
			vector.add(rigaTabella.getArticoloSuConferenza().getDescrizione());
			vector.add(rigaTabella.getArticoloSuConferenza().getTarget());

			vector.add(rigaTabella.getArticoloSuConferenza().getConferenza().getTitolo());
			vector.add(rigaTabella.getArticoloSuConferenza().getConferenza().getDataConferenza());
			vector.add(rigaTabella.getArticoloSuConferenza().getConferenza().getSede());
			vector.add(rigaTabella.getArticoloSuConferenza().getConferenza().getCasaEditrice());
			vector.add(rigaTabella.getArticoloSuConferenza().getConferenza().getDurata());

			vector.add(rigaTabella.getArticoloSuConferenza().getNumeroCitazioni());
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
		table.getColumnModel().getColumn(4).setPreferredWidth("titoloArticolo".length()*10);
		table.getColumnModel().getColumn(5).setPreferredWidth("doiArticolo".length()*15);
		table.getColumnModel().getColumn(6).setPreferredWidth("descrizione".length()*10);
		table.getColumnModel().getColumn(7).setPreferredWidth("target".length()*10);
		table.getColumnModel().getColumn(8).setPreferredWidth("titoloConf".length()*10);
		table.getColumnModel().getColumn(9).setPreferredWidth("Data conferenza".length()*7);
		table.getColumnModel().getColumn(10).setPreferredWidth("sedeConf".length()*8);
		table.getColumnModel().getColumn(11).setPreferredWidth("Casa editrice".length()*7);
		table.getColumnModel().getColumn(12).setPreferredWidth("durata(minuti)".length()*7);
		table.getColumnModel().getColumn(13).setPreferredWidth("citazioni ricevute".length()*7);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setLocation(10, 5);
		scrollpane.setSize(924, 263);
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
		esciButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esciButton.setBounds(814, 377, 120, 38);
		contentPane.add(esciButton);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JButton libriButton = new JButton("LIBRI");
		libriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaLibri();
			}
		});
		libriButton.setToolTipText("mostra i libri nel dettaglio");
		libriButton.setFont(new Font("Arial", Font.PLAIN, 12));
		libriButton.setBounds(10, 328, 120, 38);
		contentPane.add(libriButton);

		JButton datasetButton = new JButton("DATASET");
		datasetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openVisualizzaDataset();
				dispose();
				setVisible(false);
			}
		});
		datasetButton.setToolTipText("mostra i dataset nel dettaglio");
		datasetButton.setFont(new Font("Arial", Font.PLAIN, 12));
		datasetButton.setBounds(140, 328, 131, 38);
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
		risorseOnlineButton.setFont(new Font("Arial", Font.PLAIN, 12));
		risorseOnlineButton.setBounds(281, 328, 171, 38);
		contentPane.add(risorseOnlineButton);

		JButton articoliConferenza = new JButton("ARTICOLI SU CONFERENZA");
		articoliConferenza.setEnabled(false);
		articoliConferenza.setToolTipText("mostra gli articoli su conferenza nel dettaglio");
		articoliConferenza.setFont(new Font("Arial", Font.PLAIN, 12));
		articoliConferenza.setBounds(10, 377, 233, 38);
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
		articoliRivistaButton.setFont(new Font("Arial", Font.PLAIN, 12));
		articoliRivistaButton.setBounds(253, 377, 199, 38);
		contentPane.add(articoliRivistaButton);

		JButton btnVisualizza = new JButton("VISUALIZZA:");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(RigaTabellaArticoloSuConferenza rigaTabella: listaArticoloSuConferenza) {
					if(rigaTabella.getArticoloSuConferenza().getTitolo().equals(dettaglioField.getText())) {
						controller.openDettagliArticoloSuConferenza(rigaTabella.getArticoloSuConferenza());
						return;
					}
				}
				controller.openDialog("Titolo non valido");
			}
		});
		btnVisualizza.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVisualizza.setBounds(10, 279, 120, 38);
		contentPane.add(btnVisualizza);

		dettaglioField = new JTextField();
		dettaglioField.setFont(new Font("Arial", Font.PLAIN, 12));
		dettaglioField.setToolTipText("Scrivere il titolo del materiale da visualizzare nel dettaglio");
		dettaglioField.setColumns(10);
		dettaglioField.setBounds(140, 279, 312, 38);
		contentPane.add(dettaglioField);

		JButton btnNewButton = new JButton("TUTTI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaLibreria();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(462, 377, 89, 38);
		contentPane.add(btnNewButton);


		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 952, 426);
		contentPane.add(imageLabel);
	}
}
