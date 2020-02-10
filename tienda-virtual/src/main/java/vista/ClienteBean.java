package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.Cliente;
import negocio.ControladorWeb;

@ManagedBean
@SessionScoped
public class ClienteBean {

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;
	
	private List<Cliente> lista;

	@PostConstruct
	public void init() {
		lista = controlador.listarClientesEstrella();
	}
	
	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
}
