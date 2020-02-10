package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.Cliente;
import negocio.ControladorWeb;
/**
 * Clase ClienteBean, tiene los metodos necesarios para 
 * mostrar en el aplicativo web para el usuario
 * @author Jhonny Maguana
 * @author Sandra Penaranda
 * @version 2.0
 */
@ManagedBean
@SessionScoped
public class ClienteBean {

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;
	
	/**
	 * Atributo lista de tipo List de cliente
	 */
	private List<Cliente> lista;

	@PostConstruct
	public void init() {
		lista = controlador.listarClientesEstrella();
	}
	
	/**
	 * Metodo get del atributo lista
	 * @return lista
	 */
	public List<Cliente> getLista() {
		return lista;
	}

	/**
	 * Metodo set del atributo lista
	 * @param lista de tipo List de Cliente
	 */
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
}