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

@Stateless
public class ClienteDAO {

	@Inject
	private EntityManager em;
	
	public void insertar(Cliente cliente) throws Exception {
		em.persist(cliente);
	}
	
	public void actualizar(Cliente cliente) throws Exception {
		em.merge(cliente);
	}
	
	public Cliente leer(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		cliente.getNombre();
		cliente.getApellidos();
		cliente.getCorreo();
		//cliente.getFechaNacimiento();
		//cliente.getImagen();
		cliente.getTelefono();
		cliente.getCarrito().size();
		cliente.getListaCompras().size();
		return cliente;
	}
	
	public Cliente leerVacio(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		return cliente;
	}
	
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
	
	public Cliente leerConVotos(int codigo) throws Exception{
		Cliente cliente = em.find(Cliente.class, codigo);
		cliente.getListaVotos().size();
		if(cliente.getListaVotos() == null) {
			cliente.setListaVotos(new ArrayList<ProductoStock>());
		}
		return cliente;
	}
	
	public List<Cliente> listar() throws Exception {
		String jpql = "SELECT o FROM Cliente o";
		Query query = em.createQuery(jpql,Cliente.class);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	
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
	
	public List<Compra> listarCompras(int id_cliente){
		Cliente cliente = new Cliente();
		String jpql = "SELECT c FROM Cliente c WHERE c.id = :id_cliente";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("id_cliente",id_cliente);
		cliente = (Cliente) query.getSingleResult();
		cliente.getListaCompras().size();
		return cliente.getListaCompras();
	}
}