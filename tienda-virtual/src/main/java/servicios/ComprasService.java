package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.Compra;
import modelo_servicio.CompraInfo;
import negocio.ControladorMovil;

/**
 * En esta clase tenemos un atributo que viene del paquete de negocio <br>
 * y webservices que son necesarios para la aplicacion movil en la parte <br>
 * de las compras
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Path("/compras")
public class ComprasService {
	
	/**
	 * Atributo de la clase de tipo ControladorMovil
	 */
	@Inject
	private ControladorMovil controladorMovil;
	
	@GET
	@Path("/generarCompra/{codigoCliente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String generarCompras(@PathParam("codigoCliente") int codigoCliente) {
		try {
			boolean a = controladorMovil.generarCompra(codigoCliente);
			if(a) {
				return "ok";
			}else {
				return "NO";
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/listar/{codigoCliente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<CompraInfo> listarCompras(@PathParam("codigoCliente") int codigoCliente) {
		try {
			List<Compra> compras = controladorMovil.listarCompras(codigoCliente);
			List<CompraInfo> comprasInfo = new ArrayList<>();
			for(Compra c : compras) {
				CompraInfo compraInfo =  new CompraInfo();
				compraInfo.setFecha(c.getFecha().toString());
				compraInfo.setTotal(c.getTotal());
				compraInfo.setId(c.getId());
				compraInfo.setTotalProductos(c.getListaProductos().size());
				comprasInfo.add(compraInfo);
			}
			return comprasInfo;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
