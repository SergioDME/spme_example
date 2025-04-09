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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import project.MODEL.Libro;
import project.CONTROLLER.Controller;

import project.enumerazioni.GENERE_ENUM;
import project.struttureDiAppoggio.RigaTabellaLibro;
import project.struttureDiAppoggio.RigaTabellaRisorsaOnline;

public class VisualizzaDettagliRisorsaOnlineGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField urlField;
	private JTextField dateField;

	public VisualizzaDettagliRisorsaOnlineGUI(Controller c, RigaTabellaRisorsaOnline rigaRisorsaOnline) {
		setType(Type.POPUP);

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel modificaLabel = new JLabel("DETTAGLI DI \""+ rigaRisorsaOnline.getRisorsaOnline().getTitolo()+"\"");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 539, 41);
		contentPane.add(modificaLabel);

		JLabel titoloLabel = new JLabel("Titolo:");
		titoloLabel.setBackground(Color.WHITE);
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		titoloLabel.setBounds(10, 63, 62, 27);
		contentPane.add(titoloLabel);

		titoloField = new JTextField();
		titoloField.setEditable(false);
		titoloField.setText(rigaRisorsaOnline.getRisorsaOnline().getTitolo());
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
		doiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		doiLabel.setBounds(10, 111, 62, 27);
		contentPane.add(doiLabel);

		doiField = new JTextField();
		doiField.setEditable(false);
		doiField.setText(rigaRisorsaOnline.getRisorsaOnline().getDoi());
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

		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setBackground(Color.WHITE);
		urlLabel.setOpaque(true);
		urlLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		urlLabel.setBounds(10, 158, 62, 27);
		contentPane.add(urlLabel);

	    urlField = new JTextField();
	    urlField.setEditable(false);
	    urlField.setText(rigaRisorsaOnline.getRisorsaOnline().getUrl());
	    urlField.setToolTipText("13 caratteri numerici");
	    urlField.setFont(new Font("Arial", Font.PLAIN, 12));
	    urlField.setColumns(10);
	    urlField.setBounds(87, 159, 193, 27);
	    contentPane.add(urlField);


	    JLabel descrizioneLabel = new JLabel("Descrizione:");
		descrizioneLabel.setBackground(Color.WHITE);
		descrizioneLabel.setOpaque(true);
		descrizioneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		descrizioneLabel.setBounds(298, 63, 107, 27);
		contentPane.add(descrizioneLabel);

		JScrollPane descrizioneScrollPane = new JScrollPane();
		descrizioneScrollPane.setBounds(297, 90, 246, 209);
		contentPane.add(descrizioneScrollPane);

		JTextArea descrizioneArea = new JTextArea();
		descrizioneArea.setFont(new Font("Arial", Font.PLAIN, 14));
		descrizioneArea.setEditable(false);
		descrizioneArea.setText(rigaRisorsaOnline.getRisorsaOnline().getDescrizione());
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/risorsaonline.jfif"));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		JLabel dataLabel = new JLabel("Data di pubblicazione:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 215, 155, 27);
		contentPane.add(dataLabel);

		JButton paroleButton = new JButton("PAROLE CHIAVE");
		paroleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openParoleChiaveAssociate(rigaRisorsaOnline.getRisorsaOnline().getTitolo());
			}
		});
		paroleButton.setToolTipText("parole chiave della risorsa online");
		paroleButton.setFont(new Font("Arial", Font.PLAIN, 14));
		paroleButton.setBounds(10, 328, 155, 38);
		contentPane.add(paroleButton);

		JButton btnCategorie = new JButton("CATEGORIE");
		btnCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCategorieAssociate(rigaRisorsaOnline.getRisorsaOnline().getTitolo());
			}
		});
		btnCategorie.setToolTipText("categorie associate alla risorsa online");
		btnCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCategorie.setBounds(202, 328, 155, 38);
		contentPane.add(btnCategorie);

		JButton btnCitazioni = new JButton("CITAZIONI");
		btnCitazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCitazioni(rigaRisorsaOnline.getRisorsaOnline().getTitolo());
			}
		});
		btnCitazioni.setToolTipText("citazioni da/per la risorsa online");
		btnCitazioni.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCitazioni.setBounds(394, 328, 155, 38);
		contentPane.add(btnCitazioni);

		dateField = new JTextField();
		dateField.setEditable(false);
		dateField.setFont(new Font("Arial", Font.PLAIN, 12));
		if(rigaRisorsaOnline.getPubblicazione().getDataPubblicazione() != null)
			dateField.setText(rigaRisorsaOnline.getPubblicazione().getDataPubblicazione().toString());
		dateField.setBounds(175, 215, 105, 27);
		contentPane.add(dateField);
		dateField.setColumns(10);



		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 559, 375);
		contentPane.add(imageLabel);

	}

}
