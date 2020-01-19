package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.Producto;
import modelo.ProductoStock;
import modelo_servicio.ProductoInfo;
import negocio.ControladorMovil;

@Path("/productos")
public class ProductosService {
	
	@Inject
	private ControladorMovil controladorMovil;

	@GET
	@Path("/listar")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ProductoInfo> getProductos(){
		try {
			List<ProductoStock> productos = controladorMovil.listarProductos();
			List<ProductoInfo> productosInfo = new ArrayList<>();
			for(ProductoStock p : productos) {
				ProductoInfo productoInfo = new ProductoInfo();
				productoInfo.setNombre(p.getNombre());
				productoInfo.setDescripcion(p.getDescripcion());
				productoInfo.setImagenes(p.getImagenes());
				productoInfo.setPrecio(p.getPrecio());
				productosInfo.add(productoInfo);
			}
			return productosInfo;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ArrayList<ProductoInfo>();
		}
	}
}
