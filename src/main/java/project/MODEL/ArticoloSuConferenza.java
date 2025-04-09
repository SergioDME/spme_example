package project.MODEL;

public class ArticoloSuConferenza extends ArticoloScientifico{

	private Conferenza conferenza;

	public ArticoloSuConferenza(String titolo, String doi, String descrizione, String target, Conferenza conferenza) {
		super(titolo, doi, descrizione);
		this.target = target;
		this.conferenza = conferenza;
	}

	public ArticoloSuConferenza(String titolo, String doi, String descrizione, String target, Conferenza conferenza, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
		this.target = target;
		this.conferenza = conferenza;
	}

	public String getTarget() {
		return target;
	}

	public Conferenza getConferenza() {
		return conferenza;
	}


}
