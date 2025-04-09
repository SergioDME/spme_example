package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.RigaTabellaArticoloSuRivista;

public interface RigaTabellaArticoloSuRivistaDAO {

	public List<RigaTabellaArticoloSuRivista> getArticoliSuRivista();

	public RigaTabellaArticoloSuRivista getRigaArticoloSuRivista(String titolo);
}

