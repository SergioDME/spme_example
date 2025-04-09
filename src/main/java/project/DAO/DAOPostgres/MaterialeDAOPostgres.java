package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.MaterialeDAO;
import project.MODEL.Autore;
import project.MODEL.Materiale;
import project.MODEL.Pubblicazione;
import project.DAO.ConnectionPostgres;
import project.struttureDiAppoggio.RigaTabellaMateriale;

public class MaterialeDAOPostgres implements MaterialeDAO{

	private Connection conn;

	public MaterialeDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public List<Materiale> getMaterialiDiAutore(String orcid) {
		List<Materiale> lista = new LinkedList<Materiale>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * "
														+ "FROM \"materiale\" AS m JOIN \"pubblicazione\" AS p "
														+ "ON p.titolo = m.titolo "
														+ "WHERE p.orcid = ?"
														+ "ORDER BY m.titolo");
			ps.setString(1, orcid);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Materiale materiale = new Materiale(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("tipo"));
				lista.add(materiale);
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

	public void eliminaMateriale(Materiale materiale) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM pubblicazione WHERE titolo = ?;"
					+ "DELETE FROM parolachiavedelmateriale WHERE titolomateriale = ?;"
					+ "DELETE FROM categoriadiappartenenza WHERE titolomateriale = ?;"
					+ "DELETE FROM citazione WHERE titolocitato = ? OR titolocitante = ?;"
					+ "DELETE FROM materiale WHERE titolo = ?");

			ps.setString(1, materiale.getTitolo());
			ps.setString(2, materiale.getTitolo());
			ps.setString(3, materiale.getTitolo());
			ps.setString(4, materiale.getTitolo());
			ps.setString(5, materiale.getTitolo());
			ps.setString(6, materiale.getTitolo());


			ps.execute();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Materiale> getMateriali() {
		List<Materiale> lista = new LinkedList<Materiale>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT titolo, doi, descrizione, tipo, numero_citazioni\r\n"
					+ "FROM materiale AS m \r\n"
					+ "	LEFT JOIN\r\n"
					+ "     (SELECT titolocitato, COUNT(titolocitante) AS numero_citazioni\r\n"
					+ "	FROM citazione\r\n"
					+ "	GROUP BY titolocitato\r\n"
					+ "     ) AS c\r\n"
					+ "	ON\r\n"
					+ "     m.titolo = c.titolocitato\r\n"
					+ "ORDER BY titolo ASC\r\n");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Materiale materiale = new Materiale(rs.getString("titolo"), rs.getString("doi"), rs.getString("descrizione"), rs.getString("tipo"), rs.getInt("numero_citazioni"));
				lista.add(materiale);
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

	public void controllaIntegrita() {

		try {

			PreparedStatement ps = conn.prepareStatement("DELETE FROM materiale "
					+ "WHERE titolo NOT IN (SELECT titolo"
					+ "FROM pubblicazione");

			ps.execute();

			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
