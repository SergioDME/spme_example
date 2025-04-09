package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import project.DAO.AutoreDAO;
import project.MODEL.Autore;
import project.DAO.ConnectionPostgres;

public class AutoreDAOPostgres implements AutoreDAO{

	private Connection conn;

	public AutoreDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public String insertAutore(String nome, String cognome, String oRCID, String username, String password) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"autore\" VALUES (?, ?,?, ?, ?);");
			ps.setString(1, (nome.substring(0,1).toUpperCase() + nome.substring(1)));
			ps.setString(2, (cognome.substring(0,1).toUpperCase() + cognome.substring(1)));
			ps.setString(3,username);
			ps.setString(4,password);
			ps.setString(5,oRCID);

			ps.execute();
			ps.close();
			conn.close();

			return ("Autore "+ (nome.substring(0,1).toUpperCase() + nome.substring(1))+" "+(cognome.substring(0,1).toUpperCase() + cognome.substring(1))+" registrato correttamente");
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public boolean checkNotUniqueORCID(String ORCID) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"autore\" WHERE \"orcid\" = ?");
			ps.setString(1,ORCID);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)!=0) {
				ps.close();
				rs.close();
				conn.close();
				return true;
			}
			rs.close();
			ps.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean checkPresenzaUsername(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"autore\" WHERE \"username\" = ?");
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)==0) {
				ps.close();
				rs.close();
				conn.close();
				return false;
			}
			rs.close();
			ps.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean checkUniqueUsername(String username) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"autore\" WHERE \"username\" = ?");
			ps.setString(1,username);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if(rs.getInt(1)!=0) {
				ps.close();
				rs.close();
				conn.close();
				return true;
			}
			rs.close();
			ps.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public Autore getAutore(String username, String passwordString) {
		Autore autore = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"autore\" WHERE \"username\" = ? AND \"password\" = ?");
			ps.setString(1,username);
			ps.setString(2,passwordString);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("ORCID"), rs.getString("username"), rs.getString("password"));
			}

			ps.close();
			rs.close();
			conn.close();

			return autore;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Autore> getAutori() {

		Autore autore = null;
		List<Autore> listaAutori = new LinkedList<Autore>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"autore\"");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				autore = new Autore(rs.getString("nome"), rs.getString("cognome"), rs.getString("ORCID"), rs.getString("username"), rs.getString("password"));
				listaAutori.add(autore);
			}

			ps.close();
			rs.close();
			conn.close();

			return listaAutori;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean deleteAutore(Autore autore) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM \"autore\" WHERE \"orcid\" = ?");
			ps.setString(1, autore.getOrcid());

			ps.execute();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateAutore(String nome, String cognome, String orcid, String username, Autore autore) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"autore\" SET \"nome\" = ?, \"cognome\" = ?, \"orcid\" = ?,  \"username\" = ? WHERE \"orcid\" = ?");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, orcid);
			ps.setString(4, username);
			ps.setString(5, autore.getOrcid());


			ps.execute();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateAutore(String nome, String cognome, String orcid, String username, String passwordString, Autore autore) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"autore\" SET \"nome\" = ?, \"cognome\" = ?, \"orcid\" = ?,  \"username\" = ? , \"password\" = ? WHERE \"orcid\" = ?");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, orcid);
			ps.setString(4, username);
			ps.setString(5, passwordString);
			ps.setString(6, autore.getOrcid());


			ps.execute();
			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
