package project.MODEL;

import project.enumerazioni.GENERE_ENUM;

public class Dataset extends Materiale{

	private int numeroRighe;
	private int numeroColonne;
	private String url;

	public Dataset(String titolo, String doi, String url, Integer ncolonne, Integer nrighe, String descrizione) {
		super(titolo, doi, descrizione);
		this.url = url;
		this.numeroColonne = ncolonne;
		this.numeroRighe = nrighe;
	}

	public Dataset(String titolo, String doi, String url, int ncolonne, int nrighe, String descrizione, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
		this.url = url;
		this.numeroRighe = nrighe;
		this.numeroColonne = ncolonne;
	}


	public String getUrl() {
		return url;
	}

	public Integer getNcolonne() {
		return numeroColonne;
	}

	public Integer getNrighe() {
		return numeroRighe;
	}



}
