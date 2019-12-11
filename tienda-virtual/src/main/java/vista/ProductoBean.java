package vista;

import java.io.IOException;
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
	
	@Inject
	private ProductoDAO pDao;

	@PostConstruct
	public void init() {
		
		loadProducto();
	}
	
	public void loadProducto() {
		producto = new Producto();
		producto.setCategoria(new Categoria());
		this.codigo = 0;
		lista = pDao.listar();
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
	
	public String eliminar(int codigo) {
		pDao.borrar(codigo);
		loadProducto();
		return "ProductoCRUD";
	}
	
	public String editar(int codigo) {
		System.out.println("El codigo a Editar de producto es: "+codigo);
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
