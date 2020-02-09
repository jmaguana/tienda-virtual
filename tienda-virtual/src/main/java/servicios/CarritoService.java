package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.CarritoDetalle;
import modelo_servicio.ProductoInfo;
import negocio.ControladorMovil;

/**
 * En esta clase tenemos un atributo que viene del paquete de negocio <br>
 * y webservices que son necesarios para la aplicacion movil en la parte <br>
 * del carrito
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Path("/carrito")
public class CarritoService {

	/**
	 * Atributo de la clase de tipo ControladorMovil
	 */
	@Inject
	private ControladorMovil controladorMovil;

	/**
	 * Web service que permite listar los productos que se tiene en el carrito
	 * 
	 * @param codigoCliente
	 * @return una lista de los productos
	 */
	@GET
	@Path("/listar/{codigoCliente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductoInfo> listarCarrito(@PathParam("codigoCliente") int codigoCliente) {
		try {
			List<CarritoDetalle> carritos = controladorMovil.listarProductoCarrito(codigoCliente);
			List<ProductoInfo> carritosInfo = new ArrayList<>();
			for (CarritoDetalle c : carritos) {
				ProductoInfo carritoInfo = new ProductoInfo();
				carritoInfo.setCodigo(c.getProducto().getCodigo());
				carritoInfo.setCantidad(c.getCantidad());
				carritoInfo.setDescripcion(c.getProducto().getDescripcion());
				carritoInfo.setImagenes(c.getProducto().getImagen());
				carritoInfo.setNombre(c.getProducto().getNombre());
				carritoInfo.setPrecio(c.getProducto().getPrecio());
				carritoInfo.setCategoria(c.getProducto().getCategoria().getNombre());
				carritosInfo.add(carritoInfo);
			}
			return carritosInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Web service que permite insertar prodcutos al carrito
	 * 
	 * @param codigoCliente
	 * @param codigoProducto
	 * @param cantidad,      cantidad de productos a ingresar en el carrito
	 * @return un mensaje para saber si se insertaron o no los productos
	 */
	@GET
	@Path("/insertar/{codigoCliente}/{codigoProducto}/{cantidad}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String insertarCarrito(@PathParam("codigoCliente") int codigoCliente,
			@PathParam("codigoProducto") int codigoProducto, @PathParam("cantidad") int cantidad) {
		try {
			controladorMovil.insertarCarrito(codigoCliente, codigoProducto, cantidad);
			return "Ok";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * Web service que permite eliminar un producto ya agregado al carrito
	 * 
	 * @param codigoCliente
	 * @param codigoProducto
	 * @return un mensaje para verificar si elimino o no el producto
	 */
	@GET
	@Path("/eliminar/{codigoCliente}/{codigoProducto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String eliminarCarrito(@PathParam("codigoCliente") int codigoCliente,
			@PathParam("codigoProducto") int codigoProducto) {
		try {
			controladorMovil.eliminarCarrito(codigoCliente, codigoProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
		return "";
	}
}