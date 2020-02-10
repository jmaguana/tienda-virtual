package datos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Usuario;

/**
 * Clase UsuarioDAO en esta clase se realiza un CRUD de un usuario
 * mediante el uso de el atributo em del EntityManager
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
	 * @throws Exception se genera una excepcion si existe problemas 
	 * al insertar un objeto de tipo Usuario
	 */
	public void insertar(Usuario usuario) throws Exception {
		em.persist(usuario);
	}
	
	/**
	 * Metodo que permite actualizar los campos de un usuario 
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
	 * Metodo que permite listar a todos los usuarios, registrados en 
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
	 * Metodo que permite comprobar las credenciales de un usuario para 
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