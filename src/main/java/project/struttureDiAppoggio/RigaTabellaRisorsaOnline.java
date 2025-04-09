package project.struttureDiAppoggio;


import project.MODEL.Autore;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.MODEL.RisorsaOnline;

public class RigaTabellaRisorsaOnline extends RigaTabellaMateriale {
	private RisorsaOnline risorsaOnline;

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public RisorsaOnline getRisorsaOnline() {
		return risorsaOnline;
	}

	public RigaTabellaRisorsaOnline(Autore autore, Pubblicazione pubblicazione, RisorsaOnline risorsaOnline) {
		super(autore, pubblicazione);
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.risorsaOnline = risorsaOnline;
	}

	public String getUrlRisorsaOnline() {
		return risorsaOnline.getUrl();
	}

	public int getNCitazioniRisorsaOnline() {
		return risorsaOnline.getNumeroCitazioni();
	}

	public String getTitoloRisorsaOnline() {
		return risorsaOnline.getTitolo();
	}

}
