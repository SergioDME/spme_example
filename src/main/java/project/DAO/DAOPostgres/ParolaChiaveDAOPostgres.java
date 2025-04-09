package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.DAO.ParolaChiaveDAO;
import project.MODEL.ParolaChiave;
import project.DAO.ConnectionPostgres;

public class ParolaChiaveDAOPostgres implements ParolaChiaveDAO{

	private Connection conn;

	public ParolaChiaveDAOPostgres(ConnectionPostgres singleton) {
	 	conn = singleton.getConnection();
	}

	public boolean checkUniqueParolaChiave(ParolaChiave parolaChiave) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"parolachiave\" WHERE \"parola\" = ?");
			ps.setString(1,parolaChiave.getParola().toLowerCase());

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

	public void insertParolaChiave(ParolaChiave parola) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"parolachiave\" VALUES (?)");
			ps.setString(1,parola.getParola().toLowerCase());

			ps.execute();
			ps.close();
			conn.close();

			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public List<ParolaChiave> getParoleAssociate(String titoloMateriale) {
		List<ParolaChiave> listaParole = new LinkedList<ParolaChiave>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT parola \r\n"
					+ "FROM parolachiavedelmateriale AS p\r\n"
					+ "WHERE p.titolomateriale = ?\r\n"
					+ "");
			ps.setString(1, titoloMateriale);



			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				ParolaChiave parola = new ParolaChiave(rs.getString("parola"));
				listaParole.add(parola);
			}


			ps.close();
			rs.close();
			conn.close();

			return listaParole;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}



	}
}




