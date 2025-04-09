package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.DAO.CategoriaDiAppartenenzaDAO;
import project.MODEL.CategoriaDiAppartenenza;
import project.MODEL.ParolaChiave;
import project.DAO.ConnectionPostgres;

public class CategoriaDiAppartenenzaDAOPostgres implements CategoriaDiAppartenenzaDAO{

	private Connection conn;

	public CategoriaDiAppartenenzaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public boolean checkUniqueAssociazione(CategoriaDiAppartenenza categoriaDiAppartenenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"categoriadiappartenenza\" WHERE \"nomecategoria\" = ? AND \"titolomateriale\" = ?");
			ps.setString(1,categoriaDiAppartenenza.getCategoria().getNome().toLowerCase());
			ps.setString(2,categoriaDiAppartenenza.getMateriale().getTitolo());

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

	public void insertAssociazione(CategoriaDiAppartenenza categoriaDiAppartenenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"categoriadiappartenenza\" VALUES (?, ?)");
			ps.setString(1,categoriaDiAppartenenza.getCategoria().getNome().toLowerCase());
			ps.setString(2,categoriaDiAppartenenza.getMateriale().getTitolo());


			ps.execute();
			ps.close();
			conn.close();

			return;
		} catch (SQLException e) {
			return;
		}

	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String eliminaCategoriaDiAppartenenza(CategoriaDiAppartenenza categoriaDiAppartenenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM \"categoriadiappartenenza\" WHERE nomecategoria = ? AND titolomateriale = ?");
			ps.setString(1,categoriaDiAppartenenza.getCategoria().getNome().toLowerCase());
			ps.setString(2,categoriaDiAppartenenza.getMateriale().getTitolo());


			ps.execute();
			ps.close();
			conn.close();

			return ("Categoria "+categoriaDiAppartenenza.getCategoria().getNome()+" eliminata da "+categoriaDiAppartenenza.getMateriale().getTitolo());
		} catch (SQLException e) {
			return e.getMessage();
		}
	}

	public boolean checkNotLoop(CategoriaDiAppartenenza categoriaDiAppartenenza) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) \r\n"
					+ "		FROM\r\n"
					+ "		( WITH RECURSIVE Supercategoria(nomeC, super) as \r\n"
					+ "			((SELECT nome, supercategoria\r\n"
					+ "		  		FROM categoria)\r\n"
					+ "			UNION\r\n"
					+ "			 (SELECT categoria.nome, Supercategoria.super\r\n"
					+ "		  		FROM categoria, Supercategoria\r\n"
					+ "		  		WHERE categoria.supercategoria = Supercategoria.nomeC))\r\n"
					+ "		  SELECT Supercategoria.super\r\n"
					+ "		  FROM categoria join\r\n"
					+ "		 	   Supercategoria\r\n"
					+ "		 	   on (categoria.nome = Supercategoria.nomeC)\r\n"
					+ "		  WHERE nome = ?\r\n"
					+ "		) AS S JOIN\r\n"
					+ "			 categoriadiappartenenza as C\r\n"
					+ "		  ON (S.super = C.nomecategoria AND C.titolomateriale = ?)");

			PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*)\r\n"
					+ "			FROM (\r\n"
					+ "				WITH RECURSIVE SupercategoriaAppartenenza(nomeC, super) as \r\n"
					+ "  	     			 ((SELECT nome, supercategoria\r\n"
					+ "  		    			FROM categoria)\r\n"
					+ "  		     	   UNION\r\n"
					+ "    	  			  (SELECT categoria.nome, SupercategoriaAppartenenza.super\r\n"
					+ "        				FROM categoria, SupercategoriaAppartenenza\r\n"
					+ "		   				 WHERE categoria.supercategoria = SupercategoriaAppartenenza.nomeC))\r\n"
					+ "				 SELECT SupercategoriaAppartenenza.nomeC, SupercategoriaAppartenenza.super\r\n"
					+ "  				 FROM categoria join SupercategoriaAppartenenza\r\n"
					+ "    				on (categoria.nome = SupercategoriaAppartenenza.nomeC) \r\n"
					+ "				) AS S \r\n"
					+ "				JOIN categoriadiappartenenza AS C\r\n"
					+ "				ON (S.nomeC = C.nomecategoria)\r\n"
					+ "				WHERE S.super = ? AND C.titolomateriale = ?");

			ps.setString(1, categoriaDiAppartenenza.getCategoria().getNome());
			ps.setString(2, categoriaDiAppartenenza.getMateriale().getTitolo());
			ps2.setString(1, categoriaDiAppartenenza.getCategoria().getNome());
			ps2.setString(2, categoriaDiAppartenenza.getMateriale().getTitolo());

			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();

			rs.next();
			rs2.next();

			if((rs.getInt(1) + rs2.getInt(1))< 1)
				return true;
			else {
				conn.close();
				return false;
			}

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}



}
