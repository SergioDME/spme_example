package project.MODEL;


import project.enumerazioni.PERIOD_ENUM;

public class ArticoloSuRivista extends ArticoloScientifico {

	private Rivista rivista;

	public ArticoloSuRivista(String titolo, String doi, String descrizione, String target, Rivista rivista) {
		super(titolo, doi, descrizione);
		this.target = target;
		this.rivista = rivista;
	}

	public ArticoloSuRivista(String titolo, String doi, String descrizione, String target, Rivista rivista, int numeroCitazioni) {
		super(titolo, doi, descrizione, numeroCitazioni);
		this.target = target;
		this.rivista = rivista;
		if(rivista.getPeriodicita() == null)
			this.rivista.periodicita =  PERIOD_ENUM.non_definito;
		else
			this.rivista.periodicita =  PERIOD_ENUM.valueOf(rivista.periodicita.toString());
	}

	public Rivista getRivista() {
		return rivista;
	}

	public String getTarget() {
		return target;
	}



}
