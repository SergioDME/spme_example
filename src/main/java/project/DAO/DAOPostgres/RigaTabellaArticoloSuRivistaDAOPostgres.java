package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaArticoloSuRivistaDAO;
import project.MODEL.ArticoloSuRivista;
import project.MODEL.Autore;
import project.MODEL.Pubblicazione;
import project.MODEL.Rivista;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaArticoloSuRivista;

public class RigaTabellaArticoloSuRivistaDAOPostgres implements RigaTabellaArticoloSuRivistaDAO{

	private Connection conn;

	public RigaTabellaArticoloSuRivistaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public List<RigaTabellaArticoloSuRivista> getArticoliSuRivista() {
		List <RigaTabellaArticoloSuRivista> listaArticoli = new LinkedList<RigaTabellaArticoloSuRivista>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, target, r.titolo AS titolorivista, r.issn, r.periodicita, r.editore, numero_citazioni\r\n"
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
					+ "	JOIN rivista AS r\r\n"
					+ "	ON m.issnrivista = r.issn\r\n"
					+ "WHERE tipo = 'ArticoloSuRivista'\r\n"
					+ "ORDER BY m.titolo ASC\r\n"
					+ "\r\n"
					+ "");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Rivista rivista = new Rivista(rs.getString("titolorivista"), rs.getString("editore"), rs.getString("issn"), rs.getString("periodicita"));
				ArticoloSuRivista articolo = new ArticoloSuRivista(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), rivista, rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, articolo, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaArticoloSuRivista rigaTabella = new RigaTabellaArticoloSuRivista(autore, pubblicazione, articolo);

				listaArticoli.add(rigaTabella);
			}

			rs.close();
			ps.close();
			conn.close();
			return listaArticoli;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public RigaTabellaArticoloSuRivista getRigaArticoloSuRivista(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, target, r.titolo AS titolorivista, r.issn, r.periodicita, r.editore, numero_citazioni\r\n"
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
					+ "	JOIN rivista AS r\r\n"
					+ "	ON m.issnrivista = r.issn\r\n"
					+ "WHERE m.titolo = ? \r\n"
					+ "ORDER BY m.titolo ASC\r\n"
					+ "\r\n"
					+ "");
			ps.setString(1, titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();
			Rivista rivista = new Rivista(rs.getString("titolorivista"), rs.getString("editore"), rs.getString("issn"), rs.getString("periodicita"));
			ArticoloSuRivista articolo = new ArticoloSuRivista(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), rivista, rs.getInt("numero_citazioni"));
			Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
			Pubblicazione pubblicazione = new Pubblicazione(autore, articolo, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

			RigaTabellaArticoloSuRivista rigaTabella = new RigaTabellaArticoloSuRivista(autore, pubblicazione, articolo);

			rs.close();
			ps.close();
			conn.close();
			return rigaTabella;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}


}
