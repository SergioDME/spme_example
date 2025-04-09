package project.VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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

public class VisualizzaDettagliDatasetGUI extends JFrame {


	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField urlField;

	public VisualizzaDettagliDatasetGUI(Controller c, RigaTabellaDataset rigaTabellaDataset) {
		setType(Type.POPUP);

		controller = c;

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel modificaLabel = new JLabel("DETTAGLI DI \""+ rigaTabellaDataset.getDataset().getTitolo()+"\"");
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
		titoloField.setEditable(false);
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
		doiField.setEditable(false);
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
	    urlField.setEditable(false);
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

		JLabel nrigheLabel = new JLabel("N\u00B0Righe*:");
		nrigheLabel.setOpaque(true);
		nrigheLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		nrigheLabel.setBackground(Color.WHITE);
		nrigheLabel.setBounds(10, 253, 85, 27);
		contentPane.add(nrigheLabel);

		int minRighe = 0;
		int iRighe = 0;
	    SpinnerModel valueRighe = new SpinnerNumberModel(iRighe, minRighe, max, step);

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
		descrizioneArea.setEditable(false);
		descrizioneArea.setText(rigaTabellaDataset.getDataset().getDescrizione());
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/dataset.jfif"));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		JLabel dataLabel = new JLabel("Data di pubblicazione:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 291, 155, 27);
		contentPane.add(dataLabel);

		JScrollBar urlScrollBar = new JScrollBar();
		urlScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		urlScrollBar.setBounds(87, 187, 200, 17);
		BoundedRangeModel brmURL = urlField.getHorizontalVisibility();
		urlScrollBar.setModel(brmURL);
		contentPane.add(urlScrollBar);

		JLabel colonneLabel = new JLabel("");
		colonneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		colonneLabel.setOpaque(true);
		colonneLabel.setText(rigaTabellaDataset.getDataset().getNcolonne().toString());
		colonneLabel.setBounds(105, 215, 182, 27);
		contentPane.add(colonneLabel);

		JLabel righeLabel = new JLabel("");
		righeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		righeLabel.setOpaque(true);
		righeLabel.setText(rigaTabellaDataset.getDataset().getNrighe().toString());
		righeLabel.setBounds(105, 253, 182, 27);
		contentPane.add(righeLabel);

		JLabel datalabel = new JLabel("");
		datalabel.setFont(new Font("Arial", Font.PLAIN, 12));
		datalabel.setOpaque(true);
		if(rigaTabellaDataset.getPubblicazione().getDataPubblicazione() != null)
			datalabel.setText(rigaTabellaDataset.getPubblicazione().getDataPubblicazione().toString());
		datalabel.setBounds(172, 291, 116, 27);
		contentPane.add(datalabel);

		JButton paroleButton = new JButton("PAROLE CHIAVE");
		paroleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openParoleChiaveAssociate(rigaTabellaDataset.getDataset().getTitolo());
			}
		});
		paroleButton.setToolTipText("parole chiave del dataset");
		paroleButton.setFont(new Font("Arial", Font.PLAIN, 14));
		paroleButton.setBounds(10, 351, 155, 38);
		contentPane.add(paroleButton);

		JButton btnCategorie = new JButton("CATEGORIE");
		btnCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCategorieAssociate(rigaTabellaDataset.getDataset().getTitolo());
			}
		});
		btnCategorie.setToolTipText("categorie associate al dataset");
		btnCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCategorie.setBounds(202, 351, 155, 38);
		contentPane.add(btnCategorie);

		JButton btnCitazioni = new JButton("CITAZIONI");
		btnCitazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCitazioni(rigaTabellaDataset.getDataset().getTitolo());
			}
		});
		btnCitazioni.setToolTipText("citazioni da/per il dataset");
		btnCitazioni.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCitazioni.setBounds(394, 351, 155, 38);
		contentPane.add(btnCitazioni);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 559, 400);
		contentPane.add(imageLabel);
	}
}
