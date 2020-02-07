package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y metodos que son necesarios en esta clase
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Stateless
public class UsuarioDAO {

	/**
	 * Atributo de la clase tipo EntityManager
	 */
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo que permite insertar un usuario en la BD
	 * 
	 * @param usuario de tipo Usuario
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al insertar un objeto de tipo Usuario
	 */
	public void insertar(Usuario usuario) throws Exception {
		em.persist(usuario);
	}
	
	/**
	 * Metodo que permite actualizar los campos de un usuario <br>
	 * en la BD
	 * @param usuario de tipo Usuario
	 */
	public void actualizar(Usuario usuario) {
		em.merge(usuario);
	}
	
	/**
	 * Metodo que permite eliminar un usuario mediante un codigo
	 * @param codigo de tipo int que pertenece al usuario
	 */
	public void borrar(String cedula) {
		em.remove(leer(cedula));
	}
	
	/**
	 * Metodo que permite realizar la busqueda de un usuario
	 * @param codigo de tipo int y pertenece a un Usuario
	 * @return usuario que un objeto de tipo Usuario
	 */
	public Usuario leer(String cedula) {
		Usuario usuario = em.find(Usuario.class, cedula);
		return usuario;
	}
	
	/**
	 * Metodo que permite listar a todos los usuarios, registrados en <br>
	 * la BD
	 * @return usuarios que es una lista de tipo Usuario
	 */
	public List<Usuario> listar(){
		String jpql = "SELECT o FROM Usuario o"; 
		Query query = em.createQuery(jpql,Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		return usuarios;
	}
	
	/**
	 * Metodo que permite comprobar las credenciales de un usuario para <br>
	 * realizar un login
	 * @param correo que pertenece al cliente
	 * @param contrasenia que pertenece al cliente
	 * @return cliente, objeto de tipo Usuario
	 */
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