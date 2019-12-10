package vista;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import datos.ProductoDAO;
import modelo.Categoria;
import modelo.Producto;

@ManagedBean
public class ProductoBean {
	private Producto producto;
	private List<Producto> lista;
	private int codigo;
	
	@Inject
	private ProductoDAO pDao;

	@PostConstruct
	public void init() {
		producto = new Producto();
		producto.setCategoria(new Categoria());
		loadProducto();
	}
	
	public void loadProducto() {
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
		this.codigo = codigo;
		this.producto = pDao.leer(codigo);
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
