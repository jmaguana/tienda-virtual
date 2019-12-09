package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public List<Usuario> listar(){
		String jpql = "SELECT o FROM Usuario o";
		Query query = em.createQuery(jpql,Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
}
