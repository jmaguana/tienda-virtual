package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.CarritoDetalle;
import modelo.Cliente;
import modelo.Compartido;
import modelo.Compra;
import modelo.ProductoStock;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y metodos que son necesarios en esta clase
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Stateless
public class ClienteDAO {

	/**
	 * Atributo de la clase tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite insertar un cliente en la BD
	 * 
	 * @param cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al insertar un objeto de tipo Cliente
	 */
	public void insertar(Cliente cliente) throws Exception {
		em.persist(cliente);
	}
	
	/**
	 * Metodo que permite actualizar los campos de un cliente <br>
	 * en la BD
	 * @param cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al actualizar un objeto de tipo Cliente
	 */
	public void actualizar(Cliente cliente) throws Exception {
		em.merge(cliente);
	}
	
	/**
	 * Metodo que permite realizar la busqueda de un cliente
	 * @param codigo de tipo int y pertenece a un cliente
	 * @return cliente que un objeto de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al buscar un objeto de tipo Cliente
	 */
	public Cliente leer(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		cliente.getCarrito().size();
		cliente.getListaCompras().size();
		if(cliente.getCarrito() == null) {
			cliente.setCarrito(new ArrayList<CarritoDetalle>());
		}
		if(cliente.getListaCompras() == null) {
			cliente.setListaCompras(new ArrayList<Compra>());
		}
		return cliente;
	}
	
	/**
	 * Metodo que permite buscar un cliente
	 * @param codigo del cliente
	 * @return cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar la busqueda
	 */
	public Cliente leerVacio(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		return cliente;
	}
	
	/**
	 * Metodo que permite buscar un Cliente que haya compartido un producto
	 * @param codigo del cliente
	 * @return cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar la busqueda
	 */
	public Cliente leerConCompartido(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		cliente.getListaEnviado().size();
		cliente.getListaRecibido().size();
		if(cliente.getListaEnviado() == null) {
			cliente.setListaEnviado(new ArrayList<Compartido>());
		}
		if(cliente.getListaRecibido() == null) {
			cliente.setListaRecibido(new ArrayList<Compartido>());
		}
		return cliente;
	}
	
	/**
	 * Metodo que permite buscar un cliente que haya realizado algun voto <br>
	 * para un producto
	 * @param codigo del cliente
	 * @return cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar la busqueda
	 */
	public Cliente leerConVotos(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		cliente.getListaVotos().size();
		if(cliente.getListaVotos() == null) {
			cliente.setListaVotos(new ArrayList<ProductoStock>());
		}
		return cliente;
	}
	
	/**
	 * Metodo que permite listar todos los campos pertenecientes a <br>
	 * un cliente
	 * @return clientes que es una lista de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar la consulta
	 */
	public List<Cliente> listar() throws Exception {
		String jpql = "SELECT o FROM Cliente o";
		Query query = em.createQuery(jpql,Cliente.class);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	
	/**
	 * Metodo que permite comprobar las credenciales de un cliente para <br>
	 * realizar un login
	 * @param correo que pertenece al cliente
	 * @param contrasenia que pertenece al cliente
	 * @return cliente, objeto de tipo Cliente
	 */
	public Cliente login(String correo, String contrasenia){
		String jpql = "SELECT o FROM Cliente o WHERE o.correo = :correo AND o.contrasenia = :contrasenia";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("correo", correo);
		query.setParameter("contrasenia", contrasenia);
		try {
			Cliente cliente = (Cliente) query.getSingleResult();
			return cliente;
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Metodo que permite listar los productos que se hayan aniadido <br>
	 * al carrito de compras
	 * @param id, codigo del cliente
	 * @return una lista de los productos que se desean comprar
	 */
	public List<CarritoDetalle> listarProductosCarrito(int id){
		Cliente cliente = new Cliente();
		String jpql = "SELECT c FROM Cliente c WHERE c.id = :id";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("id",id);
		cliente = (Cliente) query.getSingleResult();
		cliente.getCarrito().size();
		System.out.println("Cliente carrito "+cliente.getCarrito());
		return cliente.getCarrito();
	}
	
	/**
	 * Metodo que permite listar las compras que un cliente haya realizado
	 * @param id_cliente codigo perteneciente al cliente
	 * @return una lista perteneciente al cliente de las compras que realizo
	 */
	public List<Compra> listarCompras(int id_cliente){
		Cliente cliente = new Cliente();
		String jpql = "SELECT c FROM Cliente c WHERE c.id = :id_cliente";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("id_cliente",id_cliente);
		cliente = (Cliente) query.getSingleResult();
		cliente.getListaCompras().size();
		return cliente.getListaCompras();
	}
	
	public List<Cliente> listarClientesEstrella() throws Exception{
		String jpql = "SELECT o FROM Cliente o WHERE o.compras != 0 ORDER BY o.compras DESC";
		Query query = em.createQuery(jpql, Cliente.class);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
}