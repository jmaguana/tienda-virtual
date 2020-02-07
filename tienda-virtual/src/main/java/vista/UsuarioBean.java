package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.Usuario;
import negocio.ControladorWeb;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * realizar un CRUD de usuarios, asi como metodos los cuales hacen uso de los
 * metodos del controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@ManagedBean
@SessionScoped
public class UsuarioBean {

	/**
	 * Atributo de tipo Usuario
	 */
	private Usuario usuario;

	/**
	 * Atributo de tipo lista de tipo Usuario
	 */
	private List<Usuario> lista;

	/**
	 * Atributo de tipo String
	 */
	private String cedula;

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;

	/**
	 * Metodo que permite listar los usuarios al iniciar la aplicacion
	 */
	@PostConstruct
	public void init() {
		loadUsuarios();
	}

	/**
	 * Metodo que lista los usuarios
	 */
	public void loadUsuarios() {
		usuario = new Usuario();
		this.cedula = null;
		lista = controlador.listarUsuarios();
	}

	/**
	 * 
	 * @return usuario de tipo Usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * 
	 * @param usuario de tipo Usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo que permite editar un usuario
	 * @param cedula perteneciente al usuario
	 * @return un String
	 */
	public String editar(String cedula) {
		this.cedula = cedula;
		usuario = controlador.leerUsuario(cedula);
		if (usuario == null) {
			loadUsuarios();
			return "UsuarioCRUD";
		}
		return "CrearUsuario";
	}

	/**
	 * Metodo que permite eliminar a un usuario
	 * @param cedula perteneciente al usuario
	 * @return un String
	 */
	public String eliminar(String cedula) {
		try {

			controlador.borrarUsuario(cedula);
		} catch (Exception e) {

		}
		loadUsuarios();
		return "UsuariosCRUD";
	}
	
	/**
	 * Metodo que permite crear y modificar a un usuario
	 * @return un String
	 */
	public String guardar() {
		if (cedula == null) {
			controlador.insertarUsuario(usuario);
		} else {
			controlador.actualizarUsuario(usuario);
		}
		loadUsuarios();
		return "UsuarioCRUD";
	}

	/**
	 * 
	 * @return lista de tipo Usuario
	 */
	public List<Usuario> getLista() {
		return lista;
	}

	/**
	 * 
	 * @param lista de tipo Usuario
	 */
	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	/**
	 * 
	 * @return cedula perteneciente al Usuario siendo su codigo
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Permite crear un usuario
	 * @return
	 */
	public String nuevo() {
		usuario = new Usuario();
		return "CrearUsuario";
	}

	/**
	 * 
	 * @param cedula de tipo String
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}