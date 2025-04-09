package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import project.DAO.ParolaChiaveDelMaterialeDAO;
import project.MODEL.ParolaChiaveDelMateriale;
import project.DAO.ConnectionPostgres;

public class ParolaChiaveDelMaterialeDAOPostgres implements ParolaChiaveDelMaterialeDAO{
	private Connection conn;

	public ParolaChiaveDelMaterialeDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public void insertAssociazione(ParolaChiaveDelMateriale parolaDelMateriale) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"parolachiavedelmateriale\" VALUES (?, ?)");
			ps.setString(1,parolaDelMateriale.getParolaChiave().getParola().toLowerCase());
			ps.setString(2,parolaDelMateriale.getMateriale().getTitolo());


			ps.execute();
			ps.close();
			conn.close();

			return;
		} catch (SQLException e) {
			return;
		}

	}

	public boolean checkUniqueAssociazione(ParolaChiaveDelMateriale parolaDelMateriale) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"parolachiavedelmateriale\" WHERE \"parola\" = ? AND \"titolomateriale\" = ?");
			ps.setString(1,parolaDelMateriale.getParolaChiave().getParola().toLowerCase());
			ps.setString(2,parolaDelMateriale.getMateriale().getTitolo());

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

	public String eliminaParolaDelMateriale(ParolaChiaveDelMateriale parolaDelMateriale) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM parolachiavedelmateriale WHERE parola = ? AND titolomateriale = ?;");
			ps.setString(1,parolaDelMateriale.getParolaChiave().getParola().toLowerCase());
			ps.setString(2,parolaDelMateriale.getMateriale().getTitolo());

			ps.execute();
			ps.close();
			conn.close();

			return (parolaDelMateriale.getParolaChiave().getParola()+" eliminata");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
}
