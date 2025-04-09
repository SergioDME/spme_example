package project.struttureDiAppoggio;

import java.util.Date;

import project.MODEL.ArticoloSuConferenza;
import project.MODEL.ArticoloSuRivista;
import project.MODEL.Autore;
import project.MODEL.Pubblicazione;
import project.enumerazioni.PERIOD_ENUM;

public class RigaTabellaArticoloSuRivista extends RigaTabellaMateriale{
	private ArticoloSuRivista articoloSuRivista;

	public RigaTabellaArticoloSuRivista(Autore autore, Pubblicazione pubblicazione, ArticoloSuRivista articoloSuRivista) {
		super(autore, pubblicazione);
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.articoloSuRivista= articoloSuRivista;
	}

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public ArticoloSuRivista getArticoloSuRivista() {
		return articoloSuRivista;
	}


}
