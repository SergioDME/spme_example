package project.struttureDiAppoggio;

import project.MODEL.*;
import project.enumerazioni.GENERE_ENUM;

public class RigaTabellaLibro extends RigaTabellaMateriale {
	private Libro libro;

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public Libro getLibro() {
		return libro;
	}

	public RigaTabellaLibro(Autore autore, Pubblicazione pubblicazione, Libro libro) {
		super(autore, pubblicazione);
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.libro = libro;
	}

	public int getNpagineLibro() {
		return libro.getNpagine();
	}

	public int getNCitazioniLibro() {
		return libro.getNumeroCitazioni();
	}

	public String getTitoloLibro() {
		return libro.getTitolo();
	}

	public GENERE_ENUM getGenereLibro() {
		return libro.getGenere();
	}
}
