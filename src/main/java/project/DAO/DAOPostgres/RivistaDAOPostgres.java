package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import project.DAO.RivistaDAO;
import project.MODEL.Libro;
import project.MODEL.Rivista;
import project.DAO.ConnectionPostgres;

public class RivistaDAOPostgres implements RivistaDAO {

	private Connection conn;

	public RivistaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	@Override
	public boolean checkUniqueISSNRivista(Rivista rivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"rivista\" WHERE \"issn\" = ?");
			ps.setString(1,rivista.getIssn());

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

	@Override
	public boolean checkUniqueTitoloRivista(Rivista rivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"titolo\" WHERE \"titolo\" = ?");
			ps.setString(1,rivista.getTitolo());

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

	@Override
	public void insertRivista(Rivista rivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"rivista\" (titolo, editore, issn, periodicita) VALUES (?, ?, ?, CAST(? as PERIOD_ENUM))");
			if(rivista.getTitolo().isBlank())
				ps.setNull(1, Types.VARCHAR);
			else
				ps.setString(1,rivista.getTitolo());
			if(rivista.getEditore().isBlank())
				ps.setNull(2, Types.VARCHAR);
			else
				ps.setString(2,rivista.getEditore());
			ps.setString(3,rivista.getIssn());
			if(rivista.getPeriodicita() == null)
				ps.setNull(4, Types.VARCHAR);
			else
				ps.setString(4,rivista.getPeriodicita().toString());



			ps.execute();
			ps.close();
//			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();;
		}
	}

	@Override
	public Rivista getRivistaByArticolo(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Rivista rivista = new Rivista(rs.getString("titolo"), rs.getString("editore"), rs.getString("issn"), rs.getString("periodicita"));

			if(rivista.getPeriodicita() == null)
				ps.setNull(4, Types.VARCHAR);
			else
				ps.setString(4,rivista.getPeriodicita().toString());

			rs.close();
			ps.close();
			return rivista;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String updateRivista(Rivista rivistaModificata, Rivista rivistaOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"rivista\" SET (titolo, issn, editore, periodicita) = (?, ?, ?, CAST(? AS PERIOD_ENUM)) WHERE issn = ?");
			ps.setString(1,rivistaModificata.getTitolo());
			ps.setString(2, rivistaModificata.getIssn());
			ps.setString(3, rivistaModificata.getEditore());
			ps.setString(4, rivistaModificata.getPeriodicita().toString());
			ps.setString(5, rivistaOriginale.getIssn());

			ps.execute();
			ps.close();

			return ("Rivista "+rivistaModificata.getTitolo()+" modificata correttamente");
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}

