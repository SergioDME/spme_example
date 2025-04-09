package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.DatasetDAO;
import project.MODEL.Autore;
import project.MODEL.Dataset;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaDataset;
import project.struttureDiAppoggio.RigaTabellaLibro;

public class DatasetDAOPostgres implements DatasetDAO{

	private Connection conn;

	public DatasetDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public boolean checkUniqueTitolo(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)!=0) {
				ps.close();
				rs.close();
				conn.close();
				return false;
			}
			rs.close();
			ps.close();
			return true;
		} catch (SQLException e) {
			return true;
		}
	}

	public boolean checkUniqueDOI(String doi) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"materiale\" WHERE \"doi\" = ?");
			ps.setString(1,doi.toUpperCase());

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)!=0) {
				ps.close();
				rs.close();
				conn.close();
				return false;
			}
			rs.close();
			ps.close();
			return true;
		} catch (SQLException e) {
			return true;
		}
	}

	public boolean checkUniqueURL(String url) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"materiale\" WHERE \"url\" = ?");
			ps.setString(1,url);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)!=0) {
				ps.close();
				rs.close();
				conn.close();
				return false;
			}
			rs.close();
			ps.close();
			return true;
		} catch (SQLException e) {
			return true;
		}
	}

	public String insertDataset(Dataset dataset) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"materiale\" (titolo, doi, descrizione, url, numerocolonne, numerorighe, tipo) VALUES (?, ?, ?, ?, ?, ?, CAST(? AS materiale_enum))");
			ps.setString(1,dataset.getTitolo());
			if(dataset.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, dataset.getDoi().toUpperCase());
			if(dataset.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,dataset.getDescrizione());
			ps.setString(4,dataset.getUrl());
			ps.setInt(5,dataset.getNcolonne());
			ps.setInt(6, dataset.getNrighe());
			ps.setString(7, "Dataset");

			ps.execute();
			ps.close();
			conn.close();

			return ("Dataset "+dataset.getTitolo()+" inserito correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}

	}

	public Dataset getDataset(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Dataset dataset = new Dataset(rs.getString("titolo"), rs.getString("doi"), rs.getString("url"), rs.getInt("numerocolonne"),rs.getInt("numerorighe"), rs.getString("descrizione"));

			rs.close();
			ps.close();
			return dataset;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String updateDataset(Dataset datasetModificato, Dataset datasetOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"materiale\" SET (titolo, doi, descrizione, url, numerocolonne, numerorighe) = (?, ?, ?, ?, ?, ?) WHERE titolo = ?");
			ps.setString(1,datasetModificato.getTitolo());
			if(datasetModificato.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, datasetModificato.getDoi().toUpperCase());
			if(datasetModificato.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,datasetModificato.getDescrizione());
			ps.setString(4,datasetModificato.getUrl());
			ps.setInt(5,datasetModificato.getNcolonne());
			ps.setInt(6, datasetModificato.getNrighe());
			ps.setString(7, datasetOriginale.getTitolo());

			ps.execute();
			ps.close();
			conn.close();

			return ("Dataset "+datasetModificato.getTitolo()+" modificato correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public boolean identico(Dataset dataset) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)"
					+ "FROM \"materiale\""
					+ "WHERE (\"titolo\" = ? AND \"numerorighe\" = ? AND  \"numerocolonne\" = ? AND \"url\" = ?)");
			ps.setString(1,dataset.getTitolo());
			ps.setInt(2,dataset.getNrighe());
			ps.setInt(3,dataset.getNcolonne());
			ps.setString(4,dataset.getUrl());

			ResultSet rs = ps.executeQuery();

			rs.next();

			if(rs.getInt(1) == 1)
				return true;
			else
				return false;

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

