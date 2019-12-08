package datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
		em.find(Categoria.class, codigo);
		return null;
	}
}
