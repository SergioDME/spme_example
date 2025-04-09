package project.VIEW;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.CONTROLLER.Controller;
import project.struttureDiAppoggio.Ricerca;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;

import java.awt.Toolkit;

public class RicercaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField orcidField;
	private JTextField parolaChiaveField;
	private JTextField categoria1Field;
	private JTextField categoria2Field;
	private JTextField categoria3Field;

	public RicercaGUI(Controller c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RicercaGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");
		setResizable(false);

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JButton esciButton = new JButton("ESCI");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.openLibreria();
			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setOpaque(true);
		separator_1.setBounds(0, 254, 587, 4);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.LIGHT_GRAY);
		separator_2.setOpaque(true);
		separator_2.setBounds(0, 178, 587, 4);
		contentPane.add(separator_2);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setOpaque(true);
		separator.setBounds(0, 293, 587, 4);
		contentPane.add(separator);
		esciButton.setBounds(470, 370, 107, 38);
		esciButton.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(esciButton);

		JLabel criteriLabel = new JLabel("CRITERI DI RICERCA:");
		criteriLabel.setOpaque(true);
		criteriLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		criteriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		criteriLabel.setBounds(10, 11, 567, 50);
		contentPane.add(criteriLabel);


		JLabel lblNome = new JLabel("Nome autore:");
		lblNome.setOpaque(true);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNome.setBackground(Color.WHITE);
		lblNome.setBounds(10, 72, 133, 27);
		contentPane.add(lblNome);

		nomeField = new JTextField();
		nomeField.setFont(new Font("Arial", Font.PLAIN, 12));
		nomeField.setColumns(10);
		nomeField.setBounds(198, 72, 200, 27);
		contentPane.add(nomeField);

		cognomeField = new JTextField();
		cognomeField.setFont(new Font("Arial", Font.PLAIN, 12));
		cognomeField.setColumns(10);
		cognomeField.setBounds(198, 110, 200, 27);
		contentPane.add(cognomeField);

		orcidField = new JTextField();
		orcidField.setFont(new Font("Arial", Font.PLAIN, 12));
		orcidField.setColumns(10);
		orcidField.setBounds(198, 148, 200, 27);
		contentPane.add(orcidField);

		JLabel lblCognome = new JLabel("Cognome autore:");
		lblCognome.setOpaque(true);
		lblCognome.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCognome.setBackground(Color.WHITE);
		lblCognome.setBounds(10, 110, 133, 27);
		contentPane.add(lblCognome);

		JLabel lblOrcid = new JLabel("ORCID autore:");
		lblOrcid.setOpaque(true);
		lblOrcid.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrcid.setBackground(Color.WHITE);
		lblOrcid.setBounds(10, 148, 133, 27);
		contentPane.add(lblOrcid);

		JLabel lblDataInserimento = new JLabel("Data inserimento:");
		lblDataInserimento.setOpaque(true);
		lblDataInserimento.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataInserimento.setBackground(Color.WHITE);
		lblDataInserimento.setBounds(10, 186, 178, 27);
		contentPane.add(lblDataInserimento);

		JLabel lblDataPubblicazione = new JLabel("Data pubblicazione:");
		lblDataPubblicazione.setOpaque(true);
		lblDataPubblicazione.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDataPubblicazione.setBackground(Color.WHITE);
		lblDataPubblicazione.setBounds(10, 224, 178, 27);
		contentPane.add(lblDataPubblicazione);



		JDateChooser dataInserimentoChooser = new JDateChooser();
		dataInserimentoChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dataInserimentoChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dataInserimentoChooser.setToolTipText("deve essere antecedente alla data odierna");
		dataInserimentoChooser.setBounds(198, 186, 200, 27);
		Date date = new Date();
		dataInserimentoChooser.setSelectableDateRange(null, date);
		contentPane.add(dataInserimentoChooser);

		JDateChooser dataPubblicazioneChooser = new JDateChooser();
		dataPubblicazioneChooser.getCalendarButton().setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 15));
		dataPubblicazioneChooser.setToolTipText("deve essere antecedente alla data odierna");
		dataPubblicazioneChooser.setBounds(198, 224, 200, 27);
		dataPubblicazioneChooser.setSelectableDateRange(null, date);
		contentPane.add(dataPubblicazioneChooser);

		JLabel lblParolaChiave = new JLabel("Parola chiave:");
		lblParolaChiave.setOpaque(true);
		lblParolaChiave.setFont(new Font("Arial", Font.PLAIN, 12));
		lblParolaChiave.setBackground(Color.WHITE);
		lblParolaChiave.setBounds(10, 262, 133, 27);
		contentPane.add(lblParolaChiave);

		parolaChiaveField = new JTextField();
		parolaChiaveField.setFont(new Font("Arial", Font.PLAIN, 12));
		parolaChiaveField.setColumns(10);
		parolaChiaveField.setBounds(198, 262, 200, 27);
		contentPane.add(parolaChiaveField);

		categoria1Field = new JTextField();
		categoria1Field.setBackground(Color.WHITE);
		categoria1Field.setFont(new Font("Arial", Font.PLAIN, 12));
		categoria1Field.setColumns(10);
		categoria1Field.setBounds(198, 300, 200, 27);
		contentPane.add(categoria1Field);



		categoria2Field = new JTextField();
		categoria2Field.setBackground(Color.LIGHT_GRAY);
		categoria2Field.setEnabled(false);
		categoria2Field.setFont(new Font("Arial", Font.PLAIN, 12));
		categoria2Field.setColumns(10);
		categoria2Field.setBounds(198, 338, 200, 27);
		contentPane.add(categoria2Field);
		KeyAdapter adapter = new KeyAdapter()
				{
				  public void keyReleased(java.awt.event.KeyEvent evt)
				          {
				            super.keyReleased(evt);
				            if (!(categoria1Field.getText().isBlank()))
				            {
				            	categoria2Field.setEnabled(true);
				            	categoria2Field.setBackground(Color.white);

				            }
				            else
				            {
				            	categoria2Field.setEnabled(false);
				            	categoria2Field.setBackground(Color.lightGray);

				            	pulisciCampi1();

				            }
				        }

				private void pulisciCampi1() {
					categoria2Field.setText("");

				}
				};
				categoria1Field.addKeyListener(adapter);




		categoria3Field = new JTextField();
		categoria3Field.setBackground(Color.LIGHT_GRAY);
		categoria3Field.setEnabled(false);
		categoria3Field.setFont(new Font("Arial", Font.PLAIN, 12));
		categoria3Field.setColumns(10);
		categoria3Field.setBounds(198, 376, 200, 27);
		contentPane.add(categoria3Field);


		KeyAdapter adapter1 = new KeyAdapter()
		{
		  public void keyReleased(java.awt.event.KeyEvent evt)
		          {
		            super.keyReleased(evt);
		            if (!( categoria2Field.getText().isBlank()))
		            {
		            	categoria3Field.setEnabled(true);
		            	categoria3Field.setBackground(Color.white);

		            }
		            else
		            {
		            	categoria3Field.setEnabled(false);
		            	categoria3Field.setBackground(Color.lightGray);
		            	pulisciCampi2();

		            }
		        }

		private void pulisciCampi2() {
			categoria3Field.setText("");
		}
		};
		categoria2Field.addKeyListener(adapter1);

		KeyAdapter adapter2 = new KeyAdapter()
		{
		  public void keyReleased(java.awt.event.KeyEvent evt)
		          {
		            super.keyReleased(evt);
		            if (( categoria1Field.getText().isBlank()))
		            {
		            	categoria3Field.setEnabled(false);
		            	categoria3Field.setBackground(Color.lightGray);
		            	pulisciCampi3();
		            }
		        }

		private void pulisciCampi3() {
			categoria3Field.setText("");
		}
		};
		categoria1Field.addKeyListener(adapter2);


//		if (categoria1Field.getText().isEmpty()){
//	    	 categoria2Field.setEnabled(false);
//	     }
//	     else {
//	    	 categoria2Field.setEnabled(true);
//	    }


		JLabel lblCategoria = new JLabel("Categoria 1:");
		lblCategoria.setOpaque(true);
		lblCategoria.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCategoria.setBackground(Color.WHITE);
		lblCategoria.setBounds(10, 300, 133, 27);
		contentPane.add(lblCategoria);

		JLabel lblCategoria_1 = new JLabel("Categoria 2:");
		lblCategoria_1.setOpaque(true);
		lblCategoria_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCategoria_1.setBackground(Color.WHITE);
		lblCategoria_1.setBounds(10, 338, 133, 27);
		contentPane.add(lblCategoria_1);

		JLabel lblCategoria_2 = new JLabel("Categoria 3");
		lblCategoria_2.setOpaque(true);
		lblCategoria_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCategoria_2.setBackground(Color.WHITE);
		lblCategoria_2.setBounds(10, 376, 133, 27);
		contentPane.add(lblCategoria_2);


		JButton btnRicerca = new JButton("RICERCA");
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ricerca ricerca = new Ricerca(nomeField.getText(), cognomeField.getText(), orcidField.getText(), dataInserimentoChooser.getDate(),
						dataPubblicazioneChooser.getDate(), parolaChiaveField.getText(),
						categoria1Field.getText(), categoria2Field.getText(), categoria3Field.getText());

				controller.eseguiRicerca(ricerca);
			}
		});
		btnRicerca.setFont(new Font("Arial", Font.PLAIN, 14));
		btnRicerca.setBounds(470, 321, 107, 38);
		contentPane.add(btnRicerca);

		JLabel imageLabel = new JLabel("");
		imageLabel.setBackground(Color.GRAY);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 587, 418);
		contentPane.add(imageLabel);
	}
}
