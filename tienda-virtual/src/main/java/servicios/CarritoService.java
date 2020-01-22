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

@Path("/carrito")
public class CarritoService {
	
	@Inject
	private ControladorMovil controladorMovil;

	@GET
	@Path("/listar/{codigoCliente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ProductoInfo> listarCarrito(@PathParam("codigoCliente") int codigoCliente) {
		try {
			List<CarritoDetalle> carritos = controladorMovil.listarProductoCarrito(codigoCliente);
			List<ProductoInfo> carritosInfo = new ArrayList<>();
			for(CarritoDetalle c : carritos) {
				ProductoInfo carritoInfo =  new ProductoInfo();
				carritoInfo.setCodigo(c.getProducto().getCodigo());
				carritoInfo.setCantidad(c.getCantidad());
				carritoInfo.setDescripcion(c.getProducto().getDescripcion());
				carritoInfo.setImagenes(c.getProducto().getImagenes());
				carritoInfo.setNombre(c.getProducto().getNombre());
				carritoInfo.setPrecio(c.getProducto().getPrecio());
				carritoInfo.setCategoria(c.getProducto().getCategoria().getNombre());
				carritosInfo.add(carritoInfo);
			}
			return carritosInfo;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GET
	@Path("/insertar/{codigoCliente}/{codigoProducto}/{cantidad}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String insertarCarrito(@PathParam("codigoCliente") int codigoCliente, @PathParam("codigoProducto") int codigoProducto, @PathParam("cantidad") int cantidad) {
		try {
			controladorMovil.insertarCarrito(codigoCliente, codigoProducto, cantidad);
			return "Ok";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
}
