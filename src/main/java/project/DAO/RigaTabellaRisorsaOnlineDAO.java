package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.RigaTabellaRisorsaOnline;

public interface RigaTabellaRisorsaOnlineDAO {

	List<RigaTabellaRisorsaOnline> getRisorsaOnline();

	RigaTabellaRisorsaOnline getRigaRisorsaOnline(String titolo);

}
