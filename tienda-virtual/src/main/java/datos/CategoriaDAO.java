package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;

@Stateless
public class CategoriaDAO {
	@Inject
	private EntityManager em;
	
	public void insertar(Categoria categoria) {
		em.persist(categoria);
	}
	
	public void actualizar(Categoria categoria) {
		em.merge(categoria);
	}
	
	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}
	
	public Categoria leer(int codigo) {
		Categoria c = em.find(Categoria.class, codigo);
		return c;
	}
	
	public List<Categoria> listar(){
		String jpql = "SELECT o FROM Categoria o";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
}
