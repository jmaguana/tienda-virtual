package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import datos.CategoriaDAO;
import datos.ProductoDAO;
import datos.UsuarioDAO;
import modelo.Categoria;
import modelo.ProductoStock;
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
	
	public List<ProductoStock> listarProducto(){
		try {
			return productoDao.listarProductos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void insertarProducto(ProductoStock producto) {
		try {
			productoDao.insertar(producto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actualizarProducto(ProductoStock producto) {
		try {
			productoDao.actualizar(producto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<ProductoStock> buscarProducto(String nombre) {
		try {
			return productoDao.buscar(nombre);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void borrarProducto(int codigo) {
		try {
			productoDao.borrar(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ProductoStock leerProducto(int codigo) {
		try {
			return productoDao.leer(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		}
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
	
	public void insertarUsuario(Usuario usuario)  {
		try{
			usuariosDao.insertar(usuario);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuariosDao.actualizar(usuario);
	}
	
	public List<ProductoStock> listarProductosVendidos(){
		try {
			return productoDao.listarProductosVendidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
