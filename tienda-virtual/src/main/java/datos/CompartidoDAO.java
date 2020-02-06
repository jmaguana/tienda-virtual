package datos;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Compartido;


public class CompartidoDAO {

	@Inject
	private EntityManager em;
	
	public void insertar(Compartido compartido)throws Exception {
		em.persist(compartido);
	}
}
