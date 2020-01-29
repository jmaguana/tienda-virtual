package negocio;

import java.text.DecimalFormat;
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

	public Cliente login(String correo, String contrasenia) throws Exception {
		return clienteDao.login(correo, contrasenia);
	}

	public List<Cliente> listarClientes() throws Exception {
		return clienteDao.listar();
	}

	public List<ProductoStock> listarProductos() throws Exception {
		return productoDao.listarProductos();
	}

	public ProductoStock buscarProducto(int codigo) throws Exception{
		try {
			return productoDao.leer(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<CarritoDetalle> listarProductoCarrito(int codigo) throws Exception{
		return clienteDao.listarProductosCarrito(codigo);
	}

	public List<ProductoStock> listarProductosVendidos() {
		try {
			return productoDao.listarProductosVendidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Compra> listarCompras(int id_cliente) throws Exception{
		
			return clienteDao.listarCompras(id_cliente);
		
	}

	public void insertarCarrito(int idCliente, int idProducto, int cantidad) throws Exception {
		ProductoStock p = productoDao.leer(idProducto);
		Cliente c = clienteDao.leer(idCliente);
		CarritoDetalle cDetalle = null;
		for(CarritoDetalle cd : c.getCarrito()) {
			if(cd.getProducto().getCodigo() == idProducto) {
				cDetalle = cd;
				break;
			}
		}
		if(cDetalle == null) {
			CarritoDetalle cd = new CarritoDetalle();
			cd.setProducto(p);
			cd.setCantidad(cantidad);
			c.getCarrito().add(cd);
		}else{
			cDetalle.setCantidad(cDetalle.getCantidad()+cantidad);
		}
		
		clienteDao.actualizar(c);
	}

	public boolean generarCompra(int id) throws Exception {
		double total = 0;
		Cliente c = clienteDao.leer(id);
		Compra compra = new Compra();
		compra.setFecha(new Date());
		compra.setListaProductos(new ArrayList<ProductoVendido>());
		if(c.getCarrito().size()<=0) {
			return false;
		}
		for (CarritoDetalle carrito : c.getCarrito()) {
			if(carrito.getCantidad()<=carrito.getProducto().getStock()) {
							
				ProductoVendido pv = new ProductoVendido();
				pv.setCantidad(carrito.getCantidad());
				pv.setDescripcion(carrito.getProducto().getDescripcion());
				pv.setImagen(carrito.getProducto().getImagen());
				pv.setNombre(carrito.getProducto().getNombre());
				pv.setPrecio(carrito.getProducto().getPrecio());
				compra.getListaProductos().add(pv);
				total += (pv.getCantidad() * pv.getPrecio());
				ProductoStock ps = productoDao.leer(carrito.getProducto().getCodigo());
				ps.setVendido(ps.getVendido()+carrito.getCantidad());
				productoDao.actualizar(ps);
				carrito.getProducto().setStock(carrito.getProducto().getStock()-carrito.getCantidad());
			}else {
				System.out.println("Stock insuficiente");
			}
		}
		total = (double)Math.round(total * 100d) / 100d;
		if (total == 0.0) {
			return false;
		}
		
		compra.setTotal(total);
		if (c.getListaCompras() != null) {
			c.getListaCompras().add(compra);
			c.getCarrito().clear();
		} else {
			c.setListaCompras(new ArrayList<Compra>());
			c.getListaCompras().add(compra);
			c.getCarrito().clear();
		}
		clienteDao.actualizar(c);
		return true;
	}
	
	public boolean darLike(int clienteId, int productoId) throws Exception {
		Cliente cliente = clienteDao.leer(clienteId);
		for(ProductoStock p : cliente.getListaVotos()) {
			if(productoId == p.getCodigo()) {
				return true;
			}
		}
		ProductoStock producto = productoDao.leer(productoId);
		cliente.getListaVotos().add(producto);
		clienteDao.actualizar(cliente);
		return true;
	}
	
	public boolean quitarLike(int clienteId, int productoId) throws Exception {
		Cliente cliente = clienteDao.leer(clienteId);
		for(ProductoStock p : cliente.getListaVotos()) {
			if(productoId == p.getCodigo()) {
				cliente.getListaVotos().remove(p);
				clienteDao.actualizar(cliente);
				return true;
			}
		}
		return false;
	}
}