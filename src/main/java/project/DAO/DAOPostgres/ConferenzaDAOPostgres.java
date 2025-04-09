package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import project.DAO.ConferenzaDAO;
import project.MODEL.Conferenza;
import project.MODEL.Libro;
import project.DAO.ConnectionPostgres;

public class ConferenzaDAOPostgres implements ConferenzaDAO{

	private Connection conn;

	public ConferenzaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public boolean checkUniqueConferenza(Conferenza conferenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"conferenza\" WHERE \"titolo\" = ? AND \"dataconferenza\" = ?");
			ps.setString(1,conferenza.getTitolo());
			java.sql.Date dataSQL = new java.sql.Date(conferenza.getDataConferenza().getTime());
			ps.setDate(2, dataSQL);

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

	public void insertConferenza(Conferenza conferenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"conferenza\" (titolo, dataconferenza, casaeditrice, sede, durata) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1,conferenza.getTitolo());
			java.sql.Date dataSQL = new java.sql.Date(conferenza.getDataConferenza().getTime());
			ps.setDate(2, dataSQL);
			ps.setString(3, conferenza.getCasaEditrice());
			ps.setString(4, conferenza.getSede());
			ps.setInt(5, conferenza.getDurata());



			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();;
		}
	}

	public Conferenza getConferenzaByArticolo(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Conferenza conferenza = new Conferenza(rs.getString("titolo"), rs.getInt("durata"), rs.getString("sede"),rs.getString("casaeditrice"), rs.getDate("dataconferenza"));

			rs.close();
			ps.close();
			return conferenza;
		} catch (SQLException e) {
			return null;
		}
	}

	public String updateConferenza(Conferenza conferenzaModificata, Conferenza conferenzaOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"conferenza\" SET (casaeditrice, sede, durata) = (?, ?, ?) WHERE titolo = ? AND dataconferenza = ?");
			ps.setString(1,conferenzaModificata.getCasaEditrice());
			ps.setString(2, conferenzaModificata.getSede());
			ps.setInt(3, conferenzaModificata.getDurata());
			ps.setString(4, conferenzaOriginale.getTitolo());
			java.sql.Date dataSQL = new java.sql.Date(conferenzaOriginale.getDataConferenza().getTime());
			ps.setDate(5, dataSQL);

			ps.execute();
			ps.close();

			return ("Conferenza "+conferenzaModificata.getTitolo()+" modificata correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

}
