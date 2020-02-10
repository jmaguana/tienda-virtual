package datos;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Compartido;

/**
 * Clase CompartidoDAO la cual permite mediante el uso del EntityManager
 * insertar datos en la base de datos
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
public class CompartidoDAO {

	/**
	 * Atributo de la clase tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite insertar una categoria en la BD
	 * 
	 * @param compartido de tipo Compartido
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al insertar un objeto de tipo Compartido
	 */
	public void insertar(Compartido compartido)throws Exception {
		em.persist(compartido);
	}
}