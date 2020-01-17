package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.ClienteDAO;
import modelo.Cliente;

@Stateless
public class ControladorMovil {

	@Inject
	private ClienteDAO clienteDao;
	
	public List<Cliente> listarClientes() throws Exception{
		return clienteDao.listar();
	}
}
