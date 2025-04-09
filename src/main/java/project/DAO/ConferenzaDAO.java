package project.DAO;

import project.MODEL.Conferenza;

public interface ConferenzaDAO {

	public boolean checkUniqueConferenza(Conferenza conferenza);

	public void insertConferenza(Conferenza conferenza);

	public Conferenza getConferenzaByArticolo(String titolo);

	public String updateConferenza(Conferenza conferenzaModificata, Conferenza conferenzaOriginale);
}
