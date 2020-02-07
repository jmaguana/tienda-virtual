package modelo_servicio;

/**
 * En esta clase tenemos todos aquellos atributos y elementos que son <br>
 * necesarios para un cliente, que no tiene relacion con otras tablas o clases.
 * 
 * @author Jhonny Maguana
 * @author Sandra Peñaranda
 * @version 2.0
 * 
 */

public class CompraInfo {
	
	/**
	 * Atributos de la clase
	 */
	private int id;
	
	private String fecha;
	
	private Double total;
	
	private int totalProductos;

	/**
	 * 
	 * @return codigo perteneciente a la tabla Compartido
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id, codigo de la clase Compartido
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return fecha que se ha realizado la compra
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * 
	 * @param fecha que se ha realizado la compra
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return total a pagar por la compra realizada
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total, valor a pagar por la compra
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * 
	 * @return totalProductos de productos que se han comprado
	 */
	public int getTotalProductos() {
		return totalProductos;
	}

	/**
	 * 
	 * @param totalProductos, cantidad de productos que se han comprado
	 */
	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}
}