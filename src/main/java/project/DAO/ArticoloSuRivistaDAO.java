package project.DAO;

import project.MODEL.ArticoloSuRivista;
import project.MODEL.Rivista;

public interface ArticoloSuRivistaDAO {

	public boolean checkUniqueTitolo(String titolo);

	public boolean checkUniqueDOI(String doi);

	public String insertArticoloSuConferenza(ArticoloSuRivista articoloSuRivista);

	public ArticoloSuRivista getArticoloSuRivista(String titolo, Rivista rivista);

	public boolean checkUniqueISSNRivista(String issn);

	public boolean checkUniqueTitoloRivista(String titolo);

	public String updateArticolo(ArticoloSuRivista articoloModificato, ArticoloSuRivista articoloOriginale);

	public boolean identico(ArticoloSuRivista articoloSuRivista);


}
