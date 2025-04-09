package project.DAO;

import project.MODEL.RisorsaOnline;

public interface RisorsaOnlineDAO {

	boolean checkUniqueTitolo(String titolo);

	boolean checkUniqueDOI(String doi);

	boolean checkUniqueURL(String url);

	String insertRisorsaOnline(RisorsaOnline risorsaOnline);

	RisorsaOnline getRisorsaOnline(String titolo);

	String updateRisorsaOnline(RisorsaOnline risorsaOnlineModificato, RisorsaOnline risorsaOnlineOriginale);

	boolean identico(RisorsaOnline risorsaOnline);

}
