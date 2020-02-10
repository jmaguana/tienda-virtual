package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.ProductoStock;

/**
 * Clase ProductoDAO, mediante un atributo de tipo EntityManager
 * se crean metodos los cuales alteran la base de datos
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Stateless
public class ProductoDAO {
	
	/**
	 * Atributo de la clase tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite insertar un producto en la BD
	 * 
	 * @param producto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas
	 * al insertar un objeto de tipo ProductoStock
	 */
	public void insertar(ProductoStock producto) throws Exception{
		em.persist(producto);
	}
	
	/**
	 * Metodo que permite actualizar los campos de un producto 
	 * en la BD
	 * @param producto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al actualizar un objeto de tipo ProductoStock
	 */
	public void actualizar(ProductoStock producto) throws Exception{
		em.merge(producto);
	}
	
	/**
	 * Metodo que permite eliminar un producto mediante un codigo
	 * @param codigo de tipo int que pertenece al producto
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al eliminar un objeto de tipo ProductoStock
	 */
	public void borrar(int codigo) throws Exception{
		em.remove(leer(codigo));
	}
	
	/**
	 * Metodo que permite buscar los productos sin necesidad de escribir el nombre 
	 * completo
	 * @param nombre, nombre perteneciente al producto
	 * @return productos que es una lista de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al buscar un objeto de tipo ProductoStock
	 */
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
	
	/**
	 * Metodo que permite realizar la busqueda de un producto
	 * @param codigo de tipo int y pertenece a un producto
	 * @return producto que un objeto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al buscar un objeto de tipo ProductoStock
	 */
	public ProductoStock leer(int codigo) throws Exception{
		ProductoStock producto = em.find(ProductoStock.class, codigo);
		return producto;
	}
	
	/**
	 * Metodo que permite realizar la busqueda de un producto, el cual 
	 * tenga votos registrados
	 * @param codigo de tipo int y pertenece a un producto
	 * @return producto que un objeto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al buscar un objeto de tipo ProductoStock
	 */
	public ProductoStock leerConVotos(int codigo) throws Exception{
		ProductoStock producto = em.find(ProductoStock.class, codigo);
		producto.getVotos().size();
		return producto;
	}
	
	/**
	 * Metodo que permite listar los productos 
	 * @return productos que es una lista de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al listar los productos
	 */
	public List<ProductoStock> listarProductos() throws Exception{
		String jpql = "SELECT o FROM ProductoStock o";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productos = query.getResultList();
		return productos;
	}
	
	/**
	 * Metodo que permite listar los productos que ya se han vendido
	 * @return productosVendidos que es una lista de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al listar los productos mas vendidos
	 */
	public List<ProductoStock> listarProductosVendidos() throws Exception{
		String jpql = "SELECT o FROM ProductoStock o WHERE o.vendido != 0 ORDER BY o.vendido DESC";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productosVendidos = query.getResultList();
		for(ProductoStock p : productosVendidos) {
			p.getVotos().size();
		}
		return productosVendidos;
	}
	
	/**
	 * Metodo que permite listar los productos que tenga mas likes o votos
	 * @return productos es decir un objeto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al listar los productos que mas likes tienen
	 */
	public List<ProductoStock> listarProductosMasVotados() throws Exception{
		//"SELECT p FROM ProductoStock p INNER JOIN (SELECT o, COUNT(*) suma FROM votos o GROUP BY  listaVotos_codigo ORDER BY suma) tabl ON tabl.o.codigo = p.codigo"
		//"SELECT * FROM ProductoStock p INNER JOIN (SELECT listaVotos_codigo, COUNT(*) suma FROM votos GROUP BY listaVotos_codigo ORDER BY suma DESC) o ON o.listaVotos_codigo = p.codigo"
		//SELECT p FROM ProductoStock p INNER JOIN (SELECT listaVotos_codigo, COUNT(*) suma FROM votos v GROUP BY listaVotos_codigo ORDER BY suma DESC) o ON o.listaVotos_codigo = p.codigo
		String jpql = "SELECT p FROM ProductoStock p WHERE size(p.votos) > 0 ORDER BY size(p.votos) DESC";
		Query query = em.createQuery(jpql, ProductoStock.class);
		List<ProductoStock> productos = query.getResultList();
		for(ProductoStock p : productos) {
			p.getVotos().size();
		}
		return productos;
	}
}