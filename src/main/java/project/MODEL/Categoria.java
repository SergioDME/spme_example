package project.MODEL;

public class Categoria {
	private Categoria supercategoria = null;
	private String nome;

	public Categoria(String categoria) {
		nome = categoria;
	}

	public Categoria getSupercategoria() {
		return supercategoria;
	}

	public void setSupercategoria(Categoria supercategoria) {
		this.supercategoria = supercategoria;
	}

	public String getNome() {
		return nome;
	}
}
