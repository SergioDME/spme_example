package project.MODEL;

public class ParolaChiaveDelMateriale {

	private Materiale materiale;
	private ParolaChiave parolaChiave;

	public ParolaChiaveDelMateriale(Materiale materiale, ParolaChiave parola) {
		this.materiale = materiale;
		parolaChiave = parola;
	}

	public Materiale getMateriale() {
		return materiale;
	}

	public ParolaChiave getParolaChiave() {
		return parolaChiave;
	}


}
