package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.RigaTabellaLibro;

public interface RigaTabellaLibroDAO {

	public RigaTabellaLibro getRigaLibro(String titolo);

	public List<RigaTabellaLibro> getLibri();

}
