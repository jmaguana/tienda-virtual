package vista;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.ProductoVendido;
import negocio.ControladorWeb;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * realizar reportes, asi como metodos los cuales hacen uso de los
 * metodos del controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteBean {
	
	/**
	 * Atributos de la clase
	 */
	private ProductoVendido productoVendido;
	private List<ProductoVendido> listaVendidos;
	private int codigo;
	private String descripcion ;
	private String imagen;
	private String nombre;
	private int cantidad;
	
	@Inject
	private ControladorWeb controlador;

}
