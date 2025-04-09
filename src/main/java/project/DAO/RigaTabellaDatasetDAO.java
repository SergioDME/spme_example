package project.DAO;

import java.util.List;

import project.struttureDiAppoggio.RigaTabellaDataset;

public interface RigaTabellaDatasetDAO {

	public List<RigaTabellaDataset> getDataset();

	public RigaTabellaDataset getRigaDataset(String titolo);


}
