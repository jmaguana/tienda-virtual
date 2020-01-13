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
		Usuario usuario = em.find(Usuario.class, cedula);
		return usuario;
	}
	
	public List<Usuario> listar(){
		String jpql = "SELECT o FROM Usuario o"; 
		Query query = em.createQuery(jpql,Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	public boolean login(String email, String pass) {
		String jpql = "SELECT o FROM Usuario o WHERE o.email = :id AND o.pass = :pass";
		Query query = em.createQuery(jpql,Usuario.class);
		query.setParameter("id", email);
		query.setParameter("pass", pass);
		try {
			Usuario u = (Usuario) query.getSingleResult();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
}
