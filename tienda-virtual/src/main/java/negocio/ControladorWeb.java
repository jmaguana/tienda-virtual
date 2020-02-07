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

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y metodos que son necesarios en esta clase y que van a ser <br>
 * utilizados por una aplicacion web
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Stateless
public class ControladorWeb {

	/**
	 * Atributos de la clase, que vienen del paquete de datos
	 */
	@Inject
	private CategoriaDAO categoriaDao;
	
	@Inject
	private ProductoDAO productoDao;
	
	@Inject
	private UsuarioDAO usuariosDao;
	
	/**
	 * Metodo que permite listar las categorias
	 * @return un listado de categorias
	 */
	public List<Categoria> listarCategoria(){
		return categoriaDao.listar();
	}
	
	/**
	 * Metodo que permite insertar una categoria
	 * @param categoria de tipo Categoria
	 */
	public void insertarCategoria(Categoria categoria) {
		categoriaDao.insertar(categoria);
	}
	
	/**
	 * Metodo que permite actualizar una categoria
	 * @param categoria de tipo Categoria
	 */
	public void actualizarCategoria(Categoria categoria) {
		categoriaDao.actualizar(categoria);
	}
	
	/**
	 * Metodo que permite buscar una categoria por su codigo
	 * @param codigo
	 * @return un objeto de titpo Categoria
	 */
	public Categoria leerCategoria(int codigo) {
		return categoriaDao.leer(codigo);
	}
	
	/**
	 * Metodo que permite eliminar una categoria
	 * @param codigo de la categoria a eliminar
	 */
	public void borrarCategoria(int codigo) {
		categoriaDao.borrar(codigo);
	}
	
	/**
	 * Metodo que permite listar los productos
	 * @return una lista de productos en caso de ser exitosa la busqueda <br>
	 * caso contrario retorna null
	 */
	public List<ProductoStock> listarProducto(){
		try {
			return productoDao.listarProductos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Metodo que permite insertar un producto 
	 * @param producto de tipo ProductoStock
	 */
	public void insertarProducto(ProductoStock producto) {
		try {
			productoDao.insertar(producto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que permite actualizar los campos de un producto <br>
	 * en la BD
	 * @param producto de tipo ProductoStock
	 */
	public void actualizarProducto(ProductoStock producto) {
		try {
			productoDao.actualizar(producto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que permite buscar los productos sin necesidad de escribir el nombre <br>
	 * completo
	 * @param nombre, nombre perteneciente al producto
	 * @return productos que es una lista de tipo ProductoStock
	 */
	public List<ProductoStock> buscarProducto(String nombre) {
		try {
			return productoDao.buscar(nombre);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Metodo que permite eliminar un producto mediante un codigo
	 * @param codigo de tipo int que pertenece al producto
	 */
	public void borrarProducto(int codigo) {
		try {
			productoDao.borrar(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que permite realizar la busqueda de un producto
	 * @param codigo de tipo int y pertenece a un producto
	 * @return producto que un objeto de tipo ProductoStock
	 */
	public ProductoStock leerProducto(int codigo) {
		try {
			return productoDao.leer(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		}
	}
	
	/**
	 * Metodo que permite listar a todos los usuarios
	 * @return usuarios que es una lista de tipo Usuario
	 */
	public List<Usuario> listarUsuarios() {
		return usuariosDao.listar();
	}
	
	/**
	 * Metodo que permite realizar la busqueda de un usuario
	 * @param cedula de tipo String y pertenece a un Usuario
	 * @return usuario que un objeto de tipo Usuario
	 */
	public Usuario leerUsuario(String cedula) {
		return usuariosDao.leer(cedula);
	}
	
	/**
	 * Metodo que permite eliminar un usuario mediante un codigo
	 * @param cedula de tipo int que pertenece al usuario
	 */
	public void borrarUsuario(String cedula) {
		usuariosDao.borrar(cedula);
	}
	
	/**
	 * Metodo que permite insertar un usuario
	 * 
	 * @param usuario de tipo Usuario
	 */
	public void insertarUsuario(Usuario usuario)  {
		try{
			usuariosDao.insertar(usuario);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo que permite actualizar los campos de un usuario
	 * @param usuario de tipo Usuario
	 */
	public void actualizarUsuario(Usuario usuario) {
		usuariosDao.actualizar(usuario);
	}
	
	/**
	 * Metodo que permite listar los productos que ya se han vendido
	 * @return productosVendidos que es una lista de tipo ProductoStock
	 */
	public List<ProductoStock> listarProductosVendidos(){
		try {
			return productoDao.listarProductosVendidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
