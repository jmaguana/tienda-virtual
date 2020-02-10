package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;

/**
 * En esta clase CategoriaDAO, se implementan los metodos
 * necesarios para realizar un CRUD de la misma
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Stateless
public class CategoriaDAO {
	
	/**
	 * Atributo de la clase tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite insertar una categoria en la BD
	 * 
	 * @param categoria de tipo Categoria
	 */
	public void insertar(Categoria categoria) {
		em.persist(categoria);
	}
	
	/**
	 * Metodo que permite actualizar los campos de una categoria <br>
	 * en la BD
	 * @param categoria de tipo Categoria
	 */
	public void actualizar(Categoria categoria) {
		em.merge(categoria);
	}
	
	/**
	 * Metodo que permite eliminar una categoria mediante un codigo
	 * @param codigo de tipo int que pertenece a la categoria
	 */
	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}
	
	/**
	 * Metodo que permite realizar la busqueda de una categoria
	 * @param codigo de tipo int y pertenece a una categoria
	 * @return c que un objeto de tipo Categoria
	 */
	public Categoria leer(int codigo) {
		Categoria c = em.find(Categoria.class, codigo);
		return c;
	}
	
	/**
	 * Metodo que permite listar todos los campos pertenecientes a una categoria
	 * @return categorias que es una lista de tipo Categoria
	 */
	public List<Categoria> listar(){
		String jpql = "SELECT o FROM Categoria o";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
}