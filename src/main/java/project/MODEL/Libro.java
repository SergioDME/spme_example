package project.MODEL;

import project.enumerazioni.*;

public class Libro extends Materiale{
	private String isbn;
	private GENERE_ENUM genere;
	private Integer npagine;
	private String editore;

	public Libro(String titolo, String doi, String isbn, GENERE_ENUM genere, int npagine, String editore, String descrizione) {
		super(titolo, doi, descrizione);
		this.isbn = isbn;
		this.genere = genere;
		this.npagine = npagine;
		this.editore = editore;
	}

	public Libro(String titolo, String doi, String isbn, String genere, int npagine, String editore, String descrizione) {
		super(titolo, doi, descrizione);
		this.isbn = isbn;
		if(genere == null)
			this.genere = null;
		else
			this.genere = GENERE_ENUM.valueOf(genere);
		this.npagine = npagine;
		this.editore = editore;
	}

	public Libro(String titolo, String doi, String isbn, String genere, int npagine, String editore, String descrizione, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
		this.isbn = isbn;
		if(genere == null)
			this.genere = GENERE_ENUM.non_definito;
		else
			this.genere = GENERE_ENUM.valueOf(genere);
		this.npagine = npagine;
		this.editore = editore;
	}

	public String getIsbn() {
		return isbn;
	}

	public GENERE_ENUM getGenere() {
		return genere;
	}

	public Integer getNpagine() {
		return npagine;
	}

	public String getEditore() {
		return editore;
	}
}
