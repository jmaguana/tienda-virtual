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

@ManagedBean
@SessionScoped
public class ProductoBean {
	private Producto producto;
	private List<Producto> lista;
	private int codigo;
	private String nomBuscar ;
	private String nombree;
	
	@Inject
	private ProductoDAO pDao;

	@PostConstruct
	public void init() {
		loadProducto();
	}
	
	public void loadProducto() {
		System.out.println("se llama a loadProducgto");
		nomBuscar = "";
		producto = new Producto();
		producto.setCategoria(new Categoria());
		this.codigo = 0;
		
		if(nombree == null || nombree.length() == 0) {
			System.out.println("Entra a aux null");
		lista = pDao.listar();
		}
		nombree = "";
	}
	
	public String guardar() {
		if(codigo == 0) {
			pDao.insertar(producto);
		}else {
			pDao.actualizar(producto);
		}
		loadProducto();
		return "ProductoCRUD";
	}
	
	public String buscar(String nombre) {
		nombree = nombre;
		this.lista = pDao.buscar(nomBuscar);
		System.out.println("Se está buscando: "+nomBuscar);
		System.out.println("llegan a buscar "+lista.size());
		return null;
	}
	
	public String eliminar(int codigo) {
		pDao.borrar(codigo);
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
		this.producto = pDao.leer(codigo);
		if (producto == null) {
			loadProducto();
			return "ProductoCRUD";
		}
		return "CrearProducto";
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getLista() {
		return lista;
	}

	public void setLista(List<Producto> lista) {
		this.lista = lista;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
