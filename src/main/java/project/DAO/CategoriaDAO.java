package project.DAO;

import java.util.List;

import project.MODEL.Categoria;

public interface CategoriaDAO {

	public boolean checkUniqueCategoria(Categoria categoria);

	public void insertCategoria(Categoria categoria);

	public Categoria getSupercategoria(Categoria categoria);

	public void insertSupercategoria(Categoria categoria, Categoria supercategoria);

	public void setSupercategoria(Categoria categoria, Categoria supercategoria);

	public List<Categoria> getAllCategorie();

	public void insertCategoriaInGerarchia(Categoria categoria);

	public List<Categoria> getCategorie(String titoloMateriale);


}
