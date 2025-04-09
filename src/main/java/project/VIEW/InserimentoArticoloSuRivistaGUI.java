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

import project.MODEL.ArticoloSuConferenza;
import project.MODEL.ArticoloSuRivista;
import project.MODEL.Conferenza;
import project.MODEL.Rivista;
import project.CONTROLLER.Controller;
import project.enumerazioni.GENERE_ENUM;
import project.enumerazioni.PERIOD_ENUM;

import javax.swing.JComboBox;

public class InserimentoArticoloSuRivistaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField titoloArticoloField;
	private JTextField doiField;
	private JTextField targetField;
	private JTextField editoreField;
	private JTextField titoloRivistaField;
	private JTextField issnField;

	public InserimentoArticoloSuRivistaGUI(Controller c) {

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
	    titoloArticoloField.setBounds(87, 64, 200, 27);
	    contentPane.add(titoloArticoloField);
	    titoloArticoloField.setColumns(10);

		JScrollBar titoloArticoloScrollBar = new JScrollBar();
		titoloArticoloScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloArticoloScrollBar.setBounds(87, 90, 200, 17);
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
	    doiField.setBounds(87, 112, 200, 27);
	    contentPane.add(doiField);

		JScrollBar doiScrollBar = new JScrollBar();
		doiScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		doiScrollBar.setBounds(87, 138, 200, 17);
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
	    targetField.setBounds(87, 166, 200, 27);
	    contentPane.add(targetField);

		JScrollBar targetScrollBar = new JScrollBar();
		targetScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		targetScrollBar.setBounds(87, 195, 200, 19);
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
		obbligatoriLabel.setBounds(574, 303, 99, 33);
		contentPane.add(obbligatoriLabel);

		ImageIcon img = new ImageIcon(this.getClass().getResource("/rivista.jfif"));

		JDateChooser dataPubblicazioneChooser = new JDateChooser();
		dataPubblicazioneChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dataPubblicazioneChooser.setBounds(175, 225, 112, 27);
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

		JLabel editoreLabel = new JLabel("Editore:");
		editoreLabel.setOpaque(true);
		editoreLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		editoreLabel.setBackground(Color.WHITE);
		editoreLabel.setBounds(553, 111, 62, 27);
		contentPane.add(editoreLabel);

		editoreField = new JTextField();
		editoreField.setToolTipText("Editore della rivista");
		editoreField.setFont(new Font("Arial", Font.PLAIN, 12));
		editoreField.setColumns(10);
		editoreField.setBounds(675, 111, 181, 27);
		contentPane.add(editoreField);

		JLabel rivistaDatiLabel = new JLabel("RIVISTA:");
		rivistaDatiLabel.setOpaque(true);
		rivistaDatiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rivistaDatiLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		rivistaDatiLabel.setBounds(551, 11, 305, 41);
		contentPane.add(rivistaDatiLabel);

		JLabel periodicitaLabel = new JLabel("Periodicit\u00E0:");
		periodicitaLabel.setOpaque(true);
		periodicitaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		periodicitaLabel.setBackground(Color.WHITE);
		periodicitaLabel.setBounds(554, 225, 93, 27);
		contentPane.add(periodicitaLabel);

		JLabel titoloConferenzaLabel = new JLabel("Titolo*:");
		titoloConferenzaLabel.setOpaque(true);
		titoloConferenzaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloConferenzaLabel.setBackground(Color.WHITE);
		titoloConferenzaLabel.setBounds(553, 63, 62, 27);
		contentPane.add(titoloConferenzaLabel);

		titoloRivistaField = new JTextField();
		titoloRivistaField.setToolTipText("Titolo della rivista");
		titoloRivistaField.setFont(new Font("Arial", Font.PLAIN, 12));
		titoloRivistaField.setColumns(10);
		titoloRivistaField.setBounds(675, 63, 181, 27);
		contentPane.add(titoloRivistaField);

		JScrollBar titoloConferenzaScrollBar = new JScrollBar();
		titoloConferenzaScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		titoloConferenzaScrollBar.setBounds(675, 90, 181, 17);
		BoundedRangeModel brmTitoloConferenza= titoloRivistaField.getHorizontalVisibility();
		titoloConferenzaScrollBar.setModel(brmTitoloConferenza);
		contentPane.add(titoloConferenzaScrollBar);

		issnField = new JTextField();
		issnField.setToolTipText("ISSN della rivista");
		issnField.setFont(new Font("Arial", Font.PLAIN, 12));
		issnField.setColumns(10);
		issnField.setBounds(675, 159, 181, 27);
		contentPane.add(issnField);

		JLabel issnLabel = new JLabel("ISSN*:");
		issnLabel.setOpaque(true);
		issnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		issnLabel.setBackground(Color.WHITE);
		issnLabel.setBounds(553, 158, 49, 27);
		contentPane.add(issnLabel);

		JScrollBar editoreScrollBar = new JScrollBar();
		editoreScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		editoreScrollBar.setBounds(675, 138, 181, 17);
		BoundedRangeModel brmCasaEditrice = editoreField.getHorizontalVisibility();
		editoreScrollBar.setModel(brmCasaEditrice);
		contentPane.add(editoreScrollBar);

		JScrollBar issnScrollBar = new JScrollBar();
		issnScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		issnScrollBar.setBounds(675, 185, 181, 17);
		BoundedRangeModel brmSede = issnField.getHorizontalVisibility();
		issnScrollBar.setModel(brmSede);
		contentPane.add(issnScrollBar);

		JComboBox periodicitaComboBox = new JComboBox();
		periodicitaComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		periodicitaComboBox.setBounds(675, 225, 181, 27);
		contentPane.add(periodicitaComboBox);

		periodicitaComboBox.addItem(new ComboBoxItem("(nessuna periodicita)","Value 0"));
		periodicitaComboBox.addItem(new ComboBoxItem("settimanale","Value 1"));
		periodicitaComboBox.addItem(new ComboBoxItem("quindicinale","Value 2"));
		periodicitaComboBox.addItem(new ComboBoxItem("mensile","Value 3"));
		periodicitaComboBox.addItem(new ComboBoxItem("annuale","Value 4"));
		periodicitaComboBox.addItem(new ComboBoxItem("pluriannuale","Value 5"));


		JButton confermaButton = new JButton("CONFERMA");
		confermaButton.setFont(new Font("Arial", Font.PLAIN, 14));
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PERIOD_ENUM periodicita = selectPeriodicita(periodicitaComboBox);

				Rivista rivista = new Rivista(titoloRivistaField.getText(), editoreField.getText(), issnField.getText(), periodicita);
				ArticoloSuRivista articoloSuRivista = new ArticoloSuRivista(titoloArticoloField.getText(), doiField.getText(), descrizioneArea.getText(), targetField.getText(), rivista);

				if(!(dataPubblicazioneChooser.getDate() == null)) {
					if(controller.inserisciArticoloSuRivista(articoloSuRivista)) {
						controller.inserisciPubblicazione(articoloSuRivista.getTitolo(), controller.getAutore().getOrcid(), dataPubblicazioneChooser.getDate());
						controller.chiediParolaChiave(articoloSuRivista);


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
				titoloRivistaField.setText("");
				issnField.setText("");
				editoreField.setText("");
				periodicitaComboBox.setSelectedIndex(0);
			}

			private PERIOD_ENUM selectPeriodicita(JComboBox periodicitaComboBox) {
				PERIOD_ENUM periodicita = null;
				switch(periodicitaComboBox.getSelectedItem().toString()) {
					case "settimanale":
						periodicita = PERIOD_ENUM.settimanale;
						break;
					case "quindicinale":
						periodicita = PERIOD_ENUM.quindicinale;
						break;
					case "mensile":
						periodicita = PERIOD_ENUM.mensile;
						break;
					case "annuale":
						periodicita = PERIOD_ENUM.quindicinale;
						break;
					case "pluriennale":
						periodicita = PERIOD_ENUM.pluriannuale;
						break;
					default : periodicita = null;
				}
				return periodicita;
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
		imageLabel.setBounds(0, 0, 866, 347);
		contentPane.add(imageLabel);


	}
}

