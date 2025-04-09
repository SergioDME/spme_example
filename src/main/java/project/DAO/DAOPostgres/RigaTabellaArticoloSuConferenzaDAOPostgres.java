package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaArticoloSuConferenzaDAO;
import project.MODEL.ArticoloSuConferenza;
import project.MODEL.Autore;
import project.MODEL.Conferenza;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;

public class RigaTabellaArticoloSuConferenzaDAOPostgres implements RigaTabellaArticoloSuConferenzaDAO{

	private Connection conn;

	public RigaTabellaArticoloSuConferenzaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public List<RigaTabellaArticoloSuConferenza> getArticoliSuConferenza(){
		List <RigaTabellaArticoloSuConferenza> listaArticoli = new LinkedList<RigaTabellaArticoloSuConferenza>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, target, co.titolo AS titoloconferenza, co.dataconferenza, co.casaeditrice, co.durata, co.sede, numero_citazioni\r\n"
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
					+ "	JOIN conferenza AS co\r\n"
					+ "	ON m.titoloconferenza = co.titolo\r\n"
					+ "WHERE tipo = 'ArticoloSuConferenza'\r\n"
					+ "ORDER BY m.titolo ASC");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Conferenza conferenza = new Conferenza(rs.getString("titoloconferenza"), rs.getInt("durata"), rs.getString("sede"), rs.getString("casaeditrice"), rs.getDate("dataconferenza"));
				ArticoloSuConferenza articolo = new ArticoloSuConferenza(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), conferenza, rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, articolo, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaArticoloSuConferenza rigaTabella = new RigaTabellaArticoloSuConferenza(autore, pubblicazione, articolo);

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

	public RigaTabellaArticoloSuConferenza getRigaArticoloSuConferenza(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, target, conf.titolo AS titoloconferenza, conf.dataconferenza, conf.sede, conf.casaeditrice, conf.durata, numero_citazioni\r\n"
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
					+ "	JOIN conferenza AS conf\r\n"
					+ "	ON m.titoloconferenza = conf.titolo AND m.dataconferenza = conf.dataconferenza\r\n"
					+ "WHERE m.titolo = ? \r\n"
					+ "ORDER BY m.titolo ASC\r\n"
					+ "\r\n"
					+ "");
			ps.setString(1, titolo);

			ResultSet rs = ps.executeQuery();

			rs.next();
			Conferenza conferenza = new Conferenza(rs.getString("titoloconferenza"), rs.getInt("durata"), rs.getString("sede"), rs.getString("casaeditrice"), rs.getDate("dataconferenza"));
			ArticoloSuConferenza articolo = new ArticoloSuConferenza(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("target"), conferenza, rs.getInt("numero_citazioni"));
			Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
			Pubblicazione pubblicazione = new Pubblicazione(autore, articolo, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

			RigaTabellaArticoloSuConferenza rigaTabella = new RigaTabellaArticoloSuConferenza(autore, pubblicazione, articolo);




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
