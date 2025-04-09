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

public class VisualizzaDettagliLibroGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField isbnField;
	private JTextField editoreField;
	private JTextField dateField;
	private JTextField genereField;
	private JTextField pagineField;

	public VisualizzaDettagliLibroGUI(Controller c, RigaTabellaLibro rigaLibro) {
		setType(Type.POPUP);

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel modificaLabel = new JLabel("DETTAGLI DI \""+ rigaLibro.getLibro().getTitolo()+"\"");
		modificaLabel.setOpaque(true);
		modificaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		modificaLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		modificaLabel.setBounds(10, 11, 539, 41);
		contentPane.add(modificaLabel);

		JLabel titoloLabel = new JLabel("Titolo:");
		titoloLabel.setBackground(Color.WHITE);
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloLabel.setBounds(10, 63, 62, 27);
		contentPane.add(titoloLabel);

		titoloField = new JTextField();
		titoloField.setEditable(false);
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
		doiField.setEditable(false);
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

		JLabel isbnLabel = new JLabel("ISBN:");
		isbnLabel.setBackground(Color.WHITE);
		isbnLabel.setOpaque(true);
		isbnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		isbnLabel.setBounds(10, 158, 62, 27);
		contentPane.add(isbnLabel);

	    isbnField = new JTextField();
	    isbnField.setEditable(false);
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



		JLabel npagineLabel = new JLabel("N\u00B0Pagine:");
		npagineLabel.setBackground(Color.WHITE);
		npagineLabel.setOpaque(true);
		npagineLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		npagineLabel.setBounds(10, 234, 79, 27);
		contentPane.add(npagineLabel);


		JLabel editoreLabel = new JLabel("Editore:");
		editoreLabel.setBackground(Color.WHITE);
		editoreLabel.setOpaque(true);
		editoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		editoreLabel.setBounds(10, 272, 62, 27);
		contentPane.add(editoreLabel);

		editoreField = new JTextField();
		editoreField.setEditable(false);
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
		descrizioneArea.setEditable(false);
		descrizioneArea.setText(rigaLibro.getLibro().getDescrizione());
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		JLabel dataLabel = new JLabel("Data di pubblicazione:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 328, 155, 27);
		contentPane.add(dataLabel);

		JButton paroleButton = new JButton("PAROLE CHIAVE");
		paroleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openParoleChiaveAssociate(rigaLibro.getLibro().getTitolo());
			}
		});
		paroleButton.setToolTipText("parole chiave del libro");
		paroleButton.setFont(new Font("Arial", Font.PLAIN, 14));
		paroleButton.setBounds(10, 384, 155, 38);
		contentPane.add(paroleButton);

		JButton btnCategorie = new JButton("CATEGORIE");
		btnCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCategorieAssociate(rigaLibro.getLibro().getTitolo());
			}
		});
		btnCategorie.setToolTipText("categorie del libro");
		btnCategorie.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCategorie.setBounds(202, 384, 155, 38);
		contentPane.add(btnCategorie);

		JButton btnCitazioni = new JButton("CITAZIONI");
		btnCitazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openCitazioni(rigaLibro.getLibro().getTitolo());
			}
		});
		btnCitazioni.setToolTipText("citazioni da/per il libro");
		btnCitazioni.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCitazioni.setBounds(388, 384, 155, 38);
		contentPane.add(btnCitazioni);

		dateField = new JTextField();
		dateField.setEditable(false);
		dateField.setFont(new Font("Arial", Font.PLAIN, 12));
		if(rigaLibro.getPubblicazione().getDataPubblicazione() != null)
			dateField.setText(rigaLibro.getPubblicazione().getDataPubblicazione().toString());
		dateField.setBounds(175, 328, 105, 27);
		contentPane.add(dateField);
		dateField.setColumns(10);

		genereField = new JTextField();
		genereField.setToolTipText("");
		if(rigaLibro.getLibro().getGenere().toString().equals("non_definito"))
			genereField.setText("non definito");
		else
			genereField.setText(rigaLibro.getLibro().getGenere().toString());
		genereField.setFont(new Font("Arial", Font.PLAIN, 12));
		genereField.setEditable(false);
		genereField.setColumns(10);
		genereField.setBounds(87, 197, 193, 27);
		contentPane.add(genereField);

		pagineField = new JTextField();
		pagineField.setToolTipText("");
		pagineField.setText(rigaLibro.getLibro().getNpagine().toString());
		pagineField.setFont(new Font("Arial", Font.PLAIN, 12));
		pagineField.setEditable(false);
		pagineField.setColumns(10);
		pagineField.setBounds(99, 234, 181, 27);
		contentPane.add(pagineField);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 559, 433);
		contentPane.add(imageLabel);

	}

}
