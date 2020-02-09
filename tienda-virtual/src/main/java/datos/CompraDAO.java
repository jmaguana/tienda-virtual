package datos;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Compra;


@Stateless
public class CompraDAO {
	@Inject
	private EntityManager em;
	
	public Compra leer(int codigo) throws Exception{
		Compra compra = em.find(Compra.class, codigo);
		compra.getListaProductos().size();
		return compra;
	}
	
}
