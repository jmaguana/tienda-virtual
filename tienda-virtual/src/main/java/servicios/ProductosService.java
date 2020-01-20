package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.ProductoStock;
import modelo.Usuario;
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
				productoInfo.setImagenes(p.getImagenes());
				productoInfo.setPrecio(p.getPrecio());
				productoInfo.setVotos(productos.size());
				productosInfo.add(productoInfo);
			}
			return productosInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<ProductoInfo>();
		}
	}

	@GET
	@Path("/buscar/{codigo}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ProductoInfo buscarProducto(@PathParam("codigo") int codigo) {
		ProductoStock ps = controladorMovil.buscarProducto(codigo);
		ProductoInfo pi = new ProductoInfo();
		pi.setCodigo(ps.getCodigo());
		pi.setNombre(ps.getNombre());
		pi.setDescripcion(ps.getDescripcion());
		pi.setImagenes(ps.getImagenes());
		pi.setPrecio(ps.getPrecio());
		return pi;
	}
}