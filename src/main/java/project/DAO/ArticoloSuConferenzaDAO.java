package project.DAO;

import project.MODEL.ArticoloSuConferenza;
import project.MODEL.Conferenza;

public interface ArticoloSuConferenzaDAO {

	public boolean checkUniqueTitolo(String titolo);

	public boolean checkUniqueDOI(String doi);

	public String insertArticoloSuConferenza(ArticoloSuConferenza articoloSuConferenza);

	public ArticoloSuConferenza getArticoloSuConferenza(String titolo, Conferenza conferenza);

	public String updateArticolo(ArticoloSuConferenza articoloModificato, ArticoloSuConferenza articoloOriginale);

	public boolean identico(ArticoloSuConferenza articoloSuConferenza);
}
