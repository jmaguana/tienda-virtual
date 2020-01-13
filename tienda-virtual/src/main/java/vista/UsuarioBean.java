package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import datos.UsuarioDAO;
import modelo.Usuario;
import negocio.ControladorWeb;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	
	private Usuario usuario;
	private List<Usuario> lista;
	private String cedula;
	
	@Inject
	private ControladorWeb controlador;

	@PostConstruct
	public void init() {
		loadUsuarios();
	}

	public void loadUsuarios() {
		usuario = new Usuario();
		this.cedula = null;
		lista = controlador.listarUsuarios();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String editar(String cedula) {
		this.cedula = cedula;
		usuario = controlador.leerUsuario(cedula);
		if(usuario == null) {
			loadUsuarios();
			return "UsuarioCRUD";
		}
		return "CrearUsuario";
	}
	
	public String eliminar(String cedula) {
		try {
			
			controlador.borrarUsuario(cedula);
		}catch(Exception e) {
			
		}
		loadUsuarios();
		return "UsuariosCRUD";
	}
	
	public String guardar() {
		if(cedula==null) {
			controlador.insertarUsuario(usuario);
		}else {
			controlador.actualizarUsuario(usuario);
		}
		loadUsuarios();
		return "UsuarioCRUD";
	}
	
	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getCedula() {
		return cedula;
	}

	public String nuevo() {
		usuario = new Usuario();
		return "CrearUsuario";
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
}