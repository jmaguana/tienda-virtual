package datos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private EntityManager em;
	
	public void insertar(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void actualizar(Usuario usuario) {
		em.merge(usuario);
	}
	
	public void borrar(String cedula) {
		em.remove(leer(cedula));
	}
	
	public Usuario leer(String cedula) {
		em.find(Usuario.class, cedula);
		return null;
	}
}
