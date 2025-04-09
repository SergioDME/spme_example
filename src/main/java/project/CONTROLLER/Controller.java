package project.CONTROLLER;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import project.DAO.DAOPostgres.*;
import project.DAO.DAOPostgres.AutoreDAOPostgres;
import project.DAO.DAOPostgres.MaterialeDAOPostgres;
import project.DAO.DAOPostgres.RigaTabellaArticoloSuConferenzaDAOPostgres;
import project.DAO.DAOPostgres.RigaTabellaMaterialeDAOPostgres;
import project.DAO.*;
import project.VIEW.*;
import project.MODEL.*;
import project.struttureDiAppoggio.Ricerca;
import project.struttureDiAppoggio.RigaTabellaArticoloSuConferenza;
import project.struttureDiAppoggio.RigaTabellaArticoloSuRivista;
import project.struttureDiAppoggio.RigaTabellaDataset;
import project.struttureDiAppoggio.RigaTabellaLibro;
import project.struttureDiAppoggio.RigaTabellaMateriale;
import project.struttureDiAppoggio.RigaTabellaRisorsaOnline;


public class Controller {
	//attributi

	//aggiungere tutte le finestre
	private HomeGUI home;
	private LibreriaGUI libreria;
	private AccessoPortaleAutoriGUI accessoPortaleAutori;
	private AccessoPortaleAmministratoreGUI accessoPortaleAmministratore;
	private RegistrazioneGUI registrazione;
	private PortaleAutoriGUI portaleAutori;
	private PortaleAmministratoreGUI portaleAmministratore;
	private Autore autore = null;
	private Boolean ADMIN = false;
	private InserisciMaterialeGUI inserisciMateriale;
	private InserimentoLibroGUI inserimentoLibro;
	private InserimentoDatasetGUI inserimentoDataset;
	private InserimentoRisorsaOnlineGUI inserimentoRisorsaOnline;
	private InserimentoArticoloSuConferenzaGUI inserimentoArticoloSuConferenza;
	private InserimentoArticoloSuRivistaGUI inserimentoArticoloSuRivista;
	private ModificaMaterialeGUI modificaMateriale;
	private RicercaGUI ricerca;

	private static ConnectionPostgres singleton;



	public Controller(){
		home = new HomeGUI(this);
		home.setVisible(true);
		libreria = new LibreriaGUI(this);
		accessoPortaleAutori = new AccessoPortaleAutoriGUI(this);
		accessoPortaleAmministratore = new AccessoPortaleAmministratoreGUI(this);
		registrazione = new RegistrazioneGUI(this);
		inserisciMateriale = new InserisciMaterialeGUI(this);
		inserimentoLibro = new InserimentoLibroGUI(this);
		inserimentoDataset = new InserimentoDatasetGUI(this);
		inserimentoRisorsaOnline = new InserimentoRisorsaOnlineGUI(this);
		inserimentoArticoloSuConferenza = new InserimentoArticoloSuConferenzaGUI(this);
		inserimentoArticoloSuRivista = new InserimentoArticoloSuRivistaGUI(this);
		ricerca = new RicercaGUI(this);


		//fare la new di tutte le altre
	}


	public static void main (String[] args) {

		Controller controller = new Controller();
		singleton = ConnectionPostgres.getInstance();

	}

	//open delle gui

	public void openHome() {
		home.setVisible(true);
		registrazione.setVisible(false);
		accessoPortaleAutori.setVisible(false);
		accessoPortaleAmministratore.setVisible(false);
	    libreria.setVisible(false);
	}

	public void openLibreria() {
		home.setVisible(false);
		libreria.setVisible(true);
	}

	public void openAccessoPortaleAutori() {
		home.setVisible(false);
		registrazione.setVisible(false);
		accessoPortaleAutori.setVisible(true);
	}

	public void openPortaleAutori() {
		accessoPortaleAutori.setVisible(false);
		portaleAutori = new PortaleAutoriGUI(this, autore);
		portaleAutori.setVisible(true);
	}

	public void openAccessoPortaleAmministratore() {
		home.setVisible(false);
		accessoPortaleAmministratore.setVisible(true);
	}

	public void openPortaleAmministratore() {
		accessoPortaleAmministratore.setVisible(false);
		portaleAmministratore = new PortaleAmministratoreGUI(this);
		portaleAmministratore.setVisible(true);
	}

	public void openInsesciMaterialeGUI() {
		portaleAutori.setVisible(false);
		inserimentoLibro.setVisible(false);
		inserimentoDataset.setVisible(false);
		inserimentoRisorsaOnline.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(false);
		inserisciMateriale.setVisible(true);
	}

	public void openRegistrazione() {
		accessoPortaleAutori.setVisible(false);
		registrazione.setVisible(true);
	}

	public void openInserimentoLibro() {
		inserisciMateriale.setVisible(false);
		inserimentoLibro.setVisible(true);
	}

	public void openDialog(String testo) {
		Dialog dialog = new Dialog(testo);
	}

	public void openAggiuntaParolaChiave(Materiale materiale) {
		inserimentoLibro.setVisible(false);
		inserimentoDataset.setVisible(false);
		inserimentoRisorsaOnline.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(false);

		AggiuntaParolaChiaveGUI aggiuntaParolaChiave = new AggiuntaParolaChiaveGUI(this, materiale);

		aggiuntaParolaChiave.setVisible(true);
	}

	public void returnToPortaleAutori() {
		inserimentoLibro.setVisible(false);
		inserimentoDataset.setVisible(false);
		inserimentoRisorsaOnline.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(false);
		inserisciMateriale.setVisible(false);

		openPortaleAutori();
	}

	public void openAggiuntaCategoria(Materiale materiale) {
		inserimentoLibro.setVisible(false);
		inserimentoDataset.setVisible(false);
		inserimentoRisorsaOnline.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(false);

		AggiuntaCategoriaGUI aggiuntaCategoria = new AggiuntaCategoriaGUI(this, materiale);
		aggiuntaCategoria.setVisible(true);
	}

	public void openAggiuntaCategoriaNuova(Materiale materiale) {

		AggiuntaCategoriaNuovaGUI aggiuntaCategoriaNuova = new AggiuntaCategoriaNuovaGUI(this, materiale);

		aggiuntaCategoriaNuova.setVisible(true);
	}

	public void openInserimentoDataset() {
		inserisciMateriale.setVisible(false);
		inserimentoDataset.setVisible(true);
	}

	public void openInserimentoRisoraOnline() {
		inserisciMateriale.setVisible(false);
		inserimentoRisorsaOnline.setVisible(true);
	}


	public void openInserimentoArticoloSuConferenza() {
		inserisciMateriale.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(true);
	}

	public void openInserimentoArticoloSuRivista() {
		inserisciMateriale.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(true);
	}


	public void openModificaMateriale() {

		MaterialeDAOPostgres materialeDAO = new MaterialeDAOPostgres(singleton);

		List<Materiale> listaMateriali = new LinkedList<Materiale>();
		listaMateriali = materialeDAO.getMaterialiDiAutore(autore.getOrcid());

		modificaMateriale = new ModificaMaterialeGUI(this, listaMateriali);
		modificaMateriale.setVisible(true);
	}

	public void openModificaMaterialeADMIN() {

		RigaTabellaMaterialeDAOPostgres rigaDAO = new RigaTabellaMaterialeDAOPostgres(singleton);

		List<RigaTabellaMateriale> listaRiga = new LinkedList<RigaTabellaMateriale>();

		listaRiga = rigaDAO.getRigheMateriali();

		ModificaMaterialiADMINGUI modificaMaterialiADMIN = new ModificaMaterialiADMINGUI(this, listaRiga);
		modificaMaterialiADMIN.setVisible(true);
	}

	public void openDettagliMateriale(Materiale materiale) {

		switch(materiale.getTipo()) {
			case ("Libro"):
				openDettagliLibro(materiale);
				break;

			case("Dataset"):
		    	openDettagliDataset(materiale);
				break;

			case("RisorsaOnline"):
				openDettagliRisorsaOnline(materiale);
				break;

			case("ArticoloSuConferenza"):
				openDettagliArticoloSuConferenza(materiale);
				break;

			case("ArticoloSuRivista"):
			    openDettagliArticoloSuRivista(materiale);
				break;
		}
	}

	public void openModificaAutoriADMIN() {

		List<Autore> listaAutori = new LinkedList<Autore>();

		AutoreDAOPostgres autoreDAO = new AutoreDAOPostgres(singleton);

		listaAutori = autoreDAO.getAutori();

		ModificaAutoriADMINGUI modificaAutori = new ModificaAutoriADMINGUI(this, listaAutori);
		modificaAutori.setVisible(true);
	}

	public void modificaAutore(Autore autore) {
		ModificaAutoreADMINGUI modificaAutore = new ModificaAutoreADMINGUI(this, autore);
		modificaAutore.setVisible(true);
	}

	public boolean updateAutore(String nome, String cognome, String orcid, String username, Autore autore) {
		if(checkBlank(nome) || checkBlank(cognome) || checkBlank(orcid) || checkBlank(username))
			return false;

		AutoreDAOPostgres autoreDAO = new AutoreDAOPostgres(singleton);

		return autoreDAO.updateAutore(nome, cognome, orcid, username, autore);
	}

	public boolean updateAutore(String nome, String cognome, String orcid, String username, String passwordString, Autore autore) {
		if(checkBlank(nome) || checkBlank(cognome) || checkBlank(orcid) || checkBlank(username) || checkBlank(passwordString))
			return false;

		AutoreDAOPostgres autoreDAO = new AutoreDAOPostgres(singleton);

		return autoreDAO.updateAutore(nome, cognome, orcid, username, passwordString, autore);
	}


	public void eliminaAutore(Autore autore) {

		AutoreDAOPostgres autoreDAO = new AutoreDAOPostgres(singleton);

		if(autoreDAO.deleteAutore(autore))
			openDialog(autore.getNome()+" "+autore.getCognome()+" eliminato");

		controllaIntegritaPubblicazioni();

		openModificaAutoriADMIN();
	}

	public void controllaIntegritaPubblicazioni() {

		MaterialeDAOPostgres materialeDAO = new MaterialeDAOPostgres(singleton);

		materialeDAO.controllaIntegrita();
	}


	public void openRicerca() {
		libreria.setVisible(false);
		ricerca.setVisible(true);
	}

	public void eseguiRicerca(Ricerca ricerca) {
		RigaTabellaMaterialeDAOPostgres rigaMaterialeDAO = new RigaTabellaMaterialeDAOPostgres(singleton);

		List<RigaTabellaMateriale> listaMateriali = null;
		listaMateriali = rigaMaterialeDAO.getRigheRicerca(ricerca);

		VisualizzaRisultatiGUI visualizzaRisultati= new VisualizzaRisultatiGUI(this, listaMateriali);
		visualizzaRisultati.setVisible(true);
	}

	public void openModificaParoleChiave(Materiale materiale) {

		List<ParolaChiave> listaParole = null;

		ParolaChiaveDAOPostgres parolaDAO = new ParolaChiaveDAOPostgres(singleton);

		listaParole = parolaDAO.getParoleAssociate(materiale.getTitolo());

		ModificaParoleAssociateGUI modificaParole = new ModificaParoleAssociateGUI(this, listaParole, materiale);
		modificaParole.setVisible(true);
	}

	public void openModificaCategorie(Materiale materiale) {

		List<Categoria> listaCategoria = null;

		CategoriaDAOPostgres CategoriaDAO = new CategoriaDAOPostgres(singleton);

		listaCategoria = CategoriaDAO.getCategorie(materiale.getTitolo());

		ModificaCategorieGUI modificaCategorie = new ModificaCategorieGUI(this, listaCategoria, materiale);
		modificaCategorie.setVisible(true);
	}

	public void openAggiungiCategoriaDaModifica(Materiale materiale) {

		AggiungiCategoriaDaModificaGUI aggiungiCategoria = new AggiungiCategoriaDaModificaGUI(this, materiale);

		aggiungiCategoria.setVisible(true);
	}

	public void openAggiungiCategoriaNuovaDaModifica(Materiale materiale) {

		AggiungiCategoriaNuovaDaModificaGUI aggiuntaCategoriaNuova = new AggiungiCategoriaNuovaDaModificaGUI(this, materiale);

		aggiuntaCategoriaNuova.setVisible(true);
	}

	public void openAggiungiCategoriaEsistenteDaModifica(Materiale materiale) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		List<Categoria> listaCategorie = null;
		listaCategorie = categoriaDAO.getAllCategorie();

		AggiungiCategoriaEsistenteDaModificaGUI aggiuntaCategoriaEsistente = new AggiungiCategoriaEsistenteDaModificaGUI(this, listaCategorie, materiale);
		aggiuntaCategoriaEsistente.setVisible(true);
	}

	public void openAggiuntaAGerarchiaEsistenteDaModifica(Materiale materiale) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		List<Categoria> listaCategorie = null;
		listaCategorie = categoriaDAO.getAllCategorie();

		AggiungiAGerarchiaEsistenteDaModificaGUI aggiuntaAGerarchiaEsistente = new AggiungiAGerarchiaEsistenteDaModificaGUI(this, listaCategorie, materiale);
		aggiuntaAGerarchiaEsistente.setVisible(true);
	}

	public void openAggiuntaGerarchiaNuovaDaModifica(Materiale materiale) {

		AggiungiGerarchiaNuovaDaModificaGUI aggiuntaGerarchiaNuova = new AggiungiGerarchiaNuovaDaModificaGUI(this, materiale);
		aggiuntaGerarchiaNuova.setVisible(true);
	}

	public void openAggiungiCategoriaAGerarchiaDaModifica(Materiale materiale, Categoria supercategoria) {

		AggiungiCategoriaAGerarchiaDaModificaGUI aggiuntaCategoriaAGerarchia = new AggiungiCategoriaAGerarchiaDaModificaGUI(this, materiale, supercategoria);
		aggiuntaCategoriaAGerarchia.setVisible(true);
	}

	public void openModificaCitazioni(Materiale materiale) {

		List<Citazione> listaCitazione = null;

		CitazioneDAOPostgres citazioneDAO = new CitazioneDAOPostgres(singleton);

		listaCitazione = citazioneDAO.getCitati(materiale.getTitolo());

		ModificaCitazioneGUI modificaCitazioni= new ModificaCitazioneGUI(this, listaCitazione, materiale);
		modificaCitazioni.setVisible(true);

	}

	public void openAggiuntaCitazioneDaModifica(Materiale materiale) {

		MaterialeDAOPostgres materialeDAO = new MaterialeDAOPostgres(singleton);

		List<Materiale> listaMateriali = null;
		listaMateriali = materialeDAO.getMateriali();

		AggiuntaCitazioneDaModificaGUI aggiuntaCitazioneDaModifica = new AggiuntaCitazioneDaModificaGUI(this, listaMateriali, materiale);
		aggiuntaCitazioneDaModifica.setVisible(true);
	}



// METODI DETTAGLI


	public void openDettagliLibro(Materiale materiale) {
		RigaTabellaLibroDAOPostgres libroDAO = new RigaTabellaLibroDAOPostgres(singleton);
		RigaTabellaLibro rigaLibro = libroDAO.getRigaLibro(materiale.getTitolo());

		VisualizzaDettagliLibroGUI visualizzaDettagliLibro = new VisualizzaDettagliLibroGUI(this, rigaLibro);
		visualizzaDettagliLibro.setVisible(true);
	}

	public void openDettagliDataset(Materiale materiale) {
		RigaTabellaDatasetDAOPostgres rigaDatasetDAO = new RigaTabellaDatasetDAOPostgres(singleton);
		RigaTabellaDataset rigaDataset = rigaDatasetDAO.getRigaDataset(materiale.getTitolo());

		VisualizzaDettagliDatasetGUI visualizzaDettagliDataset = new VisualizzaDettagliDatasetGUI(this, rigaDataset);
		visualizzaDettagliDataset.setVisible(true);

	}

	public void openDettagliRisorsaOnline(Materiale materiale) {
		RigaTabellaRisorsaOnlineDAOPostgres rigaRisorsaOnlineDAO = new RigaTabellaRisorsaOnlineDAOPostgres(singleton);
		RigaTabellaRisorsaOnline rigaRisorsaOnline = rigaRisorsaOnlineDAO.getRigaRisorsaOnline(materiale.getTitolo());

		VisualizzaDettagliRisorsaOnlineGUI visualizzaDettagliRisorsaOnline = new VisualizzaDettagliRisorsaOnlineGUI(this, rigaRisorsaOnline);
		visualizzaDettagliRisorsaOnline.setVisible(true);
	}

	public void openDettagliArticoloSuRivista(Materiale materiale) {
		RigaTabellaArticoloSuRivistaDAOPostgres rigaArticoloSuRivistaDAO = new RigaTabellaArticoloSuRivistaDAOPostgres(singleton);
		RigaTabellaArticoloSuRivista rigaArticoloSuRivista = rigaArticoloSuRivistaDAO.getRigaArticoloSuRivista(materiale.getTitolo());

		VisualizzaDettagliArticoloSuRivistaGUI visualizzaDettagliArticoloSuRivista = new VisualizzaDettagliArticoloSuRivistaGUI(this, rigaArticoloSuRivista);
		visualizzaDettagliArticoloSuRivista.setVisible(true);
	}

	public void openDettagliArticoloSuConferenza(Materiale materiale) {
		RigaTabellaArticoloSuConferenzaDAOPostgres rigaArticoloSuConferenzaDAO = new RigaTabellaArticoloSuConferenzaDAOPostgres(singleton);
		RigaTabellaArticoloSuConferenza rigaArticoloSuConferenza = rigaArticoloSuConferenzaDAO.getRigaArticoloSuConferenza(materiale.getTitolo());

		VisualizzaDettagliArticoloSuConferenzaGUI visualizzaDettagliArticoloSuConferenza = new VisualizzaDettagliArticoloSuConferenzaGUI(this, rigaArticoloSuConferenza);
		visualizzaDettagliArticoloSuConferenza.setVisible(true);
	}

	 //GERARCHIA

	public void openAggiuntaGerarchiaNuova(Materiale materiale) {

		AggiuntaGerarchiaNuovaGUI aggiuntaGerarchiaNuova = new AggiuntaGerarchiaNuovaGUI(this, materiale);
		aggiuntaGerarchiaNuova.setVisible(true);
	}

	public void openAggiuntaAGerarchiaEsistente(Materiale materiale) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		List<Categoria> listaCategorie = null;
		listaCategorie = categoriaDAO.getAllCategorie();

		AggiuntaAGerarchiaEsistenteGUI aggiuntaAGerarchiaEsistente = new AggiuntaAGerarchiaEsistenteGUI(this, listaCategorie, materiale);
		aggiuntaAGerarchiaEsistente.setVisible(true);
	}


	public void openAggiuntaCategoriaAGerarchia(Materiale materiale, Categoria supercategoria) {

		AggiuntaCategoriaAGerarchiaGUI aggiuntaCategoriaAGerarchia = new AggiuntaCategoriaAGerarchiaGUI(this, materiale, supercategoria);
		aggiuntaCategoriaAGerarchia.setVisible(true);
	}


	public void openAggiungiCategoriaEsistente(Materiale materiale) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		List<Categoria> listaCategorie = null;
		listaCategorie = categoriaDAO.getAllCategorie();

		AggiuntaCategoriaEsistenteGUI aggiuntaCategoriaEsistente = new AggiuntaCategoriaEsistenteGUI(this, listaCategorie, materiale);
		aggiuntaCategoriaEsistente.setVisible(true);
	}

	public void openAggiuntaCitazione(Materiale materiale) {

		MaterialeDAOPostgres materialeDAO = new MaterialeDAOPostgres(singleton);

		List<Materiale> listaMateriali = null;
		listaMateriali = materialeDAO.getMateriali();

		AggiuntaCitazioneGUI aggiuntaCitazione = new AggiuntaCitazioneGUI(this, listaMateriali, materiale);
		inserimentoLibro.setVisible(false);
		inserimentoDataset.setVisible(false);
		inserimentoRisorsaOnline.setVisible(false);
		inserimentoArticoloSuConferenza.setVisible(false);
		inserimentoArticoloSuRivista.setVisible(false);
		aggiuntaCitazione.setVisible(true);
	}

//MATERIALE

	public void openVisualizzaLibreria() {
		libreria.setVisible(false);

		RigaTabellaMaterialeDAOPostgres rigaMaterialeDAO = new RigaTabellaMaterialeDAOPostgres(singleton);

		List<RigaTabellaMateriale> listaMateriali = null;
		listaMateriali = rigaMaterialeDAO.getRigheMateriali();

		VisualizzaLibreriaGUI visualizzaLibreria = new VisualizzaLibreriaGUI(this, listaMateriali);
		visualizzaLibreria.setVisible(true);
	}

		//LIBRI

	public void openVisualizzaLibri() {
		RigaTabellaLibroDAOPostgres rigaLibroDAO = new RigaTabellaLibroDAOPostgres(singleton);

		List<RigaTabellaLibro> listaLibri = null;
		listaLibri = rigaLibroDAO.getLibri();

		VisualizzaLibriGUI visualizzaLibri = new VisualizzaLibriGUI(this, listaLibri);
		visualizzaLibri.setVisible(true);
	}

	//DATASET


	public void openVisualizzaDataset() {
		RigaTabellaDatasetDAOPostgres rigaDatasetDAO = new RigaTabellaDatasetDAOPostgres(singleton);

		List<RigaTabellaDataset> listaDataset = null;
		listaDataset = rigaDatasetDAO.getDataset();

		VisualizzaDatasetGUI visualizzaDatasetGUI = new VisualizzaDatasetGUI(this, listaDataset);
		visualizzaDatasetGUI.setVisible(true);
		}

   //RISORSA ONLINE

   public void openVisualizzaRisorsaOnline() {
	   RigaTabellaRisorsaOnlineDAOPostgres rigaRisorsaOnlineDAO = new RigaTabellaRisorsaOnlineDAOPostgres(singleton);

	   List<RigaTabellaRisorsaOnline> listaRisorsaOnline = null;
	   listaRisorsaOnline = rigaRisorsaOnlineDAO.getRisorsaOnline();

	   VisualizzaRisorsaOnlineGUI visualizzaRisorsaOnline = new VisualizzaRisorsaOnlineGUI(this, listaRisorsaOnline);
	   visualizzaRisorsaOnline.setVisible(true);
   }


   //ARTICOLO CONFERENZA

   public void openVisualizzaArticoloSuConferenza() {
	   RigaTabellaArticoloSuConferenzaDAOPostgres rigaArticoloDAO = new RigaTabellaArticoloSuConferenzaDAOPostgres(singleton);

	   List<RigaTabellaArticoloSuConferenza> listaArticoloSuConferenza = null;
	   listaArticoloSuConferenza= rigaArticoloDAO.getArticoliSuConferenza();

	   VisualizzaArticoliSuConferenzaGUI visualizzaArticoliSuConferenza = new VisualizzaArticoliSuConferenzaGUI(this, listaArticoloSuConferenza);
	   visualizzaArticoliSuConferenza.setVisible(true);
   }

	//ARTICOLO RIVISTA

	public void openVisualizzaArticoloSuRivista() {
		RigaTabellaArticoloSuRivistaDAOPostgres rigaArticoloDAO = new RigaTabellaArticoloSuRivistaDAOPostgres(singleton);

		List<RigaTabellaArticoloSuRivista> listaArticoloSuRivista = null;
		listaArticoloSuRivista = rigaArticoloDAO.getArticoliSuRivista();

		VisualizzaArticoliSuRivistaGUI visualizzaArticoliSuRivista= new VisualizzaArticoliSuRivistaGUI(this, listaArticoloSuRivista);
		visualizzaArticoliSuRivista.setVisible(true);
	}

	//PAROLE CHIAVE

	public void openParoleChiaveAssociate(String titoloMateriale){

		List<ParolaChiave> listaParole = null;

		ParolaChiaveDAOPostgres parolaDAO = new ParolaChiaveDAOPostgres(singleton);

		listaParole = parolaDAO.getParoleAssociate(titoloMateriale);

		if(listaParole.isEmpty()) {
			openDialog("Non ci sono parole chiave associate");
			return;
		}


		VisualizzaParoleAssociateGUI visualizzaParole = new VisualizzaParoleAssociateGUI(this, listaParole, titoloMateriale);
		visualizzaParole.setVisible(true);
	}

	//CITAZIONI

	public void openCitazioni(String titoloMateriale){

		List<Citazione> listaCitazione = null;

		CitazioneDAOPostgres citazioneDAO = new CitazioneDAOPostgres(singleton);

		listaCitazione = citazioneDAO.getCitazioni(titoloMateriale);

		if(listaCitazione.isEmpty()) {
			openDialog("Non ci sono citazioni associate");
			return;
		}


		VisualizzaCitazioniGUI visualizzaCitazioni= new VisualizzaCitazioniGUI(this, listaCitazione, titoloMateriale);
		visualizzaCitazioni.setVisible(true);
	}

	//CATEGORIE

	public void openCategorieAssociate(String titoloMateriale){

		List<Categoria> listaCategoria = null;

		CategoriaDAOPostgres CategoriaDAO = new CategoriaDAOPostgres(singleton);

		listaCategoria = CategoriaDAO.getCategorie(titoloMateriale);

		if(listaCategoria.isEmpty()) {
			openDialog("Non ci sono categorie associate");
			return;
		}

		VisualizzaCategorieGUI visualizzaCategorie= new VisualizzaCategorieGUI(this, listaCategoria, titoloMateriale);
		visualizzaCategorie.setVisible(true);
	}

   //AUTORE
	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Autore getAutore() {
		return autore;
	}

	//ADMIN
	public Boolean getADMIN() {
		return ADMIN;
	}

	public void logoutADMIN() {
		ADMIN = false;
	}

	//metodi

	public void loginAutore(String username, String passwordString) {
		Autore autoreLoggato;
		if(checkBlank(username)||checkBlank(passwordString))
			return;

		AutoreDAOPostgres autore = new AutoreDAOPostgres(singleton);
		if(autore.checkPresenzaUsername(username)) {
			autoreLoggato = autore.getAutore(username, passwordString);

			if(autoreLoggato != null) {
				openDialog("Login effettuato, benvenuto "+autoreLoggato.getNome()+" "+autoreLoggato.getCognome());
				setAutore(autoreLoggato);
				openPortaleAutori();
			} else {
				openDialog("Combinazione non valida");
			}
		}else {

		openDialog("Username non registrato, effettuare la registrazione");
		return;
		}
	}

	public void loginAmministratore(String username, String passwordString) {

		if(checkBlank(username) || checkBlank(passwordString))
			return;

		if(username.equals("ROOT") && passwordString.equals("ROOT")) {
			openDialog("Login amministratore effettuato");
			ADMIN = true;
			openPortaleAmministratore();
		} else
			openDialog("Combinazione non valida");

	}

	public void registraAutore(String nome, String cognome, String ORCID, String username, String passwordString) {
		if(checkBlank(nome) || checkBlank(cognome) || checkBlank(ORCID) || checkBlank(username) || checkBlank(passwordString))
			return;

		if(checkOrcidLength(ORCID))
			return;

		if(checkNome(nome))
			return;

		if(checkCognome(cognome))
			return;

		if(checkORCIDSoloNumeri(ORCID))
			return;

		AutoreDAOPostgres autore = new AutoreDAOPostgres(singleton);

		if(autore.checkNotUniqueORCID(ORCID)) {
			openDialog("Autore gia registrato");
			return;
		}

		if(autore.checkUniqueUsername(username)) {
			openDialog("Username gia in uso, contattare l'amministrazione se la password a stata smarrita");
			return;
		};

		String risposta = autore.insertAutore(nome, cognome, ORCID, username, passwordString);
		openDialog(risposta);

		openAccessoPortaleAutori();
}

	public boolean inserisciLibro(Libro libro) {
		if(checkBlank(libro.getTitolo()) || checkBlank(libro.getIsbn()) || checkBlank(libro.getEditore()) || !checkLengthISBN(libro.getIsbn()) || !checkISBNSoloNumeri(libro.getIsbn()))
			return false;

		LibroDAOPostgres libroDAO = new LibroDAOPostgres(singleton);

		if(libroDAO.identico(libro)) {
			if(chiediAggiunta(libro))
				return true;
			else
				return false;
		}

		if(!libroDAO.checkUniqueTitolo(libro.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}

		if(!libro.getDoi().isBlank())
			if (!libroDAO.checkUniqueDOI(libro.getDoi())) {
				openDialog("doi gia presente");
				return false;
			}

		if(!libroDAO.checkUniqueISBN(libro.getIsbn())) {
			openDialog("isbn gia presente");
			return false;
		}

		String risposta = libroDAO.insertLibro(libro);

		openDialog(risposta);
		return true;
	}

	public boolean inserisciDataset(Dataset dataset) {
		if(checkBlank(dataset.getTitolo()) || checkBlank(dataset.getUrl()))
			return false;

		DatasetDAOPostgres datasetDAO = new DatasetDAOPostgres(singleton);

		if(datasetDAO.identico(dataset)) {
			if(chiediAggiunta(dataset))
				return true;
			else
				return false;
		}

		if(!datasetDAO.checkUniqueTitolo(dataset.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}

		if(!dataset.getDoi().isBlank())
			if(!datasetDAO.checkUniqueDOI(dataset.getDoi())) {
				openDialog("doi gia presente");
				return false;
			}

		if(!datasetDAO.checkUniqueURL(dataset.getUrl())) {
			openDialog("url gia presente");
			return false;
		}

		String risposta = datasetDAO.insertDataset(dataset);

		openDialog(risposta);
		return true;
	}

	public boolean inserisciRisorsaOnline(RisorsaOnline risorsaOnline) {
		if(checkBlank(risorsaOnline.getTitolo()) || checkBlank(risorsaOnline.getUrl()))
			return false;

		RisorsaOnlineDAOPostgres risorsaOnlineDAO = new RisorsaOnlineDAOPostgres(singleton);

		if(risorsaOnlineDAO.identico(risorsaOnline)) {
			if(chiediAggiunta(risorsaOnline))
				return true;
			else
				return false;
		}

		if(!risorsaOnlineDAO.checkUniqueTitolo(risorsaOnline.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}

		if(!risorsaOnline.getDoi().isBlank())
			if(!risorsaOnlineDAO.checkUniqueDOI(risorsaOnline.getDoi())) {
				openDialog("doi gia presente");
				return false;
			}

		if(!risorsaOnlineDAO.checkUniqueURL(risorsaOnline.getUrl())) {
			openDialog("url gia presente");
			return false;
		}

		String risposta = risorsaOnlineDAO.insertRisorsaOnline(risorsaOnline);

		openDialog(risposta);
		return true;
	}

	public boolean inserisciArticoloSuConferenza(ArticoloSuConferenza articoloSuConferenza) {
		if(checkBlank(articoloSuConferenza.getTitolo()) || checkBlank(articoloSuConferenza.getConferenza().getTitolo()))
			return false;

		if(articoloSuConferenza.getConferenza().getDataConferenza() == null)
		{
			openDialog("Inserire la data della conferenza");
			return false;
		}

		ConferenzaDAOPostgres conferenzaDAO = new ConferenzaDAOPostgres(singleton);
		ArticoloSuConferenzaDAOPostgres articoloSuConferenzaDAO = new ArticoloSuConferenzaDAOPostgres(singleton);

		if(articoloSuConferenzaDAO.identico(articoloSuConferenza)) {
			if(chiediAggiunta(articoloSuConferenza))
				return true;
			else
				return false;
		}

		if(!articoloSuConferenzaDAO.checkUniqueTitolo(articoloSuConferenza.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}

		if(!articoloSuConferenza.getDoi().isBlank())
			if(!articoloSuConferenzaDAO.checkUniqueDOI(articoloSuConferenza.getDoi())) {
				openDialog("doi gia presente");
				return false;
			}

		if(conferenzaDAO.checkUniqueConferenza(articoloSuConferenza.getConferenza())){
			conferenzaDAO.insertConferenza(articoloSuConferenza.getConferenza());

			String risposta = articoloSuConferenzaDAO.insertArticoloSuConferenza(articoloSuConferenza);
			openDialog(risposta);
		} else {
			String risposta = articoloSuConferenzaDAO.insertArticoloSuConferenza(articoloSuConferenza);
			openDialog(risposta);
		}

		return true;
	}

	public boolean inserisciArticoloSuRivista(ArticoloSuRivista articoloSuRivista) {

		if(checkBlank(articoloSuRivista.getTitolo()) || checkBlank(articoloSuRivista.getRivista().getIssn()) || checkBlank(articoloSuRivista.getRivista().getTitolo()))
			return false;

		if(!checkISSNSoloNumeri(articoloSuRivista.getRivista().getIssn()))
			return false;

		if(!checkLengthISSN(articoloSuRivista.getRivista().getIssn()))
			return false;

		RivistaDAOPostgres rivistaDAO = new RivistaDAOPostgres(singleton);
		ArticoloSuRivistaDAOPostgres articoloSuRivistaDAO = new ArticoloSuRivistaDAOPostgres(singleton);

		if(articoloSuRivistaDAO.identico(articoloSuRivista)) {
			if(chiediAggiunta(articoloSuRivista))
				return true;
			else
				return false;
		}

		if(!articoloSuRivistaDAO.checkUniqueTitolo(articoloSuRivista.getTitolo())) {
			openDialog("Il titolo \""+articoloSuRivista.getTitolo()+"\" esiste gia");
			return false;
		}

		if(!articoloSuRivista.getDoi().isBlank())
			if (!articoloSuRivistaDAO.checkUniqueDOI(articoloSuRivista.getDoi())) {
				openDialog("doi gia presente");
				return false;
			}

		if(!articoloSuRivista.getRivista().getIssn().isBlank())
			if(!articoloSuRivistaDAO.checkUniqueISSNRivista(articoloSuRivista.getRivista().getIssn())) {
				openDialog("ISSN gia presente");
				return false;
			}

		if(!articoloSuRivista.getRivista().getTitolo().isBlank())
			if(!articoloSuRivistaDAO.checkUniqueTitoloRivista(articoloSuRivista.getRivista().getTitolo())) {
				openDialog("Il titolo \""+articoloSuRivista.getRivista().getTitolo()+"\" esiste gia");
				return false;
			}


		if(rivistaDAO.checkUniqueISSNRivista(articoloSuRivista.getRivista()) && rivistaDAO.checkUniqueTitoloRivista(articoloSuRivista.getRivista())){
			rivistaDAO.insertRivista(articoloSuRivista.getRivista());
			String risposta = articoloSuRivistaDAO.insertArticoloSuConferenza(articoloSuRivista);
			openDialog(risposta);
		} else {
			String risposta = articoloSuRivistaDAO.insertArticoloSuConferenza(articoloSuRivista);
			openDialog(risposta);
		}

		return true;
	}

	public boolean chiediAggiunta(Materiale materiale) {
		String [] options = new String[2];
		options[0] = "Sa";
		options[1] = "No";
		int result = JOptionPane.showOptionDialog(null,"Il materiale e\' gia\' presente in libreria, vuoi essere aggiunto alla lista degli autori?", "Avviso", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if(result == JOptionPane.YES_OPTION){
			return true;
	   	}else if (result == JOptionPane.NO_OPTION){
	   		return false;
	   	}

		return false;
	}

	public void inserisciPubblicazione(String titolo, String orcid, Date dataPubblicazione) {

		PubblicazioneDAOPostgres pubblicazione = new PubblicazioneDAOPostgres(singleton);

		pubblicazione.insertPubblicazione(titolo, orcid, dataPubblicazione);

	}

	public void cancellaPubblicazione(String titolo, String orcid) {

		PubblicazioneDAOPostgres pubblicazione = new PubblicazioneDAOPostgres(singleton);

		pubblicazione.deletePubblicazione(titolo, orcid);

	}

	public void associaParola(Materiale materiale, ParolaChiave parola) {

		if(checkParola(parola.getParola()))
			return;

		ParolaChiaveDAOPostgres parolaDAO = new ParolaChiaveDAOPostgres(singleton);

		if(parolaDAO.checkUniqueParolaChiave(parola)) {
			parolaDAO.insertParolaChiave(parola);
			ParolaChiaveDelMaterialeDAOPostgres parolaDelMaterialeDAO = new ParolaChiaveDelMaterialeDAOPostgres(singleton);
			ParolaChiaveDelMateriale parolaDelMateriale = new ParolaChiaveDelMateriale(materiale, parola);
			if(parolaDelMaterialeDAO.checkUniqueAssociazione(parolaDelMateriale)) {
				parolaDelMaterialeDAO.insertAssociazione(parolaDelMateriale);
				openDialog("Parola associata");
			}
			else
				openDialog("Parola gia associata");
		} else {

			ParolaChiaveDelMaterialeDAOPostgres parolaDelMaterialeDAO = new ParolaChiaveDelMaterialeDAOPostgres(singleton);
			ParolaChiaveDelMateriale parolaDelMateriale = new ParolaChiaveDelMateriale(materiale, parola);

			if(parolaDelMaterialeDAO.checkUniqueAssociazione(parolaDelMateriale)) {
				parolaDelMaterialeDAO.insertAssociazione(parolaDelMateriale);
				openDialog("Parola associata");
			} else
				openDialog("Parola gia associata");
		}
	}

	public void eliminaParolaChiave(ParolaChiave parola, Materiale materiale) {

		ParolaChiaveDelMateriale parolaDelMateriale = new ParolaChiaveDelMateriale(materiale, parola);
		ParolaChiaveDelMaterialeDAOPostgres parolaDelMaterialeDAO = new ParolaChiaveDelMaterialeDAOPostgres(singleton);

		String risposta = parolaDelMaterialeDAO.eliminaParolaDelMateriale(parolaDelMateriale);
		openDialog(risposta);

	}

	public void associaCategoria(Materiale materiale, Categoria categoria) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		if(categoriaDAO.checkUniqueCategoria(categoria)) {
			categoriaDAO.insertCategoria(categoria);
			CategoriaDiAppartenenzaDAOPostgres categoriaDiAppartenenzaDAO = new CategoriaDiAppartenenzaDAOPostgres(singleton);
			CategoriaDiAppartenenza categoriaDiAppartenenza = new CategoriaDiAppartenenza(categoria, materiale);
			if(categoriaDiAppartenenzaDAO.checkUniqueAssociazione(categoriaDiAppartenenza) && categoriaDiAppartenenzaDAO.checkNotLoop(categoriaDiAppartenenza)) {
				categoriaDiAppartenenzaDAO.insertAssociazione(categoriaDiAppartenenza);
				openDialog("Categoria associata");
			}
			else
				openDialog("Categoria gia associata o ridondante(c'e\' gia\' una sottocategoria o supercategoria associata)");
		} else {
			openDialog("Categoria gia presente nel database, selezionarla da gerarchia esistente");
		}
	}

	public void eliminaCategoria(Materiale materiale, Categoria categoria) {

		CategoriaDiAppartenenzaDAOPostgres categoriaDiAppartenenzaDAO = new CategoriaDiAppartenenzaDAOPostgres(singleton);

		CategoriaDiAppartenenza categoriaDiAppartenenza = new CategoriaDiAppartenenza(categoria, materiale);

		String risposta = categoriaDiAppartenenzaDAO.eliminaCategoriaDiAppartenenza(categoriaDiAppartenenza);
		openDialog(risposta);
	}

	public void aggiungiCitazione(Citazione citazione) {
		CitazioneDAOPostgres citazioneDAO = new CitazioneDAOPostgres(singleton);

		if(citazioneDAO.checkUniqueCitazione(citazione)) {
			if(citazioneDAO.inserisciCitazione(citazione))
				openDialog(citazione.getMaterialeCitante().getTitolo()+" ora cita "+citazione.getMaterialeCitato().getTitolo());
			else
				openDialog("Citazione fallita");
		}else
			openDialog("Materiale gia citato");
	}

	public void eliminaCitazione(Citazione citazione) {
		CitazioneDAOPostgres citazioneDAO = new CitazioneDAOPostgres(singleton);

		String risposta = citazioneDAO.eliminaCitazione(citazione);
		openDialog(risposta);
	}

	public void associaCategoriaEsistente(Materiale materiale, Categoria categoria) {

		CategoriaDiAppartenenzaDAOPostgres categoriaDiAppartenenzaDAO = new CategoriaDiAppartenenzaDAOPostgres(singleton);
		CategoriaDiAppartenenza categoriaDiAppartenenza = new CategoriaDiAppartenenza(categoria, materiale);

		if(categoriaDiAppartenenzaDAO.checkUniqueAssociazione(categoriaDiAppartenenza) && categoriaDiAppartenenzaDAO.checkNotLoop(categoriaDiAppartenenza)) {
			categoriaDiAppartenenzaDAO.insertAssociazione(categoriaDiAppartenenza);
			openDialog("Categoria associata");
		}
		else
			openDialog("Categoria gia associata o ridondante");
	}

	public void associaCategoriaAGerarchia(Materiale materiale, Categoria categoria) {

		CategoriaDAOPostgres categoriaDAO = new CategoriaDAOPostgres(singleton);

		if(categoriaDAO.checkUniqueCategoria(categoria)) {
			categoriaDAO.insertCategoriaInGerarchia(categoria);
			CategoriaDiAppartenenzaDAOPostgres categoriaDiAppartenenzaDAO = new CategoriaDiAppartenenzaDAOPostgres(singleton);
			CategoriaDiAppartenenza categoriaDiAppartenenza = new CategoriaDiAppartenenza(categoria, materiale);
			if(categoriaDiAppartenenzaDAO.checkUniqueAssociazione(categoriaDiAppartenenza) && categoriaDiAppartenenzaDAO.checkNotLoop(categoriaDiAppartenenza)) {
				categoriaDiAppartenenzaDAO.insertAssociazione(categoriaDiAppartenenza);
				openDialog("Categoria associata");
			}
			else
				openDialog("Categoria gia associata o ridondante");
		} else {
			openDialog("Categoria gia presente nel database, selezionarla da gerarchia esistente");
		}
	}

	//ELIMINA

	public void eliminaMateriale(Materiale materiale) {

		MaterialeDAOPostgres materialeDAO = new MaterialeDAOPostgres(singleton);

		materialeDAO.eliminaMateriale(materiale);

		openDialog(materiale.getTitolo()+" eliminato");

		if(ADMIN)
			openModificaMaterialeADMIN();
		else
			openModificaMateriale();
	}



	//MODIFICA

	public void modificaMateriale(Materiale materiale) {
		switch(materiale.getTipo().toString()) {
			case "Libro": RigaTabellaLibroDAOPostgres rigaLibroDAO = new RigaTabellaLibroDAOPostgres(singleton);
						  RigaTabellaLibro rigaLibro = rigaLibroDAO.getRigaLibro(materiale.getTitolo());
						  openModificaLibro(rigaLibro);
						  break;

			case "Dataset": RigaTabellaDatasetDAOPostgres rigaDatasetDAO = new RigaTabellaDatasetDAOPostgres(singleton);
							RigaTabellaDataset rigaDataset = rigaDatasetDAO.getRigaDataset(materiale.getTitolo());
							openModificaDataset(rigaDataset);
							break;

			case "RisorsaOnline": RigaTabellaRisorsaOnlineDAOPostgres rigaRisorsaOnlineDAO = new RigaTabellaRisorsaOnlineDAOPostgres(singleton);
								  RigaTabellaRisorsaOnline rigaRisorsa= rigaRisorsaOnlineDAO.getRigaRisorsaOnline(materiale.getTitolo());
								  openModificaRisorsaOnline(rigaRisorsa);
								  break;

			case "ArticoloSuConferenza": RigaTabellaArticoloSuConferenzaDAOPostgres rigaArticoloConferenzaDAO = new RigaTabellaArticoloSuConferenzaDAOPostgres(singleton);
										 RigaTabellaArticoloSuConferenza rigaArticoloConferenza = rigaArticoloConferenzaDAO.getRigaArticoloSuConferenza(materiale.getTitolo());
										 openModificaArticoloSuConferenza(rigaArticoloConferenza);
										 break;

			case "ArticoloSuRivista": RigaTabellaArticoloSuRivistaDAOPostgres rigaArticoloSuRivistaDAO = new RigaTabellaArticoloSuRivistaDAOPostgres(singleton);
									  RigaTabellaArticoloSuRivista rigaArticoloRivista = rigaArticoloSuRivistaDAO.getRigaArticoloSuRivista(materiale.getTitolo());
			  						  openModificaArticoloSuRivista(rigaArticoloRivista);
			  						  break;
		}
	}

	public void openModificaArticoloSuRivista(RigaTabellaArticoloSuRivista rigaArticoloRivista) {
		ModificaArticoloSuRivistaGUI modificaArticoloSuRivista = new ModificaArticoloSuRivistaGUI(this, rigaArticoloRivista);
		modificaArticoloSuRivista.setVisible(true);
	}


	public void openModificaArticoloSuConferenza(RigaTabellaArticoloSuConferenza rigaArticoloConferenza) {
		ModificaArticoloSuConferenzaGUI modificaArticoloSuConferenza = new ModificaArticoloSuConferenzaGUI(this, rigaArticoloConferenza);
		modificaArticoloSuConferenza.setVisible(true);
	}

	public void openModificaRisorsaOnline(RigaTabellaRisorsaOnline rigaRisorsa) {
		ModificaRisorsaOnlineGUI modificaRisorsaOnline = new ModificaRisorsaOnlineGUI(this, rigaRisorsa);
		modificaRisorsaOnline.setVisible(true);
	}


	public void openModificaDataset(RigaTabellaDataset rigaTabellaDataset) {
		ModificaDatasetGUI modificaDataset = new ModificaDatasetGUI(this, rigaTabellaDataset);
		modificaDataset.setVisible(true);
	}


	public void openModificaLibro(RigaTabellaLibro rigaLibro){
		ModificaLibroGUI modificaLibro = new ModificaLibroGUI(this, rigaLibro);
		modificaLibro.setVisible(true);
	}



	public boolean updateLibro(Libro libroModificato, Libro libroOriginale) {
		if(checkBlank(libroModificato.getTitolo()) || checkBlank(libroModificato.getIsbn()) || checkBlank(libroModificato.getEditore()) || !checkLengthISBN(libroModificato.getIsbn()) || !checkISBNSoloNumeri(libroModificato.getIsbn()))
			return false;

		LibroDAOPostgres libroDAO = new LibroDAOPostgres(singleton);

		if(!libroModificato.getTitolo().equals(libroOriginale.getTitolo()))
			if(!libroDAO.checkUniqueTitolo(libroModificato.getTitolo())) {
				openDialog("titolo gia presente");
				return false;
			}


		if(!java.util.Objects.equals(libroOriginale.getDoi(), libroModificato.getDoi()))
			if(!libroModificato.getDoi().isBlank())
				if (!libroDAO.checkUniqueDOI(libroModificato.getDoi())) {
					openDialog("doi gia presente");
					return false;
				}

		if(!libroModificato.getIsbn().equals(libroOriginale.getIsbn()))
			if(!libroDAO.checkUniqueISBN(libroModificato.getIsbn())) {
				openDialog("isbn gia presente");
				return false;
			}

		String risposta = libroDAO.updateLibro(libroModificato, libroOriginale);

		openDialog(risposta);
		return true;
	}

	public boolean updateDataset(Dataset datasetModificato, Dataset datasetOriginale) {
		if(checkBlank(datasetModificato.getTitolo()) || checkBlank(datasetModificato.getUrl()))
			return false;

		DatasetDAOPostgres datasetDAO = new DatasetDAOPostgres(singleton);

		if(!datasetDAO.checkUniqueTitolo(datasetModificato.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}
		if(!(datasetModificato.getDoi()==null || datasetOriginale.getDoi() == null))
			if(!datasetOriginale.getDoi().equals(datasetModificato.getDoi()))
				if(!datasetModificato.getDoi().isBlank())
					if(!datasetDAO.checkUniqueDOI(datasetModificato.getDoi())) {
						openDialog("doi gia presente");
						return false;
					}

		if(!datasetOriginale.getUrl().equals(datasetModificato.getUrl()))
			if(!datasetDAO.checkUniqueURL(datasetModificato.getUrl())) {
				openDialog("url gia presente");
				return false;
			}

		String risposta = datasetDAO.updateDataset(datasetModificato, datasetOriginale);

		openDialog(risposta);
		return true;
	}

	public boolean updateRisorsaOnline(RisorsaOnline risorsaOnlineModificato, RisorsaOnline risorsaOnlineOriginale) {
		if(checkBlank(risorsaOnlineModificato.getTitolo()) || checkBlank(risorsaOnlineModificato.getUrl()))
			return false;

		RisorsaOnlineDAOPostgres risorsaOnlineDAO = new RisorsaOnlineDAOPostgres(singleton);

		if(!risorsaOnlineDAO.checkUniqueTitolo(risorsaOnlineModificato.getTitolo())) {
			openDialog("titolo gia presente");
			return false;
		}

		if(!(risorsaOnlineModificato.getDoi() == null))
			if(!risorsaOnlineOriginale.getDoi().equals(risorsaOnlineModificato.getDoi()))
				if(!risorsaOnlineModificato.getDoi().isBlank())
					if(!risorsaOnlineDAO.checkUniqueDOI(risorsaOnlineModificato.getDoi())) {
						openDialog("doi gia presente");
						return false;
					}

		if(!risorsaOnlineModificato.getUrl().equals(risorsaOnlineOriginale.getUrl()))
			if(!risorsaOnlineDAO.checkUniqueURL(risorsaOnlineModificato.getUrl())) {
				openDialog("url gia presente");
				return false;
			}

		String risposta = risorsaOnlineDAO.updateRisorsaOnline(risorsaOnlineModificato, risorsaOnlineOriginale);

		openDialog(risposta);
		return true;
	}


	public boolean updateArticoloSuConferenza(ArticoloSuConferenza articoloModificato, ArticoloSuConferenza articoloOriginale) {

		ArticoloSuConferenzaDAOPostgres articoloDAO = new ArticoloSuConferenzaDAOPostgres(singleton);
		ConferenzaDAOPostgres conferenzaDAO = new ConferenzaDAOPostgres(singleton);

		if(!articoloModificato.getTitolo().equals(articoloOriginale.getTitolo()))
			if(!articoloDAO.checkUniqueTitolo(articoloModificato.getTitolo())) {
				openDialog("titolo gia presente");
				return false;
			}

		if(!(articoloModificato.getDoi() == null))
			if(!articoloOriginale.getDoi().equals(articoloModificato.getDoi()))
				if(!articoloModificato.getDoi().isBlank())
					if (!articoloDAO.checkUniqueDOI(articoloModificato.getDoi())) {
						openDialog("doi gia presente");
						return false;
					}

		String risposta2 = conferenzaDAO.updateConferenza(articoloModificato.getConferenza(), articoloOriginale.getConferenza());
		String risposta = articoloDAO.updateArticolo(articoloModificato, articoloOriginale);

		openDialog(risposta);
		openDialog(risposta2);
		return true;
	}

	public boolean updateArticoloSuRivista(ArticoloSuRivista articoloModificato, ArticoloSuRivista articoloOriginale) {
		ArticoloSuRivistaDAOPostgres articoloDAO = new ArticoloSuRivistaDAOPostgres(singleton);
		RivistaDAOPostgres rivistaDAO = new RivistaDAOPostgres(singleton);

		if(!articoloModificato.getTitolo().equals(articoloOriginale.getTitolo()))
			if(!articoloDAO.checkUniqueTitolo(articoloModificato.getTitolo())) {
				openDialog("titolo gia presente");
				return false;
			}

		if(!(articoloModificato.getDoi() == null))
			if(!articoloOriginale.getDoi().equals(articoloModificato.getDoi()))
				if(!articoloModificato.getDoi().isBlank())
					if (!articoloDAO.checkUniqueDOI(articoloModificato.getDoi())) {
						openDialog("doi gia presente");
						return false;
					}

		String risposta2 = rivistaDAO.updateRivista(articoloModificato.getRivista(), articoloOriginale.getRivista());
		String risposta = articoloDAO.updateArticolo(articoloModificato, articoloOriginale);

		openDialog(risposta);
		openDialog(risposta2);
		return true;
	}

	public void updatePubblicazione(String titolo, String orcid, Date date) {
		PubblicazioneDAOPostgres pubblicazioneDAO = new PubblicazioneDAOPostgres(singleton);

		pubblicazioneDAO.updatePubblicazione(titolo, orcid, date);
	}



	public void chiediParolaChiave(Materiale materiale) {
		String [] options = new String[2];
		options[0] = "Sa";
		options[1] = "No";
		int result = JOptionPane.showOptionDialog(null,"Vuoi associare delle parole chiave alla tua pubblicazione?", "Avviso", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if(result == JOptionPane.YES_OPTION){
			openAggiuntaParolaChiave(materiale);
	   	}else if (result == JOptionPane.NO_OPTION){
	   		chiediCategoria(materiale);
	   	}
	}

	public void chiediCategoria(Materiale materiale) {
		String [] options = new String[2];
		options[0] = "Sa";
		options[1] = "No";
		int result = JOptionPane.showOptionDialog(null,"Vuoi associare delle categorie alla tua pubblicazione?", "Avviso", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if(result == JOptionPane.YES_OPTION){
	   		openAggiuntaCategoria(materiale);
	   	}else if (result == JOptionPane.NO_OPTION){
	   		chiediCitazioni(materiale);
	   	}
	}

	public void chiediCitazioni(Materiale materiale) {
		String [] options = new String[2];
		options[0] = "Sa";
		options[1] = "No";
		int result = JOptionPane.showOptionDialog(null, "Il materiale cita altri materiali?", "Avviso", 0, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if(result == JOptionPane.YES_OPTION) {
			openAggiuntaCitazione(materiale);
		}else if (result == JOptionPane.NO_OPTION) {
			returnToPortaleAutori();
		}
	}




	//check vari
	public boolean checkBlank(String stringa) {
		if(stringa.isBlank()) {
			openDialog("Campi vuoti non ammessi");
			return true;
		}
		return false;
	}

	public boolean checkOrcidLength(String stringa) {
		if(stringa.length()!=16) {
			openDialog("L'ORCID deve essere composto da 16 numeri");
			return true;
		}
		return false;
		}

	public boolean checkNome(String stringa) {
		stringa = stringa.toLowerCase();
		char[] charArray = stringa.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= 'a' && ch <= 'z')) {
				openDialog("Il nome non a stato inserito correttamente");
				return true;
			}
		}
		return false;
	}

	public boolean checkParola(String stringa) {
		stringa = stringa.toLowerCase();
		char[] charArray = stringa.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= 'a' && ch <= 'z')) {
				openDialog("La parola chiave pua essere composta solo da lettere");
				return true;
			}
		}
		return false;
	}

	public boolean checkCognome(String stringa) {
		stringa = stringa.toLowerCase();
		char[] charArray = stringa.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= 'a' && ch <= 'z')) {
				openDialog("Il cognome non a stato inserito correttamente");
				return true;
			}
		}
		return false;
	}

	public boolean checkORCIDSoloNumeri(String stringa) {
		stringa = stringa.toLowerCase();
		char[] charArray = stringa.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= '0' && ch <= '9')) {
				openDialog("L'ORCID deve essere composto solo da numeri");
				return true;
			}
		}
		return false;
	}

	public boolean checkLengthISBN(String isbn) {
		if(isbn.length()!=13) {
			openDialog("L'ISBN deve essere composto da 13 numeri");
			return false;
		}
		else
			return true;
	}

	public boolean checkISBNSoloNumeri(String isbn) {
		char[] charArray = isbn.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= '0' && ch <= '9')) {
				openDialog("L'ISBN deve essere composto solo da numeri");
				return false;
			}
		}
		return true;
	}

	public boolean checkISSNSoloNumeri(String issn) {
		char[] charArray = issn.toCharArray();
		for(int i=0; i< charArray.length; i++) {
			char ch = charArray[i];
			if(!(ch>= '0' && ch <= '9')) {
				openDialog("L'ISSN deve essere composto solo da numeri");
				return false;
			}
		}
		return true;

	}

	public boolean checkLengthISSN(String issn) {
		if(issn.length()!=8) {
			openDialog("L'ISSN deve essere composto da 8 numeri");
			return false;
		}
		else
			return true;
	}




















}
