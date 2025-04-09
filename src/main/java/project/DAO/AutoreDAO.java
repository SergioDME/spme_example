package project.DAO;

import java.util.List;

import project.MODEL.Autore;

public interface AutoreDAO {

	public String insertAutore(String nome, String cognome, String oRCID, String username, String password);

	public boolean checkNotUniqueORCID(String ORCID);

	public boolean checkPresenzaUsername(String username);

	public boolean checkUniqueUsername(String username);

	public Autore getAutore(String username, String passwordString);

	public List<Autore> getAutori();

	public boolean deleteAutore(Autore autore);

	public boolean updateAutore(String nome, String cognome, String orcid, String username, Autore autore);

	public boolean updateAutore(String nome, String cognome, String orcid, String username, String passwordString, Autore autore);

}
