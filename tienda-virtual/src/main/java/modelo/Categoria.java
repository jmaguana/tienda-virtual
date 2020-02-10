package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase Categoria que tiene una relacion de uno a muchos con los productos
 * y todos los atributos que son necesarios para el objeto en si.
 * 
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Entity
public class Categoria {

	/**
	 * Atributos de la clase
	 */
	@Id
	@GeneratedValue
	private int codigo;

	@NotNull
	@NotEmpty
	private String nombre;
	@Size(max = 150)
	private String descripcion;

	private String imagen;

	/**
	 * Atributo de la clase ProductoStock, el cual permite la relacion de muchos a
	 * uno entre la clase ProductoStock y esta clase, para saber que categoria se
	 * relaciona con cada producto.
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria")
	private List<ProductoStock> productos;

	/**
	 * 
	 * @return lista de productos
	 */
	public List<ProductoStock> getProductos() {
		return productos;
	}

	/**
	 * 
	 * @param productos, una lista de productos pertenecientes a cada categoria
	 */
	public void setProductos(List<ProductoStock> productos) {
		this.productos = productos;
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
	 * @param codigo de la clase para relacionar
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * 
	 * @return el nombre de la categoria
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre, recibe el nombre de la categoria
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return la descripcion de la categoria
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion, recibe la descripcion de la categoria
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return imagen de la categoria
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * 
	 * @param imagen, recibe un string que es la imagen del producto
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Metodo que permite que al momento de imprimir el objeto por consola su
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen="
				+ imagen + ", productos=" + productos + "]";
	}
}