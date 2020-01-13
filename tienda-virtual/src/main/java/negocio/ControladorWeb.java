package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.CategoriaDAO;
import datos.ProductoDAO;
import datos.UsuarioDAO;
import modelo.Categoria;
import modelo.Producto;
import modelo.Usuario;

@Stateless
public class ControladorWeb {

	@Inject
	private CategoriaDAO categoriaDao;
	
	@Inject
	private ProductoDAO productoDao;
	
	@Inject
	private UsuarioDAO usuariosDao;
	
	public List<Categoria> listarCategoria(){
		return categoriaDao.listar();
	}
	
	public void insertarCategoria(Categoria categoria) {
		categoriaDao.insertar(categoria);
	}
	
	public void actualizarCategoria(Categoria categoria) {
		categoriaDao.actualizar(categoria);
	}
	
	public Categoria leerCategoria(int codigo) {
		return categoriaDao.leer(codigo);
	}
	
	public void borrarCategoria(int codigo) {
		categoriaDao.borrar(codigo);
	}
	
	public List<Producto> listarProducto(){
		return productoDao.listar();
	}
	
	public void insertarProducto(Producto producto) {
		productoDao.insertar(producto);
	}
	
	public void actualizarProducto(Producto producto) {
		productoDao.actualizar(producto);
	}
	
	public List<Producto> buscarProducto(String nombre) {
		return productoDao.buscar(nombre);
	}
	
	public void borrarProducto(int codigo) {
		productoDao.borrar(codigo);
	}
	
	public Producto leerProducto(int codigo) {
		return productoDao.leer(codigo);
	}
	
	public List<Usuario> listarUsuarios() {
		return usuariosDao.listar();
	}
	
	public Usuario leerUsuario(String cedula) {
		return usuariosDao.leer(cedula);
	}
	
	public void borrarUsuario(String cedula) {
		usuariosDao.borrar(cedula);
	}
	
	public void insertarUsuario(Usuario usuario) {
		usuariosDao.insertar(usuario);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuariosDao.actualizar(usuario);
	}
	
}
