package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * En esta clase tenemos todos aquellos atributos <br>
 * y elementos que son necesarios para realizar <br>
 * la factura detalle
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Entity
public class CarritoDetalle {

	/**
	 * Atributos de la clase <br>
	 * Id, valor autogenerado
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * Cantidad de productos a comprar
	 */
	@NotNull
	private int cantidad;

	/**
	 * Atributo de la clase ProductoStock, el cual permite <br>
	 * la relacion de muchos a uno entre la clase ProductoStock <br>
	 * y esta clase.
	 */
	@ManyToOne
	@JoinColumn(name = "producto")
	private ProductoStock producto;

	/**
	 * Obtener el codigo de la factura detalle
	 * 
	 * @return el codigo de la clase
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * Codigo de la clase
	 * 
	 * @param codigo de la clase
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return cantidad de productos a adquirir
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Cantidad de productos
	 * 
	 * @param cantidad a comprar
	 */

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * 
	 * @return un producto de tipo ProductoStock
	 */
	public ProductoStock getProducto() {
		return producto;
	}

	/**
	 * Productos
	 * 
	 * @param producto que viene de la clase ProductoStock
	 */
	public void setProducto(ProductoStock producto) {
		this.producto = producto;
	}

	/**
	 * Metodo que permite que al momento de imprimir el objeto por consola su <br>
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "CarritoDetalle [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}
}
