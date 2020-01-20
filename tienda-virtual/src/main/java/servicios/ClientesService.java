package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.Cliente;
import modelo_servicio.ClienteInfo;
import negocio.ControladorMovil;

@Path("/clientes")
public class ClientesService {

	@Inject
	private ControladorMovil controladorMovil;
	
	@GET
	@Path("/listar")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ClienteInfo> getClientes(){
		try {
			List<Cliente> clientes= controladorMovil.listarClientes();
			List<ClienteInfo> clientesInfo = new ArrayList<>();
			for(Cliente c: clientes) {
				ClienteInfo clienteInfo = new ClienteInfo();
				clienteInfo.setCodigo(c.getId());
				clienteInfo.setNombre(c.getNombre());
				clienteInfo.setApellidos(c.getApellidos());
				clienteInfo.setImagen(c.getImagen());
				clienteInfo.setFechaNacimiento(c.getFechaNacimiento());
				clienteInfo.setCorreo(c.getCorreo());
				clientesInfo.add(clienteInfo);
			}
			return clientesInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<ClienteInfo>();
		}
	}
	
	@GET
	@Path("Login/{correo}/{contrasenia}")
	@Produces("application/json")
	public ClienteInfo login(@PathParam("correo") String correo, @PathParam("contrasenia") String contrasenia) {
		try {
			Cliente c = controladorMovil.login(correo, contrasenia);
			ClienteInfo ci = new ClienteInfo();
			ci.setCodigo(c.getId());
			ci.setNombre(c.getNombre());
			ci.setApellidos(c.getApellidos());
			ci.setCorreo(c.getCorreo());
			ci.setImagen(c.getImagen());
			ci.setFechaNacimiento(c.getFechaNacimiento());
			return ci;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
