package project.DAO;

import java.util.List;

import project.MODEL.Citazione;

public interface CitazioneDAO {

	public boolean inserisciCitazione(Citazione citazione);

	public boolean checkUniqueCitazione(Citazione citazione);

	public List<Citazione> getCitazioni(String titoloMateriale);

	public List<Citazione> getCitati(String titolo);

	public String eliminaCitazione(Citazione citazione);
}
