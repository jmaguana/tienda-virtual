package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datos.ProductoDAO;
import modelo.Categoria;
import modelo.Producto;
import modelo.ProductoStock;
import negocio.ControladorWeb;

@Path("/productos")
public class ProductosService {
	
	@Inject
	private ControladorWeb controladorWeb;

	@GET
	@Path("/listar")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ProductoStock> getProductos(){
		List<ProductoStock> productos = controladorWeb.listarProducto();
		for(ProductoStock p:productos) {
			p.getCategoria().setProductos(new ArrayList<ProductoStock>());
		}
		return productos;
	}
	
	@GET
	@Path("/buscar/{nombre}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ProductoStock> buscarProducto(@PathParam("nombre") String nombre){
		List<ProductoStock> productos = controladorWeb.buscarProducto(nombre);
		for(ProductoStock p:productos) {
			p.getCategoria().setProductos(new ArrayList<ProductoStock>());
		}
		return productos;
	}

}
