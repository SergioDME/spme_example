package project.MODEL;

import project.enumerazioni.GENERE_ENUM;

public class RisorsaOnline extends Materiale {
	private String url;

	public RisorsaOnline(String titolo, String doi, String descrizione, String url) {
		super(titolo, doi, descrizione);
		this.url = url;
	}

	public RisorsaOnline(String titolo, String doi, String descrizione, String url, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
		this.url = url;

	}

	public String getUrl() {
		return url;
	}


}
