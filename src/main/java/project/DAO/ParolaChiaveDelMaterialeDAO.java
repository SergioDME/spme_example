package project.DAO;

import project.MODEL.ParolaChiaveDelMateriale;

public interface ParolaChiaveDelMaterialeDAO {

	public void insertAssociazione(ParolaChiaveDelMateriale parolaDelMateriale);

	public boolean checkUniqueAssociazione(ParolaChiaveDelMateriale parolaDelMateriale);

	public String eliminaParolaDelMateriale(ParolaChiaveDelMateriale parolaDelMateriale);


}
