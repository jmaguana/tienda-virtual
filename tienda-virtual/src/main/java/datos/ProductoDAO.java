package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.CarritoDetalle;
import modelo.ProductoStock;

@Stateless
public class ProductoDAO {
	
	@Inject
	private EntityManager em;
	
	public void insertar(ProductoStock producto) throws Exception{
		em.persist(producto);
	}
	
	public void actualizar(ProductoStock producto) throws Exception{
		em.merge(producto);
	}
	
	public void borrar(int codigo) throws Exception{
		em.remove(leer(codigo));
	}
	
	public List<ProductoStock> buscar(String nombre) throws Exception{
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
	
	public ProductoStock leer(int codigo) throws Exception{
		ProductoStock producto = em.find(ProductoStock.class, codigo);
		return producto;
	}
	
	public ProductoStock leerConVotos(int codigo) throws Exception{
		ProductoStock producto = em.find(ProductoStock.class, codigo);
		producto.getVotos().size();
		return producto;
	}
	
	public List<ProductoStock> listarProductos() throws Exception{
		String jpql = "SELECT o FROM ProductoStock o";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productos = query.getResultList();
		return productos;
	}
	
	public List<ProductoStock> listarProductosVendidos() throws Exception{
		String jpql = "SELECT o FROM ProductoStock o WHERE o.vendido != 0 ORDER BY o.vendido DESC";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productosVendidos = query.getResultList();
		return productosVendidos;
	}
}