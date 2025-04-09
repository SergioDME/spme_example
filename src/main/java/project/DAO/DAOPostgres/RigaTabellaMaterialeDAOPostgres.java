package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.RigaTabellaMaterialeDAO;
import project.MODEL.Autore;
import project.MODEL.Materiale;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.Ricerca;
import project.struttureDiAppoggio.RigaTabellaMateriale;

public class RigaTabellaMaterialeDAOPostgres implements RigaTabellaMaterialeDAO  {

	private Connection conn;

	public RigaTabellaMaterialeDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}


	@Override
	public List<RigaTabellaMateriale> getRigheMateriali() {
		List<RigaTabellaMateriale> lista = new LinkedList<RigaTabellaMateriale>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, tipo, numero_citazioni\r\n"
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
					+ "ORDER BY m.titolo ASC");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Materiale materiale = new Materiale(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("tipo"), rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, materiale, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaMateriale rigaTabella = new RigaTabellaMateriale(autore, pubblicazione, materiale);

				lista.add(rigaTabella);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}


	@Override
	public List<RigaTabellaMateriale> getRigheRicerca(Ricerca ricerca) {
		List<RigaTabellaMateriale> lista = new LinkedList<RigaTabellaMateriale>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT a.nome, a.cognome, a.orcid, p.datapubblicazione, p.datainserimento, m.titolo, doi, descrizione, tipo, numero_citazioni\r\n"
					+ "FROM autore as a\r\n"
					+ "\r\n"
					+ "JOIN pubblicazione as p\r\n"
					+ "ON a.orcid = p.orcid\r\n"
					+ "\r\n"
					+ "JOIN materiale AS m \r\n"
					+ "on p.titolo = m.titolo\r\n"
					+ "\r\n"
					+ "LEFT JOIN\r\n"
					+ "     (SELECT titolocitato, COUNT(titolocitante) AS numero_citazioni\r\n"
					+ "						FROM citazione\r\n"
					+ "					GROUP BY titolocitato\r\n"
					+ "	) AS c\r\n"
					+ "ON m.titolo = c.titolocitato\r\n"
					+ "	\r\n"
					+ "LEFT JOIN categoriadiappartenenza AS ca\r\n"
					+ "ON ca.titolomateriale = m.titolo\r\n"
					+ "\r\n"
					+ "LEFT JOIN parolachiavedelmateriale AS pc\r\n"
					+ "ON pc.titolomateriale = m.titolo\r\n"
					+ "\r\n"
					+ "WHERE a.nome ILIKE ?\r\n"
					+ "	AND a.cognome ILIKE ?\r\n"
					+ "	AND a.orcid LIKE ?\r\n"
					+ "	\r\n"
					+ "	AND CAST(p.datainserimento AS VARCHAR) LIKE ?\r\n"
					+ "	AND (COALESCE(CAST(p.datapubblicazione AS VARCHAR), '') LIKE ?)\r\n"
					+ "	\r\n"
					+ "	AND (COALESCE(ca.nomecategoria, '') ILIKE ANY(array[?, ?, ?]))\r\n"
					+ "		 \r\n"
					+ "	AND COALESCE(pc.parola, '') ILIKE ?");

			if(ricerca.getNomeAutore().isBlank())
				ps.setString(1, "%");
			else
				ps.setString(1, ricerca.getNomeAutore());

			if(ricerca.getCognomeAutore().isBlank())
				ps.setString(2, "%");
			else
				ps.setString(2, ricerca.getCognomeAutore());

			if(ricerca.getOrcidAutore().isBlank())
				ps.setString(3, "%");
			else
				ps.setString(3, ricerca.getOrcidAutore());

			if(ricerca.getDataInserimento() == null)
				ps.setString(4, "%");
			else
			{
				java.sql.Date inserimentoSQL = new java.sql.Date(ricerca.getDataInserimento().getTime());
				ps.setString(4, inserimentoSQL.toString());
			}

			if(ricerca.getDataPubblicazione() == null)
				ps.setString(5, "%");
			else
			{
				java.sql.Date pubblicazioneSQL = new java.sql.Date(ricerca.getDataPubblicazione().getTime());
				ps.setString(5, pubblicazioneSQL.toString());
			}

			if(ricerca.getCategoria1().isBlank())
				ps.setString(6, "%");
			else
				ps.setString(6, ricerca.getCategoria1());

			if(ricerca.getCategoria1().isBlank())
				ps.setString(7, "%");
			else
			{
				if(ricerca.getCategoria2().isBlank())
					ps.setString(7, "1");
				else
					ps.setString(7, ricerca.getCategoria2());
			}

			if(ricerca.getCategoria1().isBlank())
				ps.setString(8, "%");
			else
			{
				if(ricerca.getCategoria3().isBlank())
					ps.setString(8, "1");
				else
					ps.setString(8, ricerca.getCategoria3());
			}

			if(ricerca.getParola().isBlank())
				ps.setString(9, "%");
			else
				ps.setString(9, ricerca.getParola());

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Materiale materiale = new Materiale(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("tipo"), rs.getInt("numero_citazioni"));
				Autore autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("orcid"));
				Pubblicazione pubblicazione = new Pubblicazione(autore, materiale, rs.getDate("datapubblicazione"), rs.getDate("datainserimento"));

				RigaTabellaMateriale rigaTabella = new RigaTabellaMateriale(autore, pubblicazione, materiale);

				lista.add(rigaTabella);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
