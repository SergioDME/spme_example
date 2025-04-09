package project.MODEL;


import project.enumerazioni.PERIOD_ENUM;

public class Rivista {

	private String titolo;
	private String issn;
	private String editore;
	PERIOD_ENUM periodicita;

	public Rivista(String titolo, String editore, String issn, PERIOD_ENUM periodicita) {
		this.titolo = titolo;
		this.editore = editore;
		this.issn = issn;
		this.periodicita = periodicita;
	}

	public Rivista(String titolo, String editore, String issn, String periodicita) {
		this.titolo = titolo;
		this.editore = editore;
		this.issn = issn;
		if(periodicita == null)
			this.periodicita = null;
		else
			this.periodicita = PERIOD_ENUM.valueOf(periodicita);
	}

	public String getIssn() {
		return issn;
	}

	public String getTitolo() {
		return titolo;
	}

	public PERIOD_ENUM getPeriodicita() {
			return periodicita;
	}

	public String getEditore() {
		return editore;
	}
}
