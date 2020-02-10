package datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Compra;

/**
 * Clase CompraDAO, mediante el EntityManager permite
 * que se busque una compra que se haya realizado mediante
 * un codigo
 * @author Jhonny Maguana
 * @author Sandra Penaranda
 * @version 2.0
 *
 */

@Stateless
public class CompraDAO {
	
	/**
	 * Atributo de la clase de tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite buscar las compras mediante un codigo
	 * @param codigo referente al codigo de la compra
	 * @return compra es decir, un objeto de la clase compra
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al leer la compra
	 */
	public Compra leer(int codigo) throws Exception{
		Compra compra = em.find(Compra.class, codigo);
		compra.getListaProductos().size();
		return compra;
	}
}