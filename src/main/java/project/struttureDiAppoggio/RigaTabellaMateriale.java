package project.struttureDiAppoggio;

import project.MODEL.Autore;
import project.MODEL.Materiale;
import project.MODEL.Pubblicazione;

public class RigaTabellaMateriale {
	protected Autore autore;
	protected Pubblicazione pubblicazione;
	protected Materiale materiale;


	public RigaTabellaMateriale(Autore autore, Pubblicazione pubblicazione, Materiale materiale) {
		super();
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.materiale = materiale;
	}

	public RigaTabellaMateriale(Autore autore, Pubblicazione pubblicazione) {
		super();
		this.autore = autore;
		this.pubblicazione = pubblicazione;
	}

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public Materiale getMateriale() {
		return materiale;
	}

	public String getTitoloMateriale() {
		return materiale.getTitolo();
	}

	public int getNumeroCitazioniMateriale() {
		return materiale.getNumeroCitazioni();
	}
}
