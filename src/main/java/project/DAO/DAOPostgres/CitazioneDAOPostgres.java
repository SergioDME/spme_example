package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.CitazioneDAO;
import project.MODEL.Citazione;
import project.MODEL.Conferenza;
import project.MODEL.ParolaChiave;
import project.DAO.ConnectionPostgres;

public class CitazioneDAOPostgres implements CitazioneDAO{

	private Connection conn;

	public CitazioneDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public boolean inserisciCitazione(Citazione citazione) {

		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"citazione\" VALUES (?, ?)");
			ps.setString(1, citazione.getMaterialeCitante().getTitolo());
			ps.setString(2, citazione.getMaterialeCitato().getTitolo());



			ps.execute();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean checkUniqueCitazione(Citazione citazione) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"citazione\" WHERE \"titolocitante\" = ? AND \"titolocitato\" = ?");
			ps.setString(1, citazione.getMaterialeCitante().getTitolo());
			ps.setString(2, citazione.getMaterialeCitato().getTitolo());

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

	public List<Citazione> getCitazioni(String titoloMateriale) {
		List<Citazione> listaCitazione = new LinkedList<Citazione>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * \r\n"
					+ "FROM citazione AS c\r\n"
					+ "WHERE c.titolocitante = ? OR c.titolocitato = ?\r\n"
					+ "");
			ps.setString(1, titoloMateriale);
			ps.setString(2, titoloMateriale);



			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Citazione citazione = new Citazione(rs.getString("titolocitante"), rs.getString("titolocitato"));
				listaCitazione.add(citazione);
			}


			ps.close();
			rs.close();
			conn.close();

			return listaCitazione;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Citazione> getCitati(String titolo) {
		List<Citazione> listaCitazione = new LinkedList<Citazione>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * \r\n"
					+ "FROM citazione AS c\r\n"
					+ "WHERE c.titolocitante = ?\r\n"
					+ "");
			ps.setString(1, titolo);



			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Citazione citazione = new Citazione(titolo,rs.getString("titolocitato"));
				listaCitazione.add(citazione);
			}


			ps.close();
			rs.close();
			conn.close();

			return listaCitazione;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String eliminaCitazione(Citazione citazione) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM \"citazione\" WHERE titolocitante = ? AND titolocitato = ?");
			ps.setString(1, citazione.getMaterialeCitante().getTitolo());
			ps.setString(2, citazione.getMaterialeCitato().getTitolo());


			ps.execute();
			ps.close();
			conn.close();
			return ("Citazione a "+citazione.getMaterialeCitato().getTitolo()+" eliminata");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}


}
