package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		Producto producto = em.find(Producto.class, codigo);
		producto.getCategoria();
		return producto;
	}
	
	public List<Producto> listar(){
		String jpql = "SELECT o FROM Producto o";
		Query query = em.createQuery(jpql, Producto.class);
		List<Producto> productos = query.getResultList();
		
		
		return productos;
	}
}