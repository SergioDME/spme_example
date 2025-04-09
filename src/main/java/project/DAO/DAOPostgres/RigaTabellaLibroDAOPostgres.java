package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaLibroDAO;
import project.MODEL.Autore;
import project.MODEL.Libro;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaLibro;

public class RigaTabellaLibroDAOPostgres implements RigaTabellaLibroDAO{

	private Connection conn;

	public RigaTabellaLibroDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public RigaTabellaLibro getRigaLibro(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, isbn, genere, npagine, editore, numero_citazioni\r\n"
					+ "FROM autore as a\r\n"
					+ "	JOIN	\r\n"
					+ "	pubblicazione as p\r\n"
					+ "	ON a.orcid = p.orcid\r\n"
					+ "	JOIN\r\n"
					+ "	materiale AS m \r\n"
					+ "	on p.titolo = m.titolo\r\n"
					+ "\r\n"
					+ "	LEFT JOIN\r\n"
					+ "     (SELECT titolocitato, COUNT(titolocitante) AS numero_citazioni\r\n"
					+ "	FROM citazione\r\n"
					+ "	GROUP BY titolocitato\r\n"
					+ "     ) AS c\r\n"
					+ "	ON\r\n"
					+ "     m.titolo = c.titolocitato\r\n"
					+ "\r\n"
					+ "WHERE m.titolo = ? \r\n"
					+ "ORDER BY m.titolo ASC");

			ps.setString(1, titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();

			Libro libro = new Libro(rs.getString("titolo"), rs.getString("doi"), rs.getString("isbn"), rs.getString("genere"),rs.getInt("npagine"), rs.getString("editore"), rs.getString("descrizione"), rs.getInt("numero_citazioni"));
			Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
			Pubblicazione pubblicazione = new Pubblicazione(autore, libro, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

			RigaTabellaLibro rigaTabella = new RigaTabellaLibro(autore, pubblicazione, libro);

			rs.close();
			ps.close();
			conn.close();
			return rigaTabella;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<RigaTabellaLibro> getLibri() {
		List <RigaTabellaLibro> listaLibri = new LinkedList<RigaTabellaLibro>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, isbn, genere, npagine, editore, numero_citazioni\r\n"
					+ "FROM autore as a\r\n"
					+ "	JOIN	\r\n"
					+ "	pubblicazione as p\r\n"
					+ "	ON a.orcid = p.orcid\r\n"
					+ "	JOIN\r\n"
					+ "	materiale AS m \r\n"
					+ "	on p.titolo = m.titolo\r\n"
					+ "\r\n"
					+ "	LEFT JOIN\r\n"
					+ "     (SELECT titolocitato, COUNT(titolocitante) AS numero_citazioni\r\n"
					+ "	FROM citazione\r\n"
					+ "	GROUP BY titolocitato\r\n"
					+ "     ) AS c\r\n"
					+ "	ON\r\n"
					+ "     m.titolo = c.titolocitato\r\n"
					+ "\r\n"
					+ "WHERE tipo = 'Libro'\r\n"
					+ "ORDER BY m.titolo ASC");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Libro libro = new Libro(rs.getString("titolo"), rs.getString("doi"), rs.getString("isbn"), rs.getString("genere"),rs.getInt("npagine"), rs.getString("editore"), rs.getString("descrizione"), rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, libro, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaLibro rigaTabella = new RigaTabellaLibro(autore, pubblicazione, libro);

				listaLibri.add(rigaTabella);
			}

			rs.close();
			ps.close();
			conn.close();
			return listaLibri;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
