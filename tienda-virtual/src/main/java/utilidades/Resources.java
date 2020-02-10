package utilidades;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase Resources encargada de permitir que las clases del DAO 
 * puedan hacer uso del EntityManager
 * @author Jhonny Maguana
 * @author Sandra Penaranda
 * @version 2.0
 *
 */
public class Resources {
	
	/**
	 * Atributo em de tipo EntityManager
	 */
	@Produces
	@PersistenceContext
	private EntityManager em;
	
}
