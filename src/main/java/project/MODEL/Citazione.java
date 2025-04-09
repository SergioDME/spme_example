package project.MODEL;

public class Citazione {
	private Materiale materialeCitante;
	private Materiale materialeCitato;

	public Citazione(Materiale citante, Materiale citato){
		materialeCitante = citante;
		materialeCitato = citato;
	}

	public Citazione(String citante, String citato) {
		materialeCitante = new Materiale(citante);
		materialeCitato = new Materiale(citato);
	}

	public Materiale getMaterialeCitante() {
		return materialeCitante;
	}

	public Materiale getMaterialeCitato() {
		return materialeCitato;
	}
}
