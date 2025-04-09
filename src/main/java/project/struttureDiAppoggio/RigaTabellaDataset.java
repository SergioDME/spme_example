package project.struttureDiAppoggio;

import project.MODEL.*;


public class RigaTabellaDataset extends RigaTabellaMateriale {
	private Dataset dataset;

	public Autore getAutore() {
		return autore;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public RigaTabellaDataset(Autore autore, Pubblicazione pubblicazione, Dataset dataset) {
		super(autore, pubblicazione);
		this.autore = autore;
		this.pubblicazione = pubblicazione;
		this.dataset = dataset;
	}

	public int getNcolonneDataset() {
		return dataset.getNcolonne();
	}

	public int getNrigheDataset() {
		return dataset.getNrighe();
	}

	public String getTitoloDataset() {
		return dataset.getTitolo();
	}

	public String getUrlDataset() {
		return dataset.getUrl();
	}

	public int getNCitazioniDataset() {
		return dataset.getNumeroCitazioni();
	}
}
