package project.MODEL;

import java.util.Date;

public class Conferenza {
	private Date dataConferenza;
	private String titolo;
	private String sede;
	private String casaEditrice;
	private Integer durata;

	public Conferenza(String titolo, Integer durata, String sede, String casaEditrice, Date date) {
		this.titolo = titolo;
		this.durata = durata;
		this.sede = sede;
		this.casaEditrice = casaEditrice;
		this.dataConferenza = date;
	}

	public Date getDataConferenza() {
		return dataConferenza;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getSede() {
		return sede;
	}

	public String getCasaEditrice() {
		return casaEditrice;
	}

	public Integer getDurata() {
		return durata;
	}
}
