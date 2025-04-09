package project.struttureDiAppoggio;

import java.util.Date;

public class Ricerca {

	private String nomeAutore;
	private String cognomeAutore;
	private String orcidAutore;
	private Date dataInserimento;
	private Date dataPubblicazione;
	private String parola;
	private String categoria1;
	private String categoria2;
	private String categoria3;

	public Ricerca(String nomeAutore, String cognomeAutore, String orcidAutore, Date dataInserimento,
			Date dataPubblicazione, String parola, String categoria1, String categoria2, String categoria3) {
		super();
		this.nomeAutore = nomeAutore;
		this.cognomeAutore = cognomeAutore;
		this.orcidAutore = orcidAutore;
		this.dataInserimento = dataInserimento;
		this.dataPubblicazione = dataPubblicazione;
		this.parola = parola;
		this.categoria1 = categoria1;
		this.categoria2 = categoria2;
		this.categoria3 = categoria3;
	}

	public String getNomeAutore() {
		return nomeAutore;
	}
	public String getCognomeAutore() {
		return cognomeAutore;
	}
	public String getOrcidAutore() {
		return orcidAutore;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	public String getParola() {
		return parola;
	}
	public String getCategoria1() {
		return categoria1;
	}
	public String getCategoria2() {
		return categoria2;
	}
	public String getCategoria3() {
		return categoria3;
	}


}
