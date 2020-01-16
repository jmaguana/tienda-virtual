package vista;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import datos.ProductoDAO;
import modelo.Categoria;
import modelo.Producto;
import modelo.ProductoStock;
import negocio.ControladorWeb;

@ManagedBean
@SessionScoped
public class ProductoBean {
	private ProductoStock producto;
	private List<ProductoStock> lista;
	private int codigo;
	private String nomBuscar ;
	private String nombree;
	
	@Inject
	private ControladorWeb controlador;

	@PostConstruct
	public void init() {
		loadProducto();
	}
	
	public void loadProducto() {
		System.out.println("se llama a loadProducgto");
		nomBuscar = "";
		producto = new ProductoStock();
		producto.setCategoria(new Categoria());
		this.codigo = 0;
		
		if(nombree == null || nombree.length() == 0) {
			System.out.println("Entra a aux null");
		lista = controlador.listarProducto();
		}
		nombree = "";
	}
	
	public String guardar() {
		if(codigo == 0) {
			controlador.insertarProducto(producto);
		}else {
			controlador.actualizarProducto(producto);
		}
		loadProducto();
		return "ProductoCRUD";
	}
	
	public String buscar(String nombre) {
		nombree = nombre;
		this.lista = controlador.buscarProducto(nombre);
		System.out.println("Se está buscando: "+nomBuscar);
		System.out.println("llegan a buscar "+lista.size());
		return null;
	}
	
	public String eliminar(int codigo) {
		controlador.borrarProducto(codigo);
		loadProducto();
		return "ProductoCRUD";
	}
	
	public String getNomBuscar() {
		return nomBuscar;
	}

	public void setNomBuscar(String nomBuscar) {
		this.nomBuscar = nomBuscar;
	}

	public String editar(int codigo) {
		this.codigo = codigo;
		this.producto = controlador.leerProducto(codigo);
		if (producto == null) {
			loadProducto();
			return "ProductoCRUD";
		}
		return "CrearProducto";
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	public List<ProductoStock> getLista() {
		return lista;
	}

	public void setLista(List<ProductoStock> lista) {
		this.lista = lista;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}