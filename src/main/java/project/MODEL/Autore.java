package project.MODEL;

public class Autore {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String orcid;

	public Autore(String nome, String cognome, String orcid, String username, String password) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.orcid = orcid;
	}

	public Autore(String nome, String cognome, String orcid) {
		this.nome = nome;
		this.cognome = cognome;
		this.orcid = orcid;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getOrcid() {
		return orcid;
	}

	public String getUsername() {
		return username;
	}





}
