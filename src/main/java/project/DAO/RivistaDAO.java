package project.DAO;

import project.MODEL.Rivista;

public interface RivistaDAO {

	boolean checkUniqueISSNRivista(Rivista rivista);

	boolean checkUniqueTitoloRivista(Rivista rivista);

	void insertRivista(Rivista rivista);

	Rivista getRivistaByArticolo(String titolo);

	String updateRivista(Rivista rivistaModificata, Rivista rivistaOriginale);

}
