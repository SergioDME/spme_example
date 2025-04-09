package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.LibroDAO;
import project.MODEL.Autore;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.MODEL.RisorsaOnline;
import project.DAO.ConnectionPostgres;
import project.enumerazioni.GENERE_ENUM;
import project.struttureDiAppoggio.RigaTabellaLibro;


public class LibroDAOPostgres implements LibroDAO{

	private Connection conn;

	public LibroDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public String insertLibro(Libro libro){
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"materiale\" (titolo, doi, descrizione, isbn, genere, npagine, editore, tipo) VALUES (?, ?, ?, ?, CAST(? AS genere_enum), ?, ?, CAST(? AS materiale_enum))");
			ps.setString(1,libro.getTitolo());
			if(libro.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, libro.getDoi());
			if(libro.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,libro.getDescrizione());
			ps.setString(4,libro.getIsbn());
			if(libro.getGenere() == null)
				ps.setNull(5, Types.VARCHAR);
			else
				ps.setString(5,libro.getGenere().toString());
			ps.setInt(6,libro.getNpagine());
			ps.setString(7,libro.getEditore());
			ps.setString(8, "Libro");

			ps.execute();
			ps.close();
			conn.close();

			return ("Libro "+libro.getTitolo()+" inserito correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public void deleteLibro(Libro libro) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM \"materiale\" WHERE titolo = ? ");
			ps.setString(1,libro.getTitolo());

			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {

		}
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

	public boolean checkUniqueISBN(String isbn) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"materiale\" WHERE \"isbn\" = ?");
			ps.setString(1,isbn);

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

	public Libro getLibro(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"materiale\" WHERE \"titolo\" = ?");
			ps.setString(1,titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Libro libro = new Libro(rs.getString("titolo"), rs.getString("doi"), rs.getString("isbn"), rs.getString("genere"),rs.getInt("npagine"), rs.getString("editore"), rs.getString("descrizione"));

			rs.close();
			ps.close();
			return libro;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String updateLibro(Libro libroModificato, Libro libroOriginale) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"materiale\" SET (titolo, doi, descrizione, isbn, genere, npagine, editore) = (?, ?, ?, ?, CAST(? AS genere_enum), ?, ?) WHERE titolo = ?");
			ps.setString(1,libroModificato.getTitolo());
			if(libroModificato.getDoi().isBlank())
				ps.setNull(2,Types.VARCHAR);
			else
				ps.setString(2, libroModificato.getDoi().toUpperCase());
			if(libroModificato.getDescrizione().isBlank())
				ps.setNull(3,Types.LONGVARCHAR);
			else
				ps.setString(3,libroModificato.getDescrizione());
			ps.setString(4,libroModificato.getIsbn());
			if(libroModificato.getGenere() == null)
				ps.setNull(5, Types.VARCHAR);
			else
				ps.setString(5,libroModificato.getGenere().toString());
			ps.setInt(6,libroModificato.getNpagine());
			ps.setString(7,libroModificato.getEditore());
			ps.setString(8, libroOriginale.getTitolo());

			ps.execute();
			ps.close();
			conn.close();

			return ("Libro "+libroModificato.getTitolo()+" modificato correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public boolean identico(Libro libro) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*)"
					+ "FROM \"materiale\""
					+ "WHERE (\"titolo\" = ? AND \"isbn\" = ? AND  \"npagine\" = ? AND \"editore\" = ?)");
			ps.setString(1,libro.getTitolo());
			ps.setString(2,libro.getIsbn());
			ps.setInt(3,libro.getNpagine());
			ps.setString(4,libro.getEditore());

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
