package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.ClienteDAO;
import datos.ProductoDAO;
import modelo.CarritoDetalle;
import modelo.Cliente;
import modelo.Compra;
import modelo.ProductoStock;
import modelo.ProductoVendido;

@Stateless
public class ControladorMovil {

	@Inject
	private ClienteDAO clienteDao;
	
	@Inject
	private ProductoDAO productoDao;

	
	public Cliente login(String correo, String contrasenia) {
		return clienteDao.login(correo, contrasenia);
	}
	
	
	public List<Cliente> listarClientes() throws Exception{
		return clienteDao.listar();
	}
	
	public List<ProductoStock> listarProductos() throws Exception{
		return productoDao.listarProductos();
	}
	
	public ProductoStock buscarProducto(int codigo) {
		try {
			return productoDao.leer(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}	
	
	public List<CarritoDetalle> listarProductoCarrito(int codigo) {
		return clienteDao.listarProductosCarrito(codigo);
	}
	
	public List<ProductoStock> listarProductosVendidos(){
		try {
			return productoDao.listarProductosVendidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<Compra> listarCompras(int id_cliente){
		try {
			return clienteDao.listarCompras(id_cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void insertarCarrito(int idCliente, int idProducto, int cantidad) throws Exception{
			ProductoStock p = productoDao.leer(idProducto);
			Cliente c = clienteDao.leer(idCliente);
			CarritoDetalle cd = new CarritoDetalle();
			cd.setProducto(p);
			cd.setCantidad(cantidad);
			c.getCarrito().add(cd);
			clienteDao.actualizar(c);
	}
	
	public void generarCompra(int id) throws Exception{
		double total = 0;
		Cliente c = clienteDao.leer(id);
		Compra compra = new Compra();
		compra.setFecha(new Date());
		compra.setListaProductos(new ArrayList<ProductoVendido>());
		for(CarritoDetalle carrito : c.getCarrito()) {
			ProductoVendido pv = new ProductoVendido();
			pv.setCantidad(carrito.getCantidad());
			pv.setDescripcion(carrito.getProducto().getDescripcion());
			pv.setImagenes(carrito.getProducto().getImagenes());
			pv.setNombre(carrito.getProducto().getNombre());
			pv.setPrecio(carrito.getProducto().getPrecio());
			compra.getListaProductos().add(pv);
			total += (pv.getCantidad()*pv.getPrecio());
		}
		compra.setTotal(total);
		if(c.getListaCompras() !=  null) {
		c.getListaCompras().add(compra);
		}else {
			c.setListaCompras(new ArrayList<Compra>());
			c.getListaCompras().add(compra);
		}
		clienteDao.actualizar(c);
	}
}