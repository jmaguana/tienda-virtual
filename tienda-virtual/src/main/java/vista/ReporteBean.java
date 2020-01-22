package vista;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.ProductoVendido;
import negocio.ControladorWeb;


@ManagedBean
@SessionScoped
public class ReporteBean {
	
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
