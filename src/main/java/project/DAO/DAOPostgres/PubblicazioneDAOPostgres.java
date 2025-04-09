package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import project.DAO.PubblicazioneDAO;
import project.DAO.ConnectionPostgres;

public class PubblicazioneDAOPostgres implements PubblicazioneDAO{

	private Connection conn;

	public PubblicazioneDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public String insertPubblicazione(String titolo, String orcid, Date dataPubblicazione) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"pubblicazione\" (titolo, orcid, dataPubblicazione) VALUES (?, ?, ?);");
			ps.setString(1,titolo);
			ps.setString(2,orcid);
			if(dataPubblicazione == null)
				ps.setNull(3, Types.DATE);
			else {
			java.sql.Date dataSQL = new java.sql.Date(dataPubblicazione.getTime());
			ps.setDate(3, dataSQL);
			}

			ps.execute();
			ps.close();
			conn.close();

			return ("Pubblicazione riuscita");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public void deletePubblicazione(String titolo, String orcid) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM \"pubblicazione\" WHERE titolo = ? AND orcid = ?");
			ps.setString(1,titolo);
			ps.setString(2,orcid);

			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {

		}
	}

	public void updatePubblicazione(String titolo, String orcid, Date date) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"pubblicazione\" SET \"datapubblicazione\" = ? WHERE (\"titolo\" = ? AND \"orcid\" = ?);");
			java.sql.Date dataSQL = new java.sql.Date(date.getTime());
			ps.setDate(1, dataSQL);
			ps.setString(2, titolo);
			ps.setString(3, orcid);


			ps.execute();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
