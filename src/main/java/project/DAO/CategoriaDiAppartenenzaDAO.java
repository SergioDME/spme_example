package project.DAO;

import project.MODEL.CategoriaDiAppartenenza;

public interface CategoriaDiAppartenenzaDAO {


	public boolean checkUniqueAssociazione(CategoriaDiAppartenenza categoriaDiAppartenenza);

	public void insertAssociazione(CategoriaDiAppartenenza categoriaDiAppartenenza);

	public void closeConnection();

	public String eliminaCategoriaDiAppartenenza(CategoriaDiAppartenenza categoriaDiAppartenenza);

	public boolean checkNotLoop(CategoriaDiAppartenenza categoriaDiAppartenenza);
}
