package project.VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.DAO.DAOPostgres.CategoriaDiAppartenenzaDAOPostgres;
import project.MODEL.Categoria;
import project.MODEL.CategoriaDiAppartenenza;
import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.MODEL.ParolaChiave;
import project.CONTROLLER.Controller;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.Toolkit;

public class AggiungiCategoriaAGerarchiaDaModificaGUI extends JFrame {

	private Controller controller;

	private JPanel contentPane;
	private JTextField categoriaField;



	public AggiungiCategoriaAGerarchiaDaModificaGUI(Controller c, Materiale materiale, Categoria supercategoria) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AggiuntaCategoriaAGerarchiaGUI.class.getResource("/GUI/libri.png")));
		setTitle("Library managment system");

		controller = c;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);



		JLabel titoloLabel = new JLabel("ASSOCIA CATEGORIA");
		titoloLabel.setOpaque(true);
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titoloLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		titoloLabel.setBounds(11, 11, 294, 37);
		contentPane.add(titoloLabel);

		categoriaField = new JTextField();
		categoriaField.setToolTipText("massimo 30 caratteri");
		categoriaField.setFont(new Font("Arial", Font.PLAIN, 12));
		categoriaField.setBounds(10, 91, 169, 28);
		contentPane.add(categoriaField);
		categoriaField.setColumns(10);

		JLabel lunghezzaLabel = new JLabel("(massimo 30 caratteri)");
		lunghezzaLabel.setOpaque(true);
		lunghezzaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lunghezzaLabel.setBounds(31, 137, 131, 14);
		contentPane.add(lunghezzaLabel);

		JButton btnNewButton = new JButton("Associa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(categoriaField.getText().isBlank())
					controller.openDialog("Campo vuoto non ammesso");
				else {
					if(categoriaField.getText().length()<31) {
						if(checkFormato(categoriaField.getText())) {
							Categoria categoria = new Categoria(categoriaField.getText());
							categoria.setSupercategoria(supercategoria);
							controller.associaCategoriaAGerarchia(materiale, categoria);
						}
						else
							controller.openDialog("Ammesse solo lettere");
					}
					else
						controller.openDialog("Lunghezza massima 30 caratteri");
					}

				categoriaField.setText("");
			}

			private boolean checkFormato(String stringa) {
				stringa = stringa.toLowerCase();
				char[] charArray = stringa.toCharArray();
				for(int i=0; i< charArray.length; i++) {
					char ch = charArray[i];
					if(!(ch>= 'a' && ch <= 'z')) {
						return false;
					}
				}
				return true;
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(205, 59, 100, 28);
		contentPane.add(btnNewButton);

		JButton esciButton = new JButton("Esci");
		esciButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				categoriaField.setText("");
				dispose();
				controller.openModificaCategorie(materiale);
			}
		});
		esciButton.setFont(new Font("Arial", Font.PLAIN, 12));
		esciButton.setBounds(205, 128, 100, 23);
		contentPane.add(esciButton);

		JScrollBar categoriaScrollBar = new JScrollBar();
		categoriaScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		categoriaScrollBar.setBounds(11, 117, 168, 14);
		BoundedRangeModel brmCategoria = categoriaField.getHorizontalVisibility();
		categoriaScrollBar.setModel(brmCategoria);
		contentPane.add(categoriaScrollBar);

		JButton btnNewButton_1 = new JButton("Indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.openAggiuntaAGerarchiaEsistenteDaModifica(materiale);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_1.setBounds(205, 94, 100, 23);
		contentPane.add(btnNewButton_1);

		JLabel supercategoriaLabel = new JLabel("Supercategoria: "+supercategoria.getNome());
		supercategoriaLabel.setOpaque(true);
		supercategoriaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		supercategoriaLabel.setBounds(0, 59, 195, 28);
		contentPane.add(supercategoriaLabel);

		JLabel imageLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/libr.jfif"));
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 316, 151);
		contentPane.add(imageLabel);
	}
}
