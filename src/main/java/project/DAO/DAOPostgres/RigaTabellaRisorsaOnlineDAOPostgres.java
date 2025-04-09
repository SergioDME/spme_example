package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaRisorsaOnlineDAO;
import project.MODEL.Autore;
import project.MODEL.Pubblicazione;
import project.MODEL.RisorsaOnline;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaRisorsaOnline;

public class RigaTabellaRisorsaOnlineDAOPostgres implements RigaTabellaRisorsaOnlineDAO {

	private Connection conn;

	public RigaTabellaRisorsaOnlineDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	@Override
	public List<RigaTabellaRisorsaOnline> getRisorsaOnline() {
		List <RigaTabellaRisorsaOnline> listaRisorsaOnline = new LinkedList<RigaTabellaRisorsaOnline>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, url, numero_citazioni\r\n"
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
					+ "WHERE tipo = 'RisorsaOnline'\r\n"
					+ "ORDER BY m.titolo ASC");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				RisorsaOnline risorsaOnline = new RisorsaOnline(rs.getString("titolo"), rs.getString("doi"), rs.getString("url"), rs.getString("descrizione"), rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, risorsaOnline, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaRisorsaOnline rigaTabella = new RigaTabellaRisorsaOnline(autore, pubblicazione, risorsaOnline);

				listaRisorsaOnline.add(rigaTabella);
			}

			rs.close();
			ps.close();
			return listaRisorsaOnline;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
}

	@Override
	public RigaTabellaRisorsaOnline getRigaRisorsaOnline(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, url,numero_citazioni\r\n"
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

			RisorsaOnline risorsaOnline = new RisorsaOnline(rs.getString("titolo"), rs.getString("doi"),rs.getString("descrizione"), rs.getString("url"), rs.getInt("numero_citazioni"));
			Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
			Pubblicazione pubblicazione = new Pubblicazione(autore, risorsaOnline, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

			RigaTabellaRisorsaOnline rigaTabella = new RigaTabellaRisorsaOnline(autore, pubblicazione, risorsaOnline);

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
