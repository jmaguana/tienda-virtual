package datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Producto;

@Stateless
public class ProductoDAO {
	
	@Inject
	private EntityManager em;
	
	public void insertar(Producto producto) {
		em.persist(producto);
	}
	
	public void actualizar(Producto producto) {
		em.merge(producto);
	}
	
	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}
	
	public Producto leer(int codigo) {
		em.find(Producto.class, codigo);
		return null;
	}
}