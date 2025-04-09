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

import project.MODEL.ArticoloSuConferenza;
import project.MODEL.Conferenza;
import project.MODEL.Libro;
import project.CONTROLLER.Controller;
import project.enumerazioni.GENERE_ENUM;

public class InserimentoArticoloSuConferenzaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloArticoloField;
	private JTextField doiField;
	private JTextField targetField;
	private JTextField casaEditriceField;
	private JTextField titoloConferenzaField;
	private JTextField sedeField;

	public InserimentoArticoloSuConferenzaGUI(Controller c) {

		controller = c;

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InserimentoLibroGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel inserisciLabel = new JLabel("INSERISCI DATI ARTICOLO:");
		inserisciLabel.setOpaque(true);
		inserisciLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inserisciLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		inserisciLabel.setBounds(10, 11, 531, 41);
		contentPane.add(inserisciLabel);

		JLabel titoloArticoloLabel = new JLabel("Titolo*:");
		titoloArticoloLabel.setBackground(Color.WHITE);
		titoloArticoloLabel.setOpaque(true);
		titoloArticoloLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloArticoloLabel.setBounds(10, 63, 62, 27);
		contentPane.add(titoloArticoloLabel);

		titoloArticoloField = new JTextField();
		titoloArticoloField.setToolTipText("Titolo dell'articolo");
		titoloArticoloField.setFont(new Font("Arial", Font.PLAIN, 12));
	    titoloArticoloField.setBounds(87, 64, 181, 27);
	    contentPane.add(titoloArticoloField);
	    titoloArticoloField.setColumns(10);

		JScrollBar titoloArticoloScrollBar = new JScrollBar();
		titoloArticoloScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloArticoloScrollBar.setBounds(87, 90, 181, 17);
		BoundedRangeModel brmTitolo = titoloArticoloField.getHorizontalVisibility();
		titoloArticoloScrollBar.setModel(brmTitolo);
	    contentPane.add(titoloArticoloScrollBar);

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
	    doiField.setBounds(87, 112, 181, 27);
	    contentPane.add(doiField);

		JScrollBar doiScrollBar = new JScrollBar();
		doiScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		doiScrollBar.setBounds(87, 138, 181, 17);
		BoundedRangeModel brmDOI = doiField.getHorizontalVisibility();
		doiScrollBar.setModel(brmDOI);
	    contentPane.add(doiScrollBar);

		JLabel targetLabel = new JLabel("Target:");
		targetLabel.setBackground(Color.WHITE);
		targetLabel.setOpaque(true);
		targetLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		targetLabel.setBounds(10, 166, 62, 27);
		contentPane.add(targetLabel);

		targetField = new JTextField();
		targetField.setToolTipText("gruppo di persone a cui \u00E8 destinato l'articolo");
		targetField.setFont(new Font("Arial", Font.PLAIN, 12));
	    targetField.setColumns(10);
	    targetField.setBounds(87, 166, 181, 27);
	    contentPane.add(targetField);

		JScrollBar targetScrollBar = new JScrollBar();
		targetScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		targetScrollBar.setBounds(87, 195, 181, 19);
		BoundedRangeModel brmTarget = targetField.getHorizontalVisibility();
		targetScrollBar.setModel(brmTarget);
		contentPane.add(targetScrollBar);

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
		obbligatoriLabel.setBounds(566, 303, 99, 33);
		contentPane.add(obbligatoriLabel);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/conferenza.jfif"));

		JDateChooser dataPubblicazioneChooser = new JDateChooser();
		dataPubblicazioneChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dataPubblicazioneChooser.setBounds(175, 225, 93, 27);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		dataPubblicazioneChooser.setSelectableDateRange(null, date);
		contentPane.add(dataPubblicazioneChooser);

		JLabel dataPubblicazioneLabel = new JLabel("Data di pubblicazione*:");
		dataPubblicazioneLabel.setOpaque(true);
		dataPubblicazioneLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataPubblicazioneLabel.setBackground(Color.WHITE);
		dataPubblicazioneLabel.setBounds(10, 225, 155, 27);
		contentPane.add(dataPubblicazioneLabel);

		JLabel casaEditriceLabel = new JLabel("Casa editrice:");
		casaEditriceLabel.setOpaque(true);
		casaEditriceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		casaEditriceLabel.setBackground(Color.WHITE);
		casaEditriceLabel.setBounds(553, 111, 112, 27);
		contentPane.add(casaEditriceLabel);

		casaEditriceField = new JTextField();
		casaEditriceField.setToolTipText("Casa editrice che ha organizzato la conferenza");
		casaEditriceField.setFont(new Font("Arial", Font.PLAIN, 12));
		casaEditriceField.setColumns(10);
		casaEditriceField.setBounds(675, 111, 181, 27);
		contentPane.add(casaEditriceField);

		JLabel lblInserisciDatiConferenza = new JLabel("CONFERENZA:");
		lblInserisciDatiConferenza.setOpaque(true);
		lblInserisciDatiConferenza.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciDatiConferenza.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblInserisciDatiConferenza.setBounds(551, 11, 305, 41);
		contentPane.add(lblInserisciDatiConferenza);

		JLabel dataConferenzaLabel = new JLabel("Data conferenza*:");
		dataConferenzaLabel.setOpaque(true);
		dataConferenzaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		dataConferenzaLabel.setBackground(Color.WHITE);
		dataConferenzaLabel.setBounds(553, 253, 155, 27);
		contentPane.add(dataConferenzaLabel);

		JLabel titoloConferenzaLabel = new JLabel("Titolo*:");
		titoloConferenzaLabel.setOpaque(true);
		titoloConferenzaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloConferenzaLabel.setBackground(Color.WHITE);
		titoloConferenzaLabel.setBounds(553, 63, 62, 27);
		contentPane.add(titoloConferenzaLabel);

		titoloConferenzaField = new JTextField();
		titoloConferenzaField.setToolTipText("Titolo della conferenza");
		titoloConferenzaField.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloConferenzaField.setColumns(10);
		titoloConferenzaField.setBounds(675, 63, 181, 27);
		contentPane.add(titoloConferenzaField);

		JDateChooser dataConferenzaChooser = new JDateChooser();
		dataConferenzaChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dataConferenzaChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dataConferenzaChooser.setToolTipText("deve essere antecedente alla data odierna");
		dataConferenzaChooser.setBounds(763, 253, 93, 27);
		dataConferenzaChooser.setSelectableDateRange(null, date);
		contentPane.add(dataConferenzaChooser);

		JScrollBar titoloConferenzaScrollBar = new JScrollBar();
		titoloConferenzaScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloConferenzaScrollBar.setBounds(675, 90, 181, 17);
		BoundedRangeModel brmTitoloConferenza= titoloConferenzaField.getHorizontalVisibility();
		titoloConferenzaScrollBar.setModel(brmTitoloConferenza);
		contentPane.add(titoloConferenzaScrollBar);

		sedeField = new JTextField();
		sedeField.setToolTipText("Sede ospitante la conferenza");
		sedeField.setFont(new Font("Arial", Font.PLAIN, 12));
		sedeField.setColumns(10);
		sedeField.setBounds(675, 159, 181, 27);
		contentPane.add(sedeField);

		JLabel sedeLabel = new JLabel("Sede:");
		sedeLabel.setOpaque(true);
		sedeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		sedeLabel.setBackground(Color.WHITE);
		sedeLabel.setBounds(553, 158, 49, 27);
		contentPane.add(sedeLabel);

		JScrollBar casaEditriceScrollBar = new JScrollBar();
		casaEditriceScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		casaEditriceScrollBar.setBounds(675, 138, 181, 17);
		BoundedRangeModel brmCasaEditrice = casaEditriceField.getHorizontalVisibility();
		casaEditriceScrollBar.setModel(brmCasaEditrice);
		contentPane.add(casaEditriceScrollBar);

		JScrollBar sedeScrollBar = new JScrollBar();
		sedeScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		sedeScrollBar.setBounds(675, 185, 181, 17);
		BoundedRangeModel brmSede = sedeField.getHorizontalVisibility();
		sedeScrollBar.setModel(brmSede);
		contentPane.add(sedeScrollBar);

		JLabel durataLabel = new JLabel("Durata(in minuti):");
		durataLabel.setOpaque(true);
		durataLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		durataLabel.setBackground(Color.WHITE);
		durataLabel.setBounds(553, 215, 112, 27);
		contentPane.add(durataLabel);

		int min = 1;
	    int max = 2000;
	    int step = 1;
	    int i = 1;
	    SpinnerModel value = new SpinnerNumberModel(i, min, max, step);
		JSpinner durataSpinner = new JSpinner(value);
		durataSpinner.setToolTipText("inserire la durata in minuti della conferenza");
		durataSpinner.setFont(new Font("Arial", Font.PLAIN, 12));
		durataSpinner.setBounds(763, 213, 93, 29);
		contentPane.add(durataSpinner);


		JButton confermaButton = new JButton("CONFERMA");
		confermaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					durataSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				Integer durata = (Integer) durataSpinner.getValue();

				Conferenza conferenza = new Conferenza(titoloConferenzaField.getText(), durata, sedeField.getText(), casaEditriceField.getText(), dataConferenzaChooser.getDate());
 				ArticoloSuConferenza articoloSuConferenza = new ArticoloSuConferenza(titoloArticoloField.getText(), doiField.getText(), targetField.getText(), descrizioneArea.getText(), conferenza);

 				if(!(dataPubblicazioneChooser.getDate() == null)) {
					if(controller.inserisciArticoloSuConferenza(articoloSuConferenza)) {
						controller.inserisciPubblicazione(articoloSuConferenza.getTitolo(), controller.getAutore().getOrcid(), dataPubblicazioneChooser.getDate());
						controller.chiediParolaChiave(articoloSuConferenza);


						pulisciCampi();
					}
 				}
 				else
 					controller.openDialog("Data di pubblicazione mancante");
			}


			private void pulisciCampi() {
				titoloArticoloField.setText("");
				doiField.setText("");
				targetField.setText("");
				descrizioneArea.setText("");
				dataPubblicazioneChooser.setDate(null);
				dataConferenzaChooser.setDate(null);
				durataSpinner.setValue(1);
				titoloConferenzaField.setText("");
				sedeField.setText("");
				casaEditriceField.setText("");
			}

		});
		confermaButton.setBounds(683, 304, 173, 33);
		contentPane.add(confermaButton);

		JButton backButton = new JButton("\u2190");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openInsesciMaterialeGUI();
			}
		});
		backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(10, 313, 48, 23);
		contentPane.add(backButton);

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 882, 363);
		contentPane.add(imageLabel);


	}
}
