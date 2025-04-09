package project.DAO;

import project.MODEL.Dataset;

public interface DatasetDAO {

	public boolean checkUniqueTitolo(String titolo);

	public boolean checkUniqueDOI(String doi);

	public boolean checkUniqueURL(String url);

	public String insertDataset(Dataset dataset);

	public Dataset getDataset(String titolo);

	public String updateDataset(Dataset datasetModificato, Dataset datasetOriginale);

	public boolean identico(Dataset dataset);
}
