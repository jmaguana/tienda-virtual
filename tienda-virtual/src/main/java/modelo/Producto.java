package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *Clase Producto, tiene todos aquellos atributos para el objeto Producto,
 *esta clase es la clase padre de ProductoStock
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Producto {

	/**
	 * Atributos de la clase
	 */
	@Id
	@GeneratedValue
	private int codigo;

	@NotNull
	private String nombre;

	@NotNull
	private String descripcion;

	@NotNull
	private String imagen;

	@NotNull
	private double precio;

	/**
	 * 
	 * @return codigo de la clase
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @param codigo, perteneciente a la clase
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * 
	 * @return nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre, recibe el nombre del producto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return la descripcion del producto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * @param descripcion, recibe la descripcion del producto
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return la imagen del producto
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * 
	 * @param imagen, recibe la imagen del producto
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * 
	 * @return precio que tiene el producto a se vendido
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * 
	 * @param precio, recibe el precio del producto
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Metodo que permite que al momento de imprimir el objeto por consola su
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen="
				+ imagen + ", precio=" + precio + "]";
	}
}