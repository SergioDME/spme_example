package project.DAO;

import java.util.List;

import project.MODEL.Materiale;

public interface MaterialeDAO {

	public List<Materiale> getMaterialiDiAutore(String orcid);

	public void eliminaMateriale(Materiale materiale);

	public List<Materiale> getMateriali();

	public void controllaIntegrita();



}
