package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.ClienteDAO;
import datos.ProductoDAO;
import modelo.Cliente;
import modelo.ProductoStock;

@Stateless
public class ControladorMovil {

	@Inject
	private ClienteDAO clienteDao;
	
	@Inject
	private ProductoDAO productoDao;
	
	public Cliente login(String correo, String contrasenia) {
		return clienteDao.login(correo, contrasenia);
	}
	
	
	public List<Cliente> listarClientes() throws Exception{
		return clienteDao.listar();
	}
	
	public List<ProductoStock> listarProductos() throws Exception{
		return productoDao.listarProductos();
	}
	
	public ProductoStock buscarProducto(int codigo) {
		return productoDao.leer(codigo);
	}	
}