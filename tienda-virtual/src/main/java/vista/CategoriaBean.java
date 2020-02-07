package vista;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import modelo.Categoria;
import negocio.ControladorWeb;

/**
 * En esta clase tenemos los atributos necesarios para realizar un xhtml para
 * realizar un CRUD de categorias, asi como metodos los cuales hacen uso de
 * los metodos del controladorWeb
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@ManagedBean
@SessionScoped
public class CategoriaBean {

	/**
	 * Atributos de la clase
	 */
	private Categoria categoria;
	private List<Categoria> lista;
	private int codigo;
	private static Map<String, Object> listCategorias;

	/**
	 * Atributo de tipo ControladorWeb
	 */
	@Inject
	private ControladorWeb controlador;

	/**
	 * Post Constructor el cual se encarga de llamar a un metodo que lista las
	 * categorias
	 */
	@PostConstruct
	public void init() {
		loadCategoria();
	}

	/**
	 * Metodo que lista las categorias
	 */
	public void loadCategoria() {
		categoria = new Categoria();
		this.codigo = 0;
		lista = controlador.listarCategoria();
	}

	/**
	 * Metodo que lista las categorias para luego mostrarlas en un comboBox
	 * 
	 * @return un lista de categorias
	 */
	public Map<String, Object> listaCategorias() {
		listCategorias = new LinkedHashMap<String, Object>();
		loadCategoria();
		for (Categoria c : this.lista) {
			listCategorias.put(c.getNombre(), c.getCodigo());
		}
		return listCategorias;
	}

	/**
	 * Metodo que permite crear y modificar una categorias
	 * 
	 * @return un String
	 */
	public String guardar() {
		if (codigo == 0) {
			controlador.insertarCategoria(categoria);
		} else {
			controlador.actualizarCategoria(categoria);
		}
		loadCategoria();
		return "CategoriaCRUD";
	}

	/**
	 * Metodo que permite modificar una categoria por su codigo
	 * 
	 * @param codigo perteneciente a la categoria
	 * @return un String
	 */
	public String editar(int codigo) {
		System.out.println("El codigo a Editar es: " + codigo);
		this.codigo = codigo;
		categoria = controlador.leerCategoria(codigo);
		if (categoria == null) {
			loadCategoria();
			return "CategoriaCRUD";
		}
		return "CrearCategoria";
	}
	
	/**
	 * Metodo que permite eliminar una categoria
	 * @param codigo perteneciente a la categoria
	 * @return un String
	 */

	public String eliminar(int codigo) {
		try {
			controlador.borrarCategoria(codigo);
		} catch (Exception e) {

		}
		loadCategoria();
		return "CategoriaCRUD";
	}

	/**
	 * Metodo get de Categoria
	 * @return una categoria de tipo Categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Metodo set de Categoria
	 * @param categoria de tipo Categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return lista de tipo Categoria
	 */
	public List<Categoria> getLista() {
		return lista;
	}

	/**
	 * 
	 * @param lista de tipo Categoria
	 */
	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}

	/**
	 * 
	 * @return codigo de la categoria
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param codigo perteneciente a la categoria
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}