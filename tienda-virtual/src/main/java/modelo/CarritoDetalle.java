package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * La clase CarrtitoDetalle es algo similar a una factura detalle
 * ya que esta clase con los atributos que tiene controla la cantidad
 * de productos que uno desea adquirir
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

@Entity
public class CarritoDetalle {

	/**
	 * Atributos de la clase 
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
	 * Atributo de la clase ProductoStock, el cual permite 
	 * la relacion de muchos a uno entre la clase ProductoStock 
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
	 * Metodo que permite que al momento de imprimir el objeto por consola su 
	 * contenido sea legible.
	 */
	@Override
	public String toString() {
		return "CarritoDetalle [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}
}