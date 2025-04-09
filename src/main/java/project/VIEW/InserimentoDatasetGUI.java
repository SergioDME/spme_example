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
import project.MODEL.Libro;
import project.CONTROLLER.Controller;
import project.enumerazioni.GENERE_ENUM;

public class InserimentoDatasetGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloField;
	private JTextField doiField;
	private JTextField urlField;

	public InserimentoDatasetGUI(Controller c) {

		controller = c;

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel inserisciLabel = new JLabel("INSERISCI DATI:");
		inserisciLabel.setOpaque(true);
		inserisciLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inserisciLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		inserisciLabel.setBounds(10, 11, 539, 41);
		contentPane.add(inserisciLabel);

		JLabel titoloLabel = new JLabel("Titolo*:");
		titoloLabel.setBackground(Color.WHITE);
		titoloLabel.setOpaque(true);
		titoloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloLabel.setBounds(10, 63, 62, 27);
		contentPane.add(titoloLabel);

		titoloField = new JTextField();
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
		descrizioneScrollPane.setViewportView(descrizioneArea);
		descrizioneArea.setLineWrap(true);

		JLabel obbligatoriLabel = new JLabel("* = obbligatori");
		obbligatoriLabel.setBackground(Color.WHITE);
		obbligatoriLabel.setOpaque(true);
		obbligatoriLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		obbligatoriLabel.setBounds(259, 329, 107, 33);
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
		contentPane.add(dateChooser);

		JLabel dataLabel = new JLabel("Data di pubblicazione*:");
		dataLabel.setOpaque(true);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataLabel.setBackground(Color.WHITE);
		dataLabel.setBounds(10, 291, 155, 27);
		contentPane.add(dataLabel);

		JButton confermaButton = new JButton("CONFERMA");
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

				Dataset dataset = new Dataset(titoloField.getText(), doiField.getText(), urlField.getText(), ncolonne, nrighe, descrizioneArea.getText());

				if(!(dateChooser.getDate() == null)) {
					if(controller.inserisciDataset(dataset)) {
						controller.inserisciPubblicazione(dataset.getTitolo(), controller.getAutore().getOrcid(), dateChooser.getDate());
						controller.chiediParolaChiave(dataset);


						pulisciCampi();
					}
				}
				else
					controller.openDialog("Data mancante");
			}

			private void pulisciCampi() {
				titoloField.setText("");
				doiField.setText("");
				urlField.setText("");
				descrizioneArea.setText("");
				ncolonneSpinner.setValue(1);
				nrigheSpinner.setValue(0);
				dateChooser.setDate(null);
			}

		});
		confermaButton.setBounds(376, 330, 173, 33);
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
				controller.openInsesciMaterialeGUI();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(10, 339, 48, 23);
		contentPane.add(backButton);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(-151, -36, 809, 528);
		contentPane.add(imageLabel);
	}
}
