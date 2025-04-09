package project.DAO;

import project.MODEL.Libro;

public interface LibroDAO {

	public String insertLibro(Libro libro);

	public void deleteLibro(Libro libro);

	public boolean checkUniqueTitolo(String titolo);

	public boolean checkUniqueDOI(String doi);

	public boolean checkUniqueISBN(String isbn);

	public Libro getLibro(String titolo);

	public String updateLibro(Libro libroModificato, Libro libroOriginale);

	public boolean identico(Libro libro);
}
