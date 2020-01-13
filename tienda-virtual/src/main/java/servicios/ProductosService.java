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
import negocio.ControladorWeb;

@Path("/productos")
public class ProductosService {
	
	@Inject
	private ControladorWeb controladorWeb;

	@GET
	@Path("/listar")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Producto> getProductos(){
		List<Producto> productos = controladorWeb.listarProducto();
		for(Producto p:productos) {
			p.getCategoria().setProductos(new ArrayList<Producto>());
		}
		return productos;
	}
	
	@GET
	@Path("/buscar/{nombre}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Producto> buscarProducto(@PathParam("nombre") String nombre){
		List<Producto> productos = controladorWeb.buscarProducto(nombre);
		for(Producto p:productos) {
			p.getCategoria().setProductos(new ArrayList<Producto>());
		}
		return productos;
	}

}
