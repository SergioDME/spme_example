package project.struttureDiAppoggio;

import java.util.Date;

import project.MODEL.ArticoloSuConferenza;
import project.MODEL.Autore;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;


public class RigaTabellaArticoloSuConferenza extends RigaTabellaMateriale{
	private ArticoloSuConferenza articoloSuConferenza;

	public RigaTabellaArticoloSuConferenza(Autore autore, Pubblicazione pubblicazione, ArticoloSuConferenza articoloSuConferenza) {
		super(autore, pubblicazione);
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.articoloSuConferenza = articoloSuConferenza;
	}

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public ArticoloSuConferenza getArticoloSuConferenza() {
		return articoloSuConferenza;
	}

	public int getNCitazioniArticolo() {
		return articoloSuConferenza.getNumeroCitazioni();
	}

	public String getTitoloArticolo() {
		return articoloSuConferenza.getTitolo();
	}

	public String getTitoloConferenza() {
		return articoloSuConferenza.getConferenza().getTitolo();
	}

	public Date getDataConferenza() {
		return articoloSuConferenza.getConferenza().getDataConferenza();
	}


}
