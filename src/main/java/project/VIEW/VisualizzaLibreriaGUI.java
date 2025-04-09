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

public class VisualizzaLibreriaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTable table;
	private JTextField dettaglioField;

	public VisualizzaLibreriaGUI(Controller c, List<RigaTabellaMateriale> listaMateriali) {
		setTitle("Library managment system");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaLibreriaGUI.class.getResource("/GUI/libri.png")));
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 465);
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
		colonne.add("Citazioni ricevute");

		Vector<Vector<Object>> dati = new Vector<Vector<Object>>();

		for(RigaTabellaMateriale rigaTabella: listaMateriali) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(rigaTabella.getAutore().getNome()+ " "+ rigaTabella.getAutore().getCognome());
			vector.add(rigaTabella.getAutore().getOrcid());

			vector.add(rigaTabella.getPubblicazione().getDataPubblicazione());
			vector.add(rigaTabella.getPubblicazione().getDataInserimento());


			vector.add(rigaTabella.getMateriale().getTitolo());
			vector.add(rigaTabella.getMateriale().getDoi());
			vector.add(rigaTabella.getMateriale().getDescrizione());
			vector.add(rigaTabella.getMateriale().getTipo());

			vector.add(rigaTabella.getMateriale().getNumeroCitazioni());
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
		table.getColumnModel().getColumn(4).setPreferredWidth("titolodelmateriale".length()*10);
		table.getColumnModel().getColumn(5).setPreferredWidth("doidelmate".length()*15);
		table.getColumnModel().getColumn(6).setPreferredWidth("descrizionedelmate".length()*10);
		table.getColumnModel().getColumn(7).setPreferredWidth("ArticoloSuConferenza".length()*7);
		table.getColumnModel().getColumn(8).setPreferredWidth("citazioni ricevute".length()*7);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBounds(114, 11, 425, 347);

		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setLocation(10, 5);
		scrollpane.setSize(845, 263);
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
		esciButton.setBounds(735, 377, 120, 38);
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
		libriButton.setFont(new Font("Arial", Font.PLAIN, 14));
		libriButton.setBounds(10, 328, 120, 38);
		contentPane.add(libriButton);

		JButton datasetButton = new JButton("DATASET");
		datasetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaDataset();
			}
		});
		datasetButton.setToolTipText("mostra i dataset nel dettaglio");
		datasetButton.setFont(new Font("Arial", Font.PLAIN, 14));
		datasetButton.setBounds(140, 328, 131, 38);
		contentPane.add(datasetButton);

		JButton risorseOnlineButton = new JButton("RISORSE ONLINE");
		risorseOnlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				controller.openVisualizzaRisorsaOnline();
			}
		});
		risorseOnlineButton.setToolTipText("mostra le risorse online nel dettaglio");
		risorseOnlineButton.setFont(new Font("Arial", Font.PLAIN, 14));
		risorseOnlineButton.setBounds(281, 328, 171, 38);
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
		articoliRivistaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		articoliRivistaButton.setBounds(253, 377, 199, 38);
		contentPane.add(articoliRivistaButton);

		dettaglioField = new JTextField();
		dettaglioField.setFont(new Font("Arial", Font.PLAIN, 12));
		dettaglioField.setToolTipText("Scrivere il titolo del materiale da visualizzare nel dettaglio");
		dettaglioField.setBounds(140, 279, 312, 38);
		contentPane.add(dettaglioField);
		dettaglioField.setColumns(10);

		JButton btnVisualizza = new JButton("VISUALIZZA:");
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(RigaTabellaMateriale rigaTabella: listaMateriali){
					if(rigaTabella.getMateriale().getTitolo().equals(dettaglioField.getText())) {
						controller.openDettagliMateriale(rigaTabella.getMateriale());
						return;
					}
				}
				controller.openDialog("Titolo non valido");
			}
		});
		btnVisualizza.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVisualizza.setBounds(10, 279, 120, 38);
		contentPane.add(btnVisualizza);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 865, 426);
		contentPane.add(imageLabel);
	}
}
