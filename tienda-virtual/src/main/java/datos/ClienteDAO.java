package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Cliente;
import modelo.ProductoStock;
import modelo.Usuario;

@Stateless
public class ClienteDAO {

	@Inject
	private EntityManager em;
	
	public void insertar(Cliente cliente) throws Exception {
		em.persist(cliente);
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
}