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

@Path("/productos")
public class ProductosService {

	@Inject
	private ControladorMovil controladorMovil;

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
				productoInfo.setVotos(p.getVotos().size());
				productosInfo.add(productoInfo);
			}
			return productosInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<ProductoInfo>();
		}
	}
	/*
	 * accion = 1 => Dar Like  
	 */
	@GET
	@Path("/like/{productoid}/{clienteid}/{accion}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String like(@PathParam("productoid") int productoId, @PathParam("clienteid") int clienteId, @PathParam("accion") int accion) {
		if(accion == 1) {
			try {
				controladorMovil.darLike(clienteId, productoId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		}else {
			try {
				controladorMovil.quitarLike(clienteId, productoId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		}
		return "";
	}

	@GET
	@Path("/buscar/{codigo}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ProductoInfo buscarProducto(@PathParam("codigo") int codigo) {
		ProductoStock ps=null;
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
		return pi;
	}
}