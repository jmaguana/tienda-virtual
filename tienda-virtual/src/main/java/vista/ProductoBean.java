package vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import datos.ProductoDAO;
import modelo.Producto;

@ManagedBean
public class ProductoBean {

	private Producto producto;
	private List<Producto> lista;
	private int codigo;
	
	@Inject
	private ProductoDAO pDao;

	public void init() {
		producto = new Producto();
		loadProducto();
	}
	
	public void loadProducto() {
		lista = pDao.listar();
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
