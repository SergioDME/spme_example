package project.VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import project.MODEL.Libro;
import project.CONTROLLER.Controller;
import project.enumerazioni.GENERE_ENUM;
import project.struttureDiAppoggio.RigaTabellaLibro;

public class ModificaLibroGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField isbnField;
	private JTextField editoreField;

	public ModificaLibroGUI(Controller c, RigaTabellaLibro rigaLibro) {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel modificaLabel = new JLabel("MODIFICA DATI:");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 539, 41);
		contentPane.add(modificaLabel);

		JLabel titoloLabel = new JLabel("Titolo*:");
		titoloLabel.setBackground(Color.WHITE);
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloLabel.setBounds(10, 63, 62, 27);
		contentPane.add(titoloLabel);

		titoloField = new JTextField();
		titoloField.setText(rigaLibro.getLibro().getTitolo());
		titoloField.setFont(new Font("Arial", Font.PLAIN, 12));
	    titoloField.setBounds(87, 64, 193, 27);
	    contentPane.add(titoloField);
	    titoloField.setColumns(10);

		JScrollBar titoloScrollBar = new JScrollBar();
		titoloScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloScrollBar.setBounds(87, 90, 193, 17);
		BoundedRangeModel brmTitolo = titoloField.getHorizontalVisibility();
		titoloScrollBar.setModel(brmTitolo);
	    contentPane.add(titoloScrollBar);

		JLabel doiLabel = new JLabel("DOI:");
		doiLabel.setBackground(Color.WHITE);
		doiLabel.setOpaque(true);
		doiLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		doiLabel.setBounds(10, 111, 62, 27);
		contentPane.add(doiLabel);

		doiField = new JTextField();
		doiField.setText(rigaLibro.getLibro().getDoi());
		doiField.setToolTipText("solo caratteri numerici");
		doiField.setFont(new Font("Arial", Font.PLAIN, 12));
		doiField.setColumns(10);
	    doiField.setBounds(87, 112, 193, 27);
	    contentPane.add(doiField);

		JScrollBar doiScrollBar = new JScrollBar();
		doiScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		doiScrollBar.setBounds(87, 138, 193, 17);
		BoundedRangeModel brmDOI = doiField.getHorizontalVisibility();
		doiScrollBar.setModel(brmDOI);
	    contentPane.add(doiScrollBar);

		JLabel isbnLabel = new JLabel("ISBN*:");
		isbnLabel.setBackground(Color.WHITE);
		isbnLabel.setOpaque(true);
		isbnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		isbnLabel.setBounds(10, 158, 62, 27);
		contentPane.add(isbnLabel);

	    isbnField = new JTextField();
	    isbnField.setText(rigaLibro.getLibro().getIsbn());
	    isbnField.setToolTipText("13 caratteri numerici");
	    isbnField.setFont(new Font("Arial", Font.PLAIN, 12));
	    isbnField.setColumns(10);
	    isbnField.setBounds(87, 159, 193, 27);
	    contentPane.add(isbnField);

		JLabel genereLabel = new JLabel("Genere:");
		genereLabel.setBackground(Color.WHITE);
		genereLabel.setOpaque(true);
		genereLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		genereLabel.setBounds(10, 196, 62, 27);
		contentPane.add(genereLabel);

	    JComboBox genereComboBox = new JComboBox();
	    genereComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
	    genereComboBox.setBounds(87, 196, 193, 27);
	    contentPane.add(genereComboBox);

	    genereComboBox.addItem(new ComboBoxItem("(nessun genere)", "Value 0"));
	    genereComboBox.addItem(new ComboBoxItem("Fantascienza", "Value 1"));
	    genereComboBox.addItem(new ComboBoxItem("Fantasy", "Value 2"));
	    genereComboBox.addItem(new ComboBoxItem("Biografia", "Value 3"));
	    genereComboBox.addItem(new ComboBoxItem("Horror", "Value 4"));
	    genereComboBox.addItem(new ComboBoxItem("Giallo", "Value 5"));
	    genereComboBox.addItem(new ComboBoxItem("Romanzo Storico", "Value 6"));
	    genereComboBox.addItem(new ComboBoxItem("Romanzo Formazione", "Value 7"));
	    genereComboBox.addItem(new ComboBoxItem("Thriller", "Value 8"));
	    genereComboBox.addItem(new ComboBoxItem("Avventura e Azione", "Value 9"));
	    genereComboBox.addItem(new ComboBoxItem("Distopia", "Value 10"));
	    genereComboBox.addItem(new ComboBoxItem("Rosa", "Value 11"));
	    genereComboBox.addItem(new ComboBoxItem("Umoristico", "Value 12"));

	    if(rigaLibro.getLibro().getGenere() == null)
	    	genereComboBox.setSelectedIndex(0);
	    else
	    	genereComboBox.getModel().setSelectedItem(rigaLibro.getLibro().getGenere());




		JLabel npagineLabel = new JLabel("N\u00B0Pagine*:");
		npagineLabel.setBackground(Color.WHITE);
		npagineLabel.setOpaque(true);
		npagineLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		npagineLabel.setBounds(10, 234, 79, 27);
		contentPane.add(npagineLabel);

		int min = 1;
	    int max = 2000;
	    int step = 1;
	    int i = 1;
	    SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
	    JSpinner npagineSpinner = new JSpinner(value);
	    npagineSpinner.setValue(rigaLibro.getLibro().getNpagine());
	    npagineSpinner.setFont(new Font("Arial", Font.PLAIN, 12));
	    npagineSpinner.setBounds(97, 234, 62, 27);
	    contentPane.add(npagineSpinner);


		JLabel editoreLabel = new JLabel("Editore*:");
		editoreLabel.setBackground(Color.WHITE);
		editoreLabel.setOpaque(true);
		editoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		editoreLabel.setBounds(10, 272, 62, 27);
		contentPane.add(editoreLabel);

		editoreField = new JTextField();
		editoreField.setText(rigaLibro.getLibro().getEditore());
		editoreField.setFont(new Font("Arial", Font.PLAIN, 12));
	    editoreField.setColumns(10);
	    editoreField.setBounds(87, 272, 193, 27);
	    contentPane.add(editoreField);

		JScrollBar editoreScrollBar = new JScrollBar();
		editoreScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		editoreScrollBar.setBounds(87, 298, 193, 19);
		BoundedRangeModel brmEditore = editoreField.getHorizontalVisibility();
		editoreScrollBar.setModel(brmEditore);
		contentPane.add(editoreScrollBar);

		JLabel descrizioneLabel = new JLabel("Descrizione:");
		descrizioneLabel.setBackground(Color.WHITE);
		descrizioneLabel.setOpaque(true);
		descrizioneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		descrizioneLabel.setBounds(298, 63, 107, 27);
		contentPane.add(descrizioneLabel);

		JScrollPane descrizioneScrollPane = new JScrollPane();
		descrizioneScrollPane.setBounds(297, 90, 246, 209);
		contentPane.add(descrizioneScrollPane);

		JTextArea descrizioneArea = new JTextArea();
		descrizioneArea.setFont(new Font("Arial", Font.PLAIN, 12));
		descrizioneArea.setText(rigaLibro.getLibro().getDescrizione());
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		JLabel obbligatoriLabel = new JLabel("* = obbligatori");
		obbligatoriLabel.setBackground(Color.WHITE);
		obbligatoriLabel.setOpaque(true);
		obbligatoriLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		obbligatoriLabel.setBounds(259, 410, 107, 33);
		contentPane.add(obbligatoriLabel);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dateChooser.setToolTipText("deve essere antecedente alla data odierna");
		dateChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dateChooser.setBounds(175, 328, 107, 27);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		dateChooser.setSelectableDateRange(null, date);
		dateChooser.setDate(rigaLibro.getPubblicazione().getDataPubblicazione());
		contentPane.add(dateChooser);

		JLabel dataLabel = new JLabel("Data di pubblicazione*:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 328, 155, 27);
		contentPane.add(dataLabel);

		JButton modificaButton = new JButton("MODIFICA");
		modificaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		modificaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLibro(genereComboBox, npagineSpinner, descrizioneArea);

			}




			private void updateLibro(JComboBox genereComboBox, JSpinner npagineSpinner, JTextArea descrizioneArea) {
				try {
					npagineSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				Integer npagine = (Integer) npagineSpinner.getValue();
				GENERE_ENUM genere = selectGenere(genereComboBox);

				Libro libroModificato = new Libro(titoloField.getText(), doiField.getText(), isbnField.getText(), genere, npagine, editoreField.getText(), descrizioneArea.getText());

				if(java.util.Objects.equals(rigaLibro.getLibro().getTitolo(), libroModificato.getTitolo()) &&
						java.util.Objects.equals(rigaLibro.getLibro().getDoi(), libroModificato.getDoi()) &&
						java.util.Objects.equals(rigaLibro.getLibro().getDescrizione(), libroModificato.getDescrizione()) &&
						java.util.Objects.equals(rigaLibro.getLibro().getIsbn(), libroModificato.getIsbn()) &&
						java.util.Objects.equals(rigaLibro.getLibro().getEditore(), libroModificato.getEditore()) &&
						java.util.Objects.equals(rigaLibro.getLibro().getNpagine(), libroModificato.getNpagine()))
					controller.openDialog("funziona");


					if(!(dateChooser.getDate() == null)) {
						if(controller.updateLibro(libroModificato, rigaLibro.getLibro())) {
							if(!dateChooser.getDate().equals(rigaLibro.getPubblicazione().getDataPubblicazione()))
								controller.updatePubblicazione(rigaLibro.getLibro().getTitolo(), controller.getAutore().getOrcid(), dateChooser.getDate());

							dispose();
							setVisible(false);

							if(controller.getADMIN())
								controller.openModificaMaterialeADMIN();
							else
								controller.openModificaMateriale();
						}
					}
					else
						controller.openDialog("Data mancante");
				}




			private GENERE_ENUM selectGenere(JComboBox genereComboBox) {
				GENERE_ENUM genere = null;
				switch(genereComboBox.getSelectedItem().toString()) {
					case "Fantascienza": genere = GENERE_ENUM.fantascienza;
					 break;
					case "Fantasy": genere = GENERE_ENUM.fantasy;
					 break;
					case "Biografia": genere = GENERE_ENUM.biografia;
					 break;
					case "Horror": genere = GENERE_ENUM.horror;
					 break;
					case "Giallo": genere = GENERE_ENUM.giallo;
					 break;
					case "Romanzo Storico": genere = GENERE_ENUM.romanzoStorico;
					 break;
					case "Romanzo Formazione": genere = GENERE_ENUM.romanzoDiFormazione;
					 break;
					case "Thriller": genere = GENERE_ENUM.thriller;
					 break;
					case "Avventura e Azione": genere = GENERE_ENUM.avventuraEazione;
					 break;
					case "Distopia": genere = GENERE_ENUM.distopia;
					 break;
					case "Rosa": genere = GENERE_ENUM.rosa;
					 break;
					case "Umoristico": genere = GENERE_ENUM.umoristico;
					 break;
					default : genere = null;
				}
				return genere;
			}
		});
		modificaButton.setBounds(376, 410, 173, 33);
		contentPane.add(modificaButton);

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controller.getADMIN())
					controller.openModificaMaterialeADMIN();
				else
					controller.openModificaMateriale();
				dispose();
				setVisible(false);
			}
		});
		backButton.setFont(new Font("Arial", Font.BOLD, 12));
		backButton.setBounds(10, 415, 48, 23);
		contentPane.add(backButton);

		JButton btnParoleChiave = new JButton("PAROLE CHIAVE");
		btnParoleChiave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaParoleChiave(rigaLibro.getLibro());
			}
		});
		btnParoleChiave.setFont(new Font("Arial", Font.PLAIN, 14));
		btnParoleChiave.setBounds(10, 366, 173, 33);
		contentPane.add(btnParoleChiave);

		JButton btnCategorie = new JButton("CATEGORIE");
		btnCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaCategorie(rigaLibro.getLibro());
			}
		});
		btnCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCategorie.setBounds(193, 366, 173, 33);
		contentPane.add(btnCategorie);

		JButton btnCitazioni = new JButton("CITAZIONI");
		btnCitazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaCitazioni(rigaLibro.getLibro());
			}
		});
		btnCitazioni.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCitazioni.setBounds(376, 366, 173, 33);
		contentPane.add(btnCitazioni);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(-10, 0, 569, 471);
		contentPane.add(imageLabel);

	}
}
