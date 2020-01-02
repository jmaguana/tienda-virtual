package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;
import modelo.Producto;
import modelo.Usuario;

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
	
	public List<Producto> buscar(String nombre){
		String jpql = "SELECT o FROM Producto o WHERE o.nombre LIKE '%' || :nom || '%' ";
		Query query = em.createQuery(jpql,Producto.class);
		query.setParameter("nom", nombre);
		
		List<Producto> productos = new ArrayList<Producto>();
		try {
			productos = query.getResultList();
			if(productos!=null) {
				System.out.println("hay "+productos.size()+" cosos");
				return productos;
			}else {
				return new ArrayList<Producto>();
			}
		}catch(Exception e) {
			return productos;
		}
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
		
		/*
		for	(Producto p:productos) {
			p.setCategoria(new Categoria());
		}*/
		return productos;
	}
}