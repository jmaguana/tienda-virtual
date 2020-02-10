package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.ProductoStock;
import negocio.ControladorWeb;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * mostrar la pagina principal al usuario final, asi como metodos los cuales
 * hacen uso de los metodos del controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@ManagedBean
@SessionScoped
public class InicioBean {

	/**
	 * Atributo de la clase de tipo ProductoStock
	 */
	private List<ProductoStock> lista;
	
	private String valor;

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;

	/**
	 * 
	 * @return una lista de tipo ProductoStock
	 */
	public List<ProductoStock> getLista() {
		return lista;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * 
	 * @param lista pertenenciente a la clase ProductoStock
	 */	
	public void setLista(List<ProductoStock> lista) {
		this.lista = lista;
	}

	/**
	 * Metodo que muestra los productos mas vendidos
	 */
	@PostConstruct
	public void init() {
		valor = "Productos mas votados";
		lista = controlador.listarProductosVendidos();
		//lista = controlador.listarProductosMasVotados();
	}
	
	
	public String accion() {
		if(valor.equals("Productos mas votados")) {
			lista = controlador.listarProductosVendidos();
			valor = "Productos mas vendidos";
			System.out.println(valor);
		}else {
			
			lista = controlador.listarProductosMasVotados();
			valor = "Productos mas votados";
			System.out.println(valor);
		}
		return null;
	}
	
}