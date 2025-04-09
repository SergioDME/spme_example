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

import project.MODEL.Dataset;
import project.CONTROLLER.Controller;
import project.struttureDiAppoggio.RigaTabellaDataset;

public class ModificaDatasetGUI extends JFrame {


	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField urlField;

	public ModificaDatasetGUI(Controller c, RigaTabellaDataset rigaTabellaDataset) {

		controller = c;

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 457);
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
		titoloField.setText(rigaTabellaDataset.getDataset().getTitolo());
		titoloField.setEditable(true);
		titoloField.setFont(new Font("Arial", Font.PLAIN, 12));
	    titoloField.setBounds(87, 64, 200, 27);
	    contentPane.add(titoloField);
	    titoloField.setColumns(10);

		JScrollBar titoloScrollBar = new JScrollBar();
		titoloScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloScrollBar.setBounds(87, 90, 200, 17);
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
		doiField.setText(rigaTabellaDataset.getDataset().getDoi());
		doiField.setToolTipText("solo caratteri numerici");
		doiField.setFont(new Font("Arial", Font.PLAIN, 12));
		doiField.setColumns(10);
	    doiField.setBounds(87, 112, 200, 27);
	    contentPane.add(doiField);

		JScrollBar doiScrollBar = new JScrollBar();
		doiScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		doiScrollBar.setBounds(87, 138, 200, 17);
		BoundedRangeModel brmDOI = doiField.getHorizontalVisibility();
		doiScrollBar.setModel(brmDOI);
	    contentPane.add(doiScrollBar);

		JLabel urlLabel = new JLabel("URL*:");
		urlLabel.setBackground(Color.WHITE);
		urlLabel.setOpaque(true);
		urlLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		urlLabel.setBounds(10, 158, 62, 27);
		contentPane.add(urlLabel);

	    urlField = new JTextField();
	    urlField.setToolTipText("");
	    urlField.setFont(new Font("Arial", Font.PLAIN, 12));
	    urlField.setText(rigaTabellaDataset.getDataset().getUrl());
	    urlField.setColumns(10);
	    urlField.setBounds(87, 159, 200, 27);
	    contentPane.add(urlField);


		JLabel ncolonneLabel = new JLabel("N\u00B0Colonne*:");
		ncolonneLabel.setBackground(Color.WHITE);
		ncolonneLabel.setOpaque(true);
		ncolonneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		ncolonneLabel.setBounds(10, 215, 85, 27);
		contentPane.add(ncolonneLabel);



		int minColonne = 1;
	    int max = 2000;
	    int step = 1;
	    int iColonne = 1;
	    SpinnerModel valueColonne = new SpinnerNumberModel(iColonne, minColonne, max, step);
	    JSpinner ncolonneSpinner = new JSpinner(valueColonne);
	    ncolonneSpinner.setValue(rigaTabellaDataset.getDataset().getNcolonne());
	    ncolonneSpinner.setFont(new Font("Arial", Font.PLAIN, 12));
	    ncolonneSpinner.setBounds(103, 215, 62, 27);
	    contentPane.add(ncolonneSpinner);

		JLabel nrigheLabel = new JLabel("N\u00B0Righe*:");
		nrigheLabel.setOpaque(true);
		nrigheLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		nrigheLabel.setBackground(Color.WHITE);
		nrigheLabel.setBounds(10, 253, 85, 27);
		contentPane.add(nrigheLabel);

		int minRighe = 0;
		int iRighe = 0;
	    SpinnerModel valueRighe = new SpinnerNumberModel(iRighe, minRighe, max, step);
		JSpinner nrigheSpinner = new JSpinner(valueRighe);
		nrigheSpinner.setValue(rigaTabellaDataset.getDataset().getNrighe());
		nrigheSpinner.setFont(new Font("Arial", Font.PLAIN, 12));
		nrigheSpinner.setBounds(103, 253, 62, 27);
		contentPane.add(nrigheSpinner);

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
		descrizioneArea.setText(rigaTabellaDataset.getDataset().getDescrizione());
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		JLabel obbligatoriLabel = new JLabel("* = obbligatori");
		obbligatoriLabel.setBackground(Color.WHITE);
		obbligatoriLabel.setOpaque(true);
		obbligatoriLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		obbligatoriLabel.setBounds(259, 373, 107, 33);
		contentPane.add(obbligatoriLabel);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/dataset.jfif"));

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dateChooser.setToolTipText("deve essere antecedente alla data odierna");
		dateChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dateChooser.setBounds(175, 291, 112, 27);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		dateChooser.setSelectableDateRange(null, date);
		dateChooser.setDate(rigaTabellaDataset.getPubblicazione().getDataPubblicazione());
		contentPane.add(dateChooser);

		JLabel dataLabel = new JLabel("Data di pubblicazione*:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 291, 155, 27);
		contentPane.add(dataLabel);

		JButton confermaButton = new JButton("MODIFICA");
		confermaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserisciDataset(ncolonneSpinner, nrigheSpinner, descrizioneArea, dateChooser);
			}

			private void inserisciDataset(JSpinner ncolonneSpinner, JSpinner nrigheSpinner, JTextArea descrizioneArea,
					JDateChooser dateChooser) {
				try {
					ncolonneSpinner.commitEdit();
					nrigheSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				Integer ncolonne = (Integer) ncolonneSpinner.getValue();
				Integer nrighe = (Integer) nrigheSpinner.getValue();

				Dataset datasetModificato = new Dataset(titoloField.getText(), doiField.getText(), urlField.getText(), ncolonne, nrighe, descrizioneArea.getText());

				if(!(dateChooser.getDate() == null)) {
					if(controller.updateDataset(datasetModificato, rigaTabellaDataset.getDataset())) {
						if(!dateChooser.getDate().equals(rigaTabellaDataset.getPubblicazione().getDataPubblicazione()))
							controller.updatePubblicazione(rigaTabellaDataset.getDataset().getTitolo(), controller.getAutore().getOrcid(), dateChooser.getDate());

						dispose();
						setVisible(false);
						controller.openModificaMateriale();
					}
				}
				else
					controller.openDialog("Data mancante");
			}

		});
		confermaButton.setBounds(376, 373, 173, 33);
		contentPane.add(confermaButton);

		JScrollBar urlScrollBar = new JScrollBar();
		urlScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		urlScrollBar.setBounds(87, 187, 200, 17);
		BoundedRangeModel brmURL = urlField.getHorizontalVisibility();
		urlScrollBar.setModel(brmURL);
		contentPane.add(urlScrollBar);


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
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(10, 377, 48, 23);
		contentPane.add(backButton);

		JButton btnCitazioni = new JButton("CITAZIONI");
		btnCitazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaCitazioni(rigaTabellaDataset.getDataset());
			}
		});
		btnCitazioni.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCitazioni.setBounds(376, 329, 173, 33);
		contentPane.add(btnCitazioni);

		JButton btnCategorie = new JButton("CATEGORIE");
		btnCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaCitazioni(rigaTabellaDataset.getDataset());
			}
		});
		btnCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCategorie.setBounds(193, 329, 173, 33);
		contentPane.add(btnCategorie);

		JButton btnParoleChiave = new JButton("PAROLE CHIAVE");
		btnParoleChiave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openModificaParoleChiave(rigaTabellaDataset.getDataset());
			}
		});
		btnParoleChiave.setFont(new Font("Arial", Font.PLAIN, 14));
		btnParoleChiave.setBounds(10, 329, 173, 33);
		contentPane.add(btnParoleChiave);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 559, 445);
		contentPane.add(imageLabel);
	}
}
