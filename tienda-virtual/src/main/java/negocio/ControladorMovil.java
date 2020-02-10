package negocio;

//import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.ClienteDAO;
import datos.CompartidoDAO;
import datos.CompraDAO;
import datos.ProductoDAO;
import modelo.CarritoDetalle;
import modelo.Cliente;
import modelo.Compartido;
import modelo.Compra;
//import modelo.Producto;
import modelo.ProductoStock;
import modelo.ProductoVendido;
import modelo_servicio.CompraInfo;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y metodos que son necesarios en esta clase y que van a ser <br>
 * utilizados por una aplicacion movil
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Stateless
public class ControladorMovil {

	/**
	 * Atributos de la clase, que vienen del paquete de datos
	 */
	@Inject
	private ClienteDAO clienteDao;

	@Inject
	private ProductoDAO productoDao;
	
	@Inject
	private CompartidoDAO compartidoDao;
	
	@Inject
	private CompraDAO compraDao;

	/**
	 * Metodo que permite realizar un login
	 * @param correo que pertenece al cliente
	 * @param contrasenia que pertenece al cliente
	 * @return un objeto de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar el login
	 */
	public Cliente login(String correo, String contrasenia) throws Exception {
		return clienteDao.login(correo, contrasenia);
	}

	/**
	 * Metodo que permite listar todos los campos pertenecientes a <br>
	 * un cliente
	 * @return clientes que es una lista de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problema <br>
	 * al realizar la consulta
	 */
	public List<Cliente> listarClientes() throws Exception {
		return clienteDao.listar();
	}

	/**
	 * Metodo que permite listar los productos 
	 * @return productos que es una lista de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al listar los productos
	 */
	public List<ProductoStock> listarProductos() throws Exception {
		return productoDao.listarProductos();
	}

	/**
	 * Metodo que permite realizar la busqueda de un producto, el cual <br>
	 * tenga votos registrados
	 * @param codigo de tipo int y pertenece a un producto
	 * @return producto que un objeto de tipo ProductoStock
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al buscar un objeto de tipo ProductoStock
	 */
	public ProductoStock buscarProducto(int codigo) throws Exception{
			return productoDao.leerConVotos(codigo);
	}

	/**
	 * Metodo que permite listar los productos que se hayan aniadido <br>
	 * al carrito de compras
	 * @param id, codigo del cliente
	 * @return una lista de los productos que se desean comprar
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al listar
	 */
	public List<CarritoDetalle> listarProductoCarrito(int codigo) throws Exception{
		return clienteDao.listarProductosCarrito(codigo);
	}

	/**
	 * Metodo que permite listar los productos que han sido vendidos
	 * @return una lista de productos de tipo ProductoStock
	 */
	public List<ProductoStock> listarProductosVendidos() {
		try {
			return productoDao.listarProductosVendidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Metodo que permite listar las compras
	 * @param id_cliente
	 * @return una listado de las compras que ha realizado un cliente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al listar
	 */
	public List<Compra> listarCompras(int id_cliente) throws Exception{
			return clienteDao.listarCompras(id_cliente);
	}

	/**
	 * Metodo que permite compartir productos entre los clientes
	 * @param idEmisor codigo del que envia
	 * @param idReceptor codigo del que recibe
	 * @param idCodigo 
	 * @return True en caso de realizar el metodo correctamente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al compartir
	 */
	public boolean compartir(int idEmisor, int idReceptor, int idCodigo) throws Exception {
		Compartido compartido = new Compartido();
		ProductoStock producto = productoDao.leer(idCodigo);
		System.out.println("El producto es: "+producto.getNombre());
		Cliente c1 = clienteDao.leerVacio(idEmisor);
		Cliente c2 = clienteDao.leerVacio(idReceptor);
		compartido.setClienteEnvia(c1);
		compartido.setClienteRecibe(c2);
		compartido.setProducto(producto);
		compartido.setVisto(false);
		compartidoDao.insertar(compartido);
		return true;
	}
	
	/**
	 * Metodo que permite agregar productos al carrito
	 * @param idCliente codigo del cliente
	 * @param idProducto codigo del producto
	 * @param cantidad cantidad del producto
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al insertar
	 */
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
	
	public CompraInfo preCompra(int id) throws Exception{
		double total = 0;
		int cantidad = 0;
		Cliente c = clienteDao.leer(id);
		CompraInfo compra = new CompraInfo();
		if(c.getCarrito().size()<=0) {
			return null;
		}
		for (CarritoDetalle carrito : c.getCarrito()) {
			if(carrito.getCantidad()<=carrito.getProducto().getStock()) {
				total += (carrito.getCantidad() * carrito.getProducto().getPrecio());
				cantidad = cantidad + carrito.getCantidad();
			}
		}
		compra.setTotalProductos(cantidad);
		compra.setTotal(total);
		compra.setFecha((new Date()).toString());
		return compra;
	}	

	/**
	 * Metodo que permite realizar una compra
	 * @param id codigo del cliente
	 * @return True en caso de ser exitosa la compra
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al relizar la compra
	 */
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
				throw new Exception("No existe suficiente Stock de: "+carrito.getProducto().getNombre());
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
		c.setCompras(c.getCompras()+1);
		clienteDao.actualizar(c);
		return true;
	}
	
	/**
	 * Metodo que permite dar un me gusta a un producto
	 * @param clienteId codigo del cliente
	 * @param productoId codigo del producto
	 * @return True en caso de ser exitoso el metodo
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al dar like
	 */
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
	
	/**
	 * Metodo que permite saber si el producto ya tiene un me gusta 
	 * @param clienteId codigo del cliente
	 * @param productoId codigo del producto
	 * @return True en caso de ser satisfactorio el resultado
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al verificar like
	 */
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
	

	/**
	 * Metodo que permite quitar un me gusta a un producto
	 * @param clienteId codigo del cliente
	 * @param productoId codigo del producto
	 * @return True en caso de ser exitoso el metodo
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al quitar un like
	 */
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
	
	/**
	 * Metodo que permite insertar un cliente en la BD
	 * 
	 * @param cliente de tipo Cliente
	 * @throws Exception se genera una excepcion si existe problemas <br>
	 * al insertar un objeto de tipo Cliente
	 */
	public void insertarCliente(Cliente cliente) throws Exception {
		clienteDao.insertar(cliente);
	}
	
	public List<Compartido> listarCompartido(int codigo) throws Exception {
		Cliente cliente = clienteDao.leerConCompartido(codigo);
		for(Compartido compartido: cliente.getListaRecibido()) {
			compartido.setVisto(true);
		}
		clienteDao.actualizar(cliente);
		return cliente.getListaRecibido();
	}
	
	public int compartidoSinVer(int codigo) throws Exception {
		Cliente cliente = clienteDao.leerConCompartido(codigo);
		int numero = 0;
		for(Compartido compartido: cliente.getListaRecibido()) {
			if(!compartido.isVisto()) {
				numero++;
			}
		}
		return numero;
	}
	
	public void eliminarCarrito(int codigocliente, int codigoProducto)throws Exception{
		Cliente cliente = clienteDao.leer(codigocliente);
		for(CarritoDetalle cd: cliente.getCarrito()) {
			if(cd.getProducto().getCodigo() == codigoProducto) {
				cliente.getCarrito().remove(cd);
				break;
			}
		}
		clienteDao.actualizar(cliente);
	}
	
	public List<ProductoVendido> listarCompra(int codigo) throws Exception{
		Compra compra = compraDao.leer(codigo);
		return compra.getListaProductos();
	}
}


