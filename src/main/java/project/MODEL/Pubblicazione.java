package project.MODEL;

import java.sql.Date;

public class Pubblicazione {
	private Date dataPubblicazione;
	private Date dataInserimento;
	private Autore autore;
	private Materiale materiale;

	public Pubblicazione(Autore autore, Materiale materiale, Date dataPubblicazione, Date dataInserimento) {
		super();
		this.dataPubblicazione = dataPubblicazione;
		this.dataInserimento = dataInserimento;
		this.autore = autore;
		this.materiale = materiale;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public Autore getAutore() {
		return autore;
	}

	public Materiale getMateriale() {
		return materiale;
	}
}
