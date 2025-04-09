package project.DAO.DAOPostgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import project.DAO.CategoriaDAO;
import project.MODEL.Categoria;
import project.MODEL.Citazione;
import project.MODEL.Libro;
import project.MODEL.Materiale;
import project.DAO.ConnectionPostgres;

public class CategoriaDAOPostgres implements CategoriaDAO{

	private Connection conn;

	public CategoriaDAOPostgres(ConnectionPostgres singleton) {
		conn = singleton.getConnection();
	}

	public boolean checkUniqueCategoria(Categoria categoria) {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM \"categoria\" WHERE \"nome\" = ?");
			ps.setString(1,categoria.getNome().toLowerCase());

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

	public void insertCategoria(Categoria categoria) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"categoria\" (nome) VALUES (?)");
			ps.setString(1,categoria.getNome().toLowerCase());

			ps.execute();
			ps.close();
			conn.close();

			return;
		} catch (SQLException e) {
			return;
		}
	}

	public Categoria getSupercategoria(Categoria categoria) {
		Categoria supercategoria = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT \"supercategoria\" FROM \"categoria\" WHERE \"nome\" = ?");
			ps.setString(1,categoria.getNome().toLowerCase());
			ResultSet rs = ps.executeQuery();

			rs.next();

			supercategoria = new Categoria(rs.getString("supercategoria"));

			rs.close();
			ps.close();
			conn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return supercategoria;
	}

	public void insertSupercategoria(Categoria categoria, Categoria supercategoria) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"categoria\" VALUES (?, ?)");
			PreparedStatement ps2 = conn.prepareStatement("UPDATE \"categoria\" SET \"supercategoria\" = ? WHERE \"nome\" = ?")	;

			ps.setString(1, supercategoria.getNome().toLowerCase());
			ps.setNull(2, Types.VARCHAR);
			ps2.setString(1, supercategoria.getNome().toLowerCase());
			ps2.setString(2, categoria.getNome().toLowerCase());

			ps.execute();
			ps2.execute();
			ps.close();
			ps2.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setSupercategoria(Categoria categoria, Categoria supercategoria) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE \"categoria\" SET \"supercategoria\" = ? WHERE \"nome\" = ?");

			ps.setString(1,supercategoria.getNome().toLowerCase());
			ps.setString(2,categoria.getNome().toLowerCase());

			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Categoria> getAllCategorie() {
		List<Categoria> lista = new LinkedList<Categoria>();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM categoria ORDER BY nome");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Categoria categoria = new Categoria(rs.getString("nome"));
				if(!(rs.getString("supercategoria") == null)) {
					Categoria supercategoria = new Categoria(rs.getString("supercategoria"));
					categoria.setSupercategoria(supercategoria);
				}
				lista.add(categoria);
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

	public void insertCategoriaInGerarchia(Categoria categoria) {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO \"categoria\" (nome, supercategoria) VALUES (?, ?)");
			ps.setString(1,categoria.getNome().toLowerCase());
			ps.setString(2, categoria.getSupercategoria().getNome().toLowerCase());

			ps.execute();
			ps.close();
			conn.close();

			return;
		} catch (SQLException e) {
			return;
		}
	}

	public List<Categoria> getCategorie(String titoloMateriale) {
		List<Categoria> listaCategorie = new LinkedList<Categoria>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT nome, supercategoria\r\n"
					+ "FROM categoria as c\r\n"
					+ "JOIN categoriadiappartenenza as ca\r\n"
					+ "ON c.nome = ca.nomecategoria \r\n"
					+ "WHERE ca.titolomateriale = ?");
			ps.setString(1, titoloMateriale);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Categoria supercategoria = new Categoria(rs.getString("supercategoria"));
				Categoria categoria = new Categoria(rs.getString("nome"));
				categoria.setSupercategoria(supercategoria);
				listaCategorie.add(categoria);
			}


			ps.close();
			rs.close();
			conn.close();

			return listaCategorie;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
