package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaDatasetDAO;
import project.MODEL.Autore;
import project.MODEL.Dataset;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaDataset;

public class RigaTabellaDatasetDAOPostgres implements RigaTabellaDatasetDAO{

	private Connection conn;

	public RigaTabellaDatasetDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}


	public List<RigaTabellaDataset> getDataset() {
		List <RigaTabellaDataset> listaDataset = new LinkedList<RigaTabellaDataset>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, url, numerorighe, numerocolonne, numero_citazioni\r\n"
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
					+ "WHERE tipo = 'Dataset'\r\n"
					+ "ORDER BY m.titolo ASC");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Dataset dataset = new Dataset(rs.getString("titolo"), rs.getString("doi"), rs.getString("url"),rs.getInt("numerocolonne"), rs.getInt("numerorighe"), rs.getString("descrizione"), rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, dataset, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaDataset rigaTabella = new RigaTabellaDataset(autore, pubblicazione, dataset);

				listaDataset.add(rigaTabella);
			}

			rs.close();
			ps.close();
			conn.close();
			return listaDataset;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public RigaTabellaDataset getRigaDataset(String titolo) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, url, numerorighe, numerocolonne, numero_citazioni\r\n"
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

			Dataset dataset = new Dataset(rs.getString("titolo"), rs.getString("doi"), rs.getString("url"),rs.getInt("numerocolonne"), rs.getInt("numerorighe"), rs.getString("descrizione"), rs.getInt("numero_citazioni"));
			Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
			Pubblicazione pubblicazione = new Pubblicazione(autore, dataset, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

			RigaTabellaDataset rigaTabella = new RigaTabellaDataset(autore, pubblicazione, dataset);




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
