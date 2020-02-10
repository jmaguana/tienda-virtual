package modelo;

import javax.persistence.Entity;

/**
 * Clase ProductoVendido, esta clase herada de la clase Producto
 * y con su atributo nos permite saber cuantos productos son vendidos
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */
@Entity
public class ProductoVendido extends Producto{
	
	/**
	 * Atributo de la clase
	 */
	private int cantidad;

	/**
	 * 
	 * @return cantidad de los productos que se han vendido
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * @param cantidad de productos vendidos
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}