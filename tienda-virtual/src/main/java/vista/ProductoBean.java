package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.Categoria;
import modelo.Producto;
import modelo.ProductoStock;
import negocio.ControladorWeb;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * realizar un CRUD de productos, asi como metodos los cuales hacen uso de 
 * los metodos del controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@ManagedBean
@SessionScoped
public class ProductoBean {
	
	/**
	 * Atributo de tipo ProductoStock
	 */
	private ProductoStock producto;
	
	/**
	 * Atributo de tipo lista de ProductoStock
	 */
	private List<ProductoStock> lista;
	private int codigo;
	private String nomBuscar;
	private String nombree;

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;

	/**
	 * Metodo PostConstruct que llama al metodo listar productos
	 */
	@PostConstruct
	public void init() {
		loadProducto();
	}

	/**
	 * Metodo que lista los productos
	 */
	public void loadProducto() {
		System.out.println("se llama a loadProducgto");
		nomBuscar = "";
		producto = new ProductoStock();
		producto.setCategoria(new Categoria());
		this.codigo = 0;

		if (nombree == null || nombree.length() == 0) {
			System.out.println("Entra a aux null");
			lista = controlador.listarProducto();
		}
		nombree = "";
	}

	/**
	 * Metodo que permite crear y modificar un producto
	 * 
	 * @return un String
	 */
	public String guardar() {
		if (codigo == 0) {
			controlador.insertarProducto(producto);
		} else {
			controlador.actualizarProducto(producto);
		}
		loadProducto();
		return "ProductoCRUD";
	}
	
	/**
	 * Metodo que permite buscar un producto por su nombre
	 * @param nombre hace referencia al nombre del producto
	 * @return un string
	 */
	public String buscar(String nombre) {
		nombree = nombre;
		this.lista = controlador.buscarProducto(nombre);
		System.out.println("Se está buscando: " + nomBuscar);
		System.out.println("llegan a buscar " + lista.size());
		return null;
	}

	/**
	 * Metodo que permite eliminar un producto
	 * @param codigo pertenenciente al producto
	 * @return un string
	 */
	public String eliminar(int codigo) {
		controlador.borrarProducto(codigo);
		loadProducto();
		return "ProductoCRUD";
	}

	/**
	 * 
	 * @return nombre con el cual se buscara un producto
	 */
	public String getNomBuscar() {
		return nomBuscar;
	}

	/**
	 * 
	 * @param nomBuscar pertenenciente al nombre de un producto que se
	 * desee buscar
	 */
	public void setNomBuscar(String nomBuscar) {
		this.nomBuscar = nomBuscar;
	}

	/**
	 * Metodo que permite editar un producto
	 * @param codigo pertenenciente al producto
	 * @return un String
	 */
	public String editar(int codigo) {
		this.codigo = codigo;
		this.producto = controlador.leerProducto(codigo);
		if (producto == null) {
			loadProducto();
			return "ProductoCRUD";
		}
		return "CrearProducto";
	}

	/**
	 * 
	 * @return producto de tipo Producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * 
	 * @param producto de tipo ProductoStock
	 */
	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	/**
	 * 
	 * @return lista de tipo ProductoStock
	 */
	public List<ProductoStock> getLista() {
		return lista;
	}

	/**
	 * 
	 * @param lista de tipo ProductoStock
	 */
	public void setLista(List<ProductoStock> lista) {
		this.lista = lista;
	}

	/**
	 * 
	 * @return codigo de un producto
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param codigo perteneciente a un producto
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}