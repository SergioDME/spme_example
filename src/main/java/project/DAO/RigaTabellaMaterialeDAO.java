package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.Ricerca;
import project.struttureDiAppoggio.RigaTabellaMateriale;

public interface RigaTabellaMaterialeDAO {

	List<RigaTabellaMateriale> getRigheMateriali();

	List<RigaTabellaMateriale> getRigheRicerca(Ricerca ricerca);

}
