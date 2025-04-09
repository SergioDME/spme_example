package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.ArticoloSuRivistaDAO;
import project.MODEL.ArticoloSuConferenza;
import project.MODEL.ArticoloSuRivista;
import project.MODEL.Autore;
import project.MODEL.Conferenza;
import project.MODEL.Dataset;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.MODEL.Rivista;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;
import project.struttureDiAppoggio.RigaTabellaArticoloSuRivista;
import project.struttureDiAppoggio.RigaTabellaDataset;

public class ArticoloSuRivistaDAOPostgres implements ArticoloSuRivistaDAO{

	private Connection conn;

	public ArticoloSuRivistaDAOPostgres(ConnectionPostgres singleton) {
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

	public String insertArticoloSuConferenza(ArticoloSuRivista articoloSuRivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"materiale\" (titolo, doi, descrizione, target, issnrivista, tipo) VALUES (?, ?, ?, ?, ?, CAST(? AS materiale_enum))");
			ps.setString(1,articoloSuRivista.getTitolo());
			if(articoloSuRivista.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, articoloSuRivista.getDoi().toUpperCase());
			if(articoloSuRivista.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,articoloSuRivista.getDescrizione());
			if(articoloSuRivista.getTarget().isBlank())
				ps.setNull(4, Types.VARCHAR);
			else
				ps.setString(4, articoloSuRivista.getTarget());
			ps.setString(5, articoloSuRivista.getRivista().getIssn());
			ps.setString(6, "ArticoloSuRivista");

			ps.execute();
			ps.close();
			conn.close();

			return ("Articolo "+articoloSuRivista.getTitolo()+" inserito correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public ArticoloSuRivista getArticoloSuRivista(String titolo, Rivista rivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			ArticoloSuRivista articoloSuRivista = new ArticoloSuRivista(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), rivista);

			rs.close();
			ps.close();
			return articoloSuRivista;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public boolean checkUniqueISSNRivista(String issn) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"rivista\" WHERE \"issn\" = ?");
			ps.setString(1,issn);

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

	public boolean checkUniqueTitoloRivista(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"rivista\" WHERE \"titolo\" = ?");
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

	public String updateArticolo(ArticoloSuRivista articoloModificato, ArticoloSuRivista articoloOriginale) {
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

	public boolean identico(ArticoloSuRivista articoloSuRivista) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)"
					+ "FROM \"materiale\""
					+ "WHERE (\"titolo\" = ? AND \"issnrivista\" = ? AND \"titolo\" = ?)");
			ps.setString(1, articoloSuRivista.getTitolo());
			ps.setString(2, articoloSuRivista.getRivista().getIssn());
			ps.setString(3, articoloSuRivista.getRivista().getTitolo());


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
