package project.MODEL;

public class Materiale {

	private String titolo;
	private String doi;
	private String descrizione;
	private String tipo;
	private Integer numeroCitazioniRicevute;

	public Materiale(String titolo, String doi, String descrizione, String tipo, int ncit) {
		this.titolo = titolo;
		this.doi = doi;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.numeroCitazioniRicevute = ncit;
	}

	public Materiale(String titolo, String doi, String descrizione, String tipo) {
		this.titolo = titolo;
		this.doi = doi;
		this.descrizione = descrizione;
		this.tipo = tipo;
	}

	public Materiale(String titolo, String doi, String descrizione, int ncit) {
		this.titolo = titolo;
		this.doi = doi;
		this.descrizione = descrizione;
		this.numeroCitazioniRicevute = ncit;
	}

	public Materiale(String titolo, String doi, String descrizione) {
		this.titolo = titolo;
		this.doi = doi;
		this.descrizione = descrizione;
	}

	public Materiale(String titolo){
		this.titolo = titolo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getDoi() {
		return doi;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Integer getNumeroCitazioni() {
		return numeroCitazioniRicevute;
	}
}
