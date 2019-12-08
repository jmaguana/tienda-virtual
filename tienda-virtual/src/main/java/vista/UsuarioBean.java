package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import datos.UsuarioDAO;
import modelo.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario;
	
	@Inject
	private UsuarioDAO uDao;
	
	private List<Usuario> lista;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String guardar() {
		uDao.insertar(usuario);
		return null;
	}
	
}
