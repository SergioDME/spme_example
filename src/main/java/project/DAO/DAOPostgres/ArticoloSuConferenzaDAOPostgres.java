package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.ArticoloSuConferenzaDAO;
import project.MODEL.ArticoloSuConferenza;
import project.MODEL.ArticoloSuRivista;
import project.MODEL.Autore;
import project.MODEL.Conferenza;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.MODEL.Rivista;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;
import project.struttureDiAppoggio.RigaTabellaArticoloSuRivista;
import project.struttureDiAppoggio.RigaTabellaLibro;

public class ArticoloSuConferenzaDAOPostgres implements ArticoloSuConferenzaDAO{

	private Connection conn;

	public ArticoloSuConferenzaDAOPostgres(ConnectionPostgres singleton) {
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

	public String insertArticoloSuConferenza(ArticoloSuConferenza articoloSuConferenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"materiale\" (titolo, doi, descrizione, target, titoloconferenza, dataconferenza, tipo) VALUES (?, ?, ?, ?, ?, ?, CAST(? AS materiale_enum))");
			ps.setString(1,articoloSuConferenza.getTitolo());
			if(articoloSuConferenza.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, articoloSuConferenza.getDoi().toUpperCase());
			if(articoloSuConferenza.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,articoloSuConferenza.getDescrizione());
			if(articoloSuConferenza.getTarget().isBlank())
				ps.setNull(4, Types.VARCHAR);
			else
				ps.setString(4, articoloSuConferenza.getTarget());
			ps.setString(5, articoloSuConferenza.getConferenza().getTitolo());
			java.sql.Date dataSQL = new java.sql.Date(articoloSuConferenza.getConferenza().getDataConferenza().getTime());
			ps.setDate(6, dataSQL);
			ps.setString(7, "ArticoloSuConferenza");

			ps.execute();
			ps.close();
			conn.close();

			return ("Articolo "+articoloSuConferenza.getTitolo()+" inserito correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public ArticoloSuConferenza getArticoloSuConferenza(String titolo, Conferenza conferenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			ArticoloSuConferenza articoloSuConferenza = new ArticoloSuConferenza(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), conferenza);

			rs.close();
			ps.close();
			return articoloSuConferenza;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String updateArticolo(ArticoloSuConferenza articoloModificato, ArticoloSuConferenza articoloOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"materiale\" SET (titolo, doi, descrizione, target) = (?, ?, ?, ?) WHERE titolo = ?");
			ps.setString(1,articoloModificato.getTitolo());
			if(articoloModificato.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, articoloModificato.getDoi().toUpperCase());
			if(articoloModificato.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,articoloModificato.getDescrizione());
			ps.setString(4, articoloModificato.getTarget());

			ps.setString(5, articoloOriginale.getTitolo());

			ps.execute();
			ps.close();
			conn.close();

			return ("Articolo "+articoloModificato.getTitolo()+" modificato correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public boolean identico(ArticoloSuConferenza articoloSuConferenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)"
					+ "FROM \"materiale\""
					+ "WHERE (\"titolo\" = ? AND \"titoloconferenza\" = ? AND  \"dataconferenza\" = ?)");
			ps.setString(1, articoloSuConferenza.getTitolo());
			ps.setString(2, articoloSuConferenza.getConferenza().getTitolo());
			java.sql.Date dateSQL = new java.sql.Date(articoloSuConferenza.getConferenza().getDataConferenza().getTime());
			ps.setDate(3, dateSQL);

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
