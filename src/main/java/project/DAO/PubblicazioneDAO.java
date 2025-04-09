package project.DAO;

import java.util.Date;

public interface PubblicazioneDAO {

	public String insertPubblicazione(String titolo, String orcid, Date dataPubblicazione);

	public void deletePubblicazione(String titolo, String orcid);

	public void updatePubblicazione(String titolo, String orcid, Date date);
}
