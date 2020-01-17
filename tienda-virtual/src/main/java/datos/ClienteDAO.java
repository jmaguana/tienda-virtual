package datos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Cliente;
import modelo.CarritoDetalle;

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
}
