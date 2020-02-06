package negocio;

//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.ClienteDAO;
import datos.CompartidoDAO;
import datos.ProductoDAO;
import modelo.CarritoDetalle;
import modelo.Cliente;
import modelo.Compartido;
import modelo.Compra;
//import modelo.Producto;
import modelo.ProductoStock;
import modelo.ProductoVendido;

@Stateless
public class ControladorMovil {

	@Inject
	private ClienteDAO clienteDao;

	@Inject
	private ProductoDAO productoDao;
	
	@Inject
	private CompartidoDAO compartidoDao;

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
			return productoDao.leerConVotos(codigo);
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

	public boolean compartir(int idEmisor, int idReceptor, int idCodigo) throws Exception {
		Compartido compartido = new Compartido();
		ProductoStock producto = productoDao.leer(idCodigo);
		System.out.println("El producto es: "+producto.getNombre());
		Cliente c1 = clienteDao.leerVacio(idEmisor);
		Cliente c2 = clienteDao.leerVacio(idReceptor);
		compartido.setClienteEnvia(c1);
		compartido.setClienteRecibe(c2);
		compartido.setProducto(producto);
		compartidoDao.insertar(compartido);
		return true;
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
		Cliente cliente = clienteDao.leerConVotos(clienteId);
		for(ProductoStock p : cliente.getListaVotos()) {
			if(productoId == p.getCodigo()) {
				return true;
			}
		}
		ProductoStock producto = productoDao.leer(productoId);
		//producto.getVotos().add(cliente);
		cliente.getListaVotos().add(producto);
		clienteDao.actualizar(cliente);
		return true;
	}
	
	public boolean isLiked(int clienteId, int productoId) throws Exception {
		Cliente cliente = clienteDao.leerConVotos(clienteId);
		if(cliente.getListaVotos() == null) {
			return false;
		}
		for(ProductoStock p : cliente.getListaVotos()) {
			if(productoId == p.getCodigo()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean quitarLike(int clienteId, int productoId) throws Exception {
		Cliente cliente = clienteDao.leerConVotos(clienteId);
		for(ProductoStock p : cliente.getListaVotos()) {
			if(productoId == p.getCodigo()) {
				cliente.getListaVotos().remove(p);
				clienteDao.actualizar(cliente);
				return true;
			}
		}
		return false;
	}
	
	public void insertarCliente(Cliente cliente) throws Exception {
		clienteDao.insertar(cliente);
	}
}