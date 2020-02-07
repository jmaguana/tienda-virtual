package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.ProductoStock;
import modelo_servicio.ProductoInfo;
import negocio.ControladorMovil;

/**
 * En esta clase tenemos un atributo que viene del paquete de negocio <br>
 * y webservices que son necesarios para la aplicacion movil en la parte <br>
 * de los productos
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Path("/productos")
public class ProductosService {

	/**
	 * Atributo de la clase de tipo ControladorMovil
	 */
	@Inject
	private ControladorMovil controladorMovil;

	/**
	 * Web serivce que permite listar los productos
	 * @return una lista de productos de tipo ProductoInfo
	 */
	@GET
	@Path("/listar")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductoInfo> getProductos() {
		try {
			List<ProductoStock> productos = controladorMovil.listarProductos();
			List<ProductoInfo> productosInfo = new ArrayList<>();
			for (ProductoStock p : productos) {
				ProductoInfo productoInfo = new ProductoInfo();
				productoInfo.setCodigo(p.getCodigo());
				productoInfo.setNombre(p.getNombre());
				productoInfo.setDescripcion(p.getDescripcion());
				productoInfo.setImagenes(p.getImagen());
				productoInfo.setPrecio(p.getPrecio());
				productoInfo.setVotos(0);
				productosInfo.add(productoInfo);
			}
			return productosInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<ProductoInfo>();
		}
	}

	/**
	 * Web service que permite dar like a un producto
	 * @param productoId codigo del producto
	 * @param clienteId codigo del cliente
	 * @param accion permite saber si al producto se le da un like o se lo quita
	 * @return un mensaje de confirmacion para saber si el metodo se <br>
	 * ejecuto correctamente
	 */
	@GET
	@Path("/like/{productoid}/{clienteid}/{accion}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String like(@PathParam("productoid") int productoId, @PathParam("clienteid") int clienteId,
			@PathParam("accion") int accion) {
		if (accion == 1) {
			try {
				controladorMovil.darLike(clienteId, productoId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		} else {
			try {
				controladorMovil.quitarLike(clienteId, productoId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		}
		return "";
	}

	/**
	 * Web service que permite saber si es un like
	 * @param productoId codigo del producto
	 * @param clienteId codigo del cliente
	 * @return true o false dependiendo si se cumple o no con la accion <br>
	 * del metodo del cual se hace uso
	 */
	@GET
	@Path("/islike/{productoid}/{clienteid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean isLiked(@PathParam("productoid") int productoId, @PathParam("clienteid") int clienteId) {
		try {
			return controladorMovil.isLiked(clienteId, productoId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Web service que permite buscar a un producto
	 * @param codigo
	 * @return un objeto de tipo ProductoInfo
	 */
	@GET
	@Path("/buscar/{codigo}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ProductoInfo buscarProducto(@PathParam("codigo") int codigo) {
		ProductoStock ps = null;
		try {
			ps = controladorMovil.buscarProducto(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductoInfo pi = new ProductoInfo();
		pi.setCodigo(ps.getCodigo());
		pi.setNombre(ps.getNombre());
		pi.setDescripcion(ps.getDescripcion());
		pi.setImagenes(ps.getImagen());
		pi.setPrecio(ps.getPrecio());
		pi.setVotos(ps.getVotos().size());
		return pi;
	}
}