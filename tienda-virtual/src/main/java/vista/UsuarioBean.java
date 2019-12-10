package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import datos.UsuarioDAO;
import modelo.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario;
	private List<Usuario> lista;
	private String cedula;
	
	@Inject
	private UsuarioDAO uDao;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		loadUsuarios();
	}

	public void loadUsuarios() {
		lista = uDao.listar();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String editar(String cedula) {
		usuario = uDao.leer(cedula);
		return "CrearUsuario";
	}
	
	public String guardar() {
		if(cedula==null) {
			uDao.insertar(usuario);
		}else {
			uDao.actualizar(usuario);
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

