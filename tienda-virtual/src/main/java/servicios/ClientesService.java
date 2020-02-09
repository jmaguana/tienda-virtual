package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.Cliente;
import modelo.Compartido;
import modelo_servicio.ClienteInfo;
import modelo_servicio.ProductoInfo;
import negocio.ControladorMovil;

/**
 * En esta clase tenemos un atributo que viene del paquete de negocio <br>
 * y webservices que son necesarios para la aplicacion movil en la parte <br>
 * del cliente
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Path("/clientes")
public class ClientesService {

	/**
	 * Atributo de la clase de tipo ControladorMovil
	 */
	@Inject
	private ControladorMovil controladorMovil;

	/**
	 * Web service que permite listar a los clientes
	 * 
	 * @return una lista de clientes
	 */
	@GET
	@Path("listar/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ClienteInfo> getClientes(@PathParam("id") int codigo) {
		try {
			List<Cliente> clientes = controladorMovil.listarClientes();
			List<ClienteInfo> clientesInfo = new ArrayList<>();
			for (Cliente c : clientes) {
				ClienteInfo clienteInfo = new ClienteInfo();
				clienteInfo.setCodigo(c.getId());
				clienteInfo.setNombre(c.getNombre());
				clienteInfo.setApellidos(c.getApellidos());
				// clienteInfo.setImagen(c.getImagen());
				// clienteInfo.setFechaNacimiento(c.getFechaNacimiento().toString());
				clienteInfo.setCorreo(c.getCorreo());
				clienteInfo.setTelefono(c.getTelefono());
				if(codigo != c.getId()) {
					clientesInfo.add(clienteInfo);
				}
			}
			return clientesInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<ClienteInfo>();
		}
	}

	/**
	 * Web service que permite compartir un producto entre los clientes
	 * 
	 * @param emisor   codigo del cliente que envia el producto
	 * @param receptor codigo del cliente que recibira el producto
	 * @param producto codigo del producto a ser compartido
	 * @return true o false dependiendo del resultado
	 */
	@GET
	@Path("compartir/{idEmisor}/{idReceptor}/{idProducto}")
	@Produces("application/json")
	public boolean compartir(@PathParam("idEmisor") int emisor, @PathParam("idReceptor") int receptor,
			@PathParam("idProducto") int producto) {
		try {
			boolean a = controladorMovil.compartir(emisor, receptor, producto);
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Web service que permite realizar un login en la aplicacion
	 * @param correo perteneciente al cliente
	 * @param contrasenia perteneciente al cliente
	 * @return un objeto cliente de tipo ClienteInfo
	 */
	@GET
	@Path("login/{correo}/{contrasenia}")
	@Produces("application/json")
	public ClienteInfo login(@PathParam("correo") String correo, @PathParam("contrasenia") String contrasenia) {
		try {
			Cliente c = controladorMovil.login(correo, contrasenia);
			ClienteInfo ci = new ClienteInfo();
			ci.setCodigo(c.getId());
			ci.setNombre(c.getNombre());
			ci.setApellidos(c.getApellidos());
			ci.setCorreo(c.getCorreo());
			// ci.setImagen(c.getImagen());
			// ci.setFechaNacimiento(c.getFechaNacimiento().toString());
			ci.setTelefono(c.getTelefono());
			return ci;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Web service que permite crear un nuevo cliente, es de tipo POST
	 * @param cliente de tipo Cliente
	 */
	@POST
	@Path("crearCliente")
	@Produces("application/json")
	@Consumes("application/json")
	public void createClient(ClienteInfo clienteInfo) {
		Cliente cliente = new Cliente();
		try {
			cliente.setNombre(clienteInfo.getNombre());
			cliente.setContrasenia(clienteInfo.getContrasenia());
			cliente.setApellidos(clienteInfo.getContrasenia());
			cliente.setTelefono(clienteInfo.getTelefono());
			cliente.setCorreo(clienteInfo.getCorreo());
			
			controladorMovil.insertarCliente(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@GET
	@Path("listacompartido/{codigo}")
	@Produces("application/json")
	public List<ProductoInfo> listarCompartido(@PathParam("codigo") int codigo) {
		try {
			List<Compartido> lista = controladorMovil.listarCompartido(codigo);
			List<ProductoInfo> listaCompartido = new ArrayList<>();
			for(Compartido c: lista) {
				ProductoInfo producto = new ProductoInfo();
				producto.setNombreCompartido(c.getClienteEnvia().getNombre()+" "+c.getClienteEnvia().getApellidos());
				producto.setCorreoCompartido(c.getClienteEnvia().getCorreo());
				producto.setNombre(c.getProducto().getNombre());
				producto.setCodigo(c.getProducto().getCodigo());
				producto.setDescripcion(c.getProducto().getDescripcion());
				producto.setImagenes(c.getProducto().getImagen());
				producto.setPrecio(c.getProducto().getPrecio());
				producto.setVotos(0);
				listaCompartido.add(producto);
			}
			return listaCompartido;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("numerocompartido/{codigo}")
	@Produces("application/json")
	public int numeroCompartido(@PathParam("codigo") int codigo) {
		try {
			return controladorMovil.compartidoSinVer(codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}