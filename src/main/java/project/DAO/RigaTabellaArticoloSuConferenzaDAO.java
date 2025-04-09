package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;

public interface RigaTabellaArticoloSuConferenzaDAO {

	public List<RigaTabellaArticoloSuConferenza> getArticoliSuConferenza();

	public RigaTabellaArticoloSuConferenza getRigaArticoloSuConferenza(String titolo);


}
