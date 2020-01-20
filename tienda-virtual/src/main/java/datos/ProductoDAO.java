package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.ProductoStock;

@Stateless
public class ProductoDAO {
	
	@Inject
	private EntityManager em;
	
	public void insertar(ProductoStock producto) {
		em.persist(producto);
	}
	
	public void actualizar(ProductoStock producto) {
		em.merge(producto);
	}
	
	public void borrar(int codigo) {
		em.remove(leer(codigo));
	}
	
	public List<ProductoStock> buscar(String nombre){
		String jpql = "SELECT o FROM ProductoStock o WHERE o.nombre LIKE '%' || :nom || '%' ";
		Query query = em.createQuery(jpql,ProductoStock.class);
		query.setParameter("nom", nombre);
		
		List<ProductoStock> productos = new ArrayList<ProductoStock>();
		try {
			productos = query.getResultList();
			if(productos!=null) {
				System.out.println("hay "+productos.size()+" cosos");
				return productos;
			}else {
				return new ArrayList<ProductoStock>();
			}
		}catch(Exception e) {
			return productos;
		}
	}
	
	public ProductoStock leer(int codigo) {
		ProductoStock producto = em.find(ProductoStock.class, codigo);
		producto.getNombre();
		producto.getDescripcion();
		producto.getImagenes();
		producto.getPrecio();
		return producto;
	}
	
	public List<ProductoStock> listarProductos(){
		String jpql = "SELECT o FROM ProductoStock o";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productos = query.getResultList();
		return productos;
	}
}