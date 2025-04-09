package project.MODEL;

public abstract class ArticoloScientifico extends Materiale {

	protected String target;

	public ArticoloScientifico(String titolo, String doi, String descrizione) {
		super(titolo, doi, descrizione);
		// TODO Auto-generated constructor stub
	}

	public ArticoloScientifico(String titolo, String doi, String descrizione, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
	}
}
