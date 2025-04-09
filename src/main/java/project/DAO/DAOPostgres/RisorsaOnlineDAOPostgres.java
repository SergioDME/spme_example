package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RisorsaOnlineDAO;
import project.MODEL.Autore;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.MODEL.RisorsaOnline;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaLibro;
import project.struttureDiAppoggio.RigaTabellaRisorsaOnline;

public class RisorsaOnlineDAOPostgres implements RisorsaOnlineDAO {

	private Connection conn;

	public RisorsaOnlineDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
	public String insertRisorsaOnline(RisorsaOnline risorsaOnline) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"materiale\" (titolo, doi, descrizione, url, tipo) VALUES (?, ?, ?, ?, CAST(? AS materiale_enum))");
			ps.setString(1,risorsaOnline.getTitolo());
			if(risorsaOnline.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, risorsaOnline.getDoi().toUpperCase());
			if(risorsaOnline.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,risorsaOnline.getDescrizione());
			ps.setString(4,risorsaOnline.getUrl());
			ps.setString(5, "RisorsaOnline");

			ps.execute();
			ps.close();
			conn.close();

			return ("Risorsa Online "+risorsaOnline.getTitolo()+" inserita correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}

	}

	@Override
	public RisorsaOnline getRisorsaOnline(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			RisorsaOnline risorsaOnline = new RisorsaOnline(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("url"));

			rs.close();
			ps.close();
			return risorsaOnline;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateRisorsaOnline(RisorsaOnline risorsaOnlineModificato, RisorsaOnline risorsaOnlineOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"materiale\" SET (titolo, doi, descrizione, url) = (?, ?, ?, ?) WHERE titolo = ?");
			ps.setString(1,risorsaOnlineModificato.getTitolo());
			if(risorsaOnlineModificato.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, risorsaOnlineModificato.getDoi().toUpperCase());
			if(risorsaOnlineModificato.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,risorsaOnlineModificato.getDescrizione());
			ps.setString(4,risorsaOnlineModificato.getUrl());
			ps.setString(5, risorsaOnlineOriginale.getTitolo());

			ps.execute();
			ps.close();
			conn.close();

			return ("Risorsa Online "+risorsaOnlineModificato.getTitolo()+" modificata correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}

	}

	@Override
	public boolean identico(RisorsaOnline risorsaOnline) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)"
					+ "FROM \"materiale\""
					+ "WHERE (\"titolo\" = ? AND \"nrighe\" = ? AND  \"ncolonne\" = ? AND \"url\" = ?)");
			ps.setString(1,risorsaOnline.getTitolo());
			ps.setString(2,risorsaOnline.getUrl());

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


