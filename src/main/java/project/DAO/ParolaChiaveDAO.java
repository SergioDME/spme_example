package project.DAO;

import java.util.List;

import project.MODEL.ParolaChiave;

public interface ParolaChiaveDAO {

	public boolean checkUniqueParolaChiave(ParolaChiave parolaChiave);

	public void insertParolaChiave(ParolaChiave parola);

	public List<ParolaChiave> getParoleAssociate(String titoloMateriale);


}
