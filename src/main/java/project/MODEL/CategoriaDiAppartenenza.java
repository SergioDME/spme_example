package project.MODEL;

public class CategoriaDiAppartenenza {
	private Categoria categoria;
	private Materiale materiale;

	public CategoriaDiAppartenenza(Categoria categoria, Materiale materiale) {
		this.categoria = categoria;
		this.materiale = materiale;
	}

	public Materiale getMateriale() {
		return materiale;
	}

	public Categoria getCategoria() {
		return categoria;
	}
}
